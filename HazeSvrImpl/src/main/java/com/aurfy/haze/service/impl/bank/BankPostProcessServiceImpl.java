package com.aurfy.haze.service.impl.bank;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aurfy.haze.core.model.payment.HazeRespCode;
import com.aurfy.haze.core.model.payment.PayStatusEnum;
import com.aurfy.haze.core.model.payment.RespCode;
import com.aurfy.haze.dao.configuration.channel.ChannelMapper;
import com.aurfy.haze.dao.configuration.channel.ChannelProviderMapper;
import com.aurfy.haze.dao.payment.PaymentFlowMapper;
import com.aurfy.haze.dao.payment.PaymentSummaryMapper;
import com.aurfy.haze.entity.configuration.channel.ChannelProviderEntity;
import com.aurfy.haze.entity.payment.PaymentFlowEntity;
import com.aurfy.haze.entity.payment.PaymentSummaryEntity;
import com.aurfy.haze.service.api.bank.BankPostProcessService;
import com.aurfy.haze.service.api.payment.PaymentService;
import com.aurfy.haze.service.bean.bank.BankRespData;
import com.aurfy.haze.service.bean.bank.BankResponse;
import com.aurfy.haze.service.bean.payment.PayResponse;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.exceptions.DuplicateException;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.bank.handler.BankHandler;
import com.aurfy.haze.service.impl.bank.handler.HandlerHelper;

@Service(AOP_NAME.BANK_POST_PROCESS_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BankPostProcessServiceImpl implements BankPostProcessService {

	private static final Logger logger = LoggerFactory.getLogger(BankPostProcessServiceImpl.class);

	@Autowired
	private PaymentFlowMapper paymentFlowMapper;
	@Autowired
	private PaymentSummaryMapper paymentSummaryMapper;
	@Autowired
	private ChannelProviderMapper channelProviderMapper;
	@Autowired
	private ChannelMapper channelMapper;
	@Autowired
	private PaymentService paymentService;

	@Override
	public void onCallback(String bankOrderId, Map<String, String> data) throws ServiceException,
			RuntimeServiceException {

		logger.debug("get the parameter from the controller : {}", data.toString());
		logger.debug("get the paymentflow with the order id {}", bankOrderId);
		PaymentFlowEntity paymentFlow = paymentFlowMapper.selectByBankOrderId(bankOrderId);
		PaymentFlowEntity paymentFlowUpdate = new PaymentFlowEntity();
		paymentFlowUpdate.setID(paymentFlow.getID());
		logger.debug("get channel provider by provider id {}", paymentFlow.getProviderId());
		ChannelProviderEntity channelProvider = channelProviderMapper.selectOne(paymentFlow.getProviderId());
		logger.debug("get Handler by channel provider class");
		BankHandler handler = HandlerHelper.getHandler(channelProvider.getProviderClassifier());

		logger.debug("1. signature validation");
		boolean isTrusted = handler.verifySignature(paymentFlow.getChannelId(), data);

		if (!isTrusted) {
			logger.error("validate signature fail ：{}" + data);
			throw new ServiceException("validate signature fail");
		}

		logger.debug("2. check status(from cache if any)");
		PayStatusEnum paymentStatus = PayStatusCache.getInstance().get(paymentFlow.getPaySummaryId());
		if (PayStatusEnum.SUCCESS.equals(paymentStatus)) {
			logger.error("the transaction has already been success");
			// TODO to revert this payment
			throw new DuplicateException("the transaction has already been success");
		}
		PaymentSummaryEntity paymentSummary = paymentSummaryMapper.selectOne(paymentFlow.getPaySummaryId());
		if (PayStatusEnum.SUCCESS.equals(paymentSummary.getStatus())) {
			logger.error("the transaction has already been success");
			// TODO to revert this payment
			throw new DuplicateException("the transaction has already been success");
		}

		BankResponse bankResponse = handler.assembleResponse(data);
		RespCode responseCode = bankResponse.getRespCode();
		
		if (HazeRespCode.SUCCESS.equals(responseCode.getMappedRespCode())) {
			logger.debug("the payment {} is success", paymentFlow.getID());
			paymentFlowUpdate.setStatus(PayStatusEnum.SUCCESS);
		} else {
			paymentFlowUpdate.setStatus(PayStatusEnum.FAIL);
		}
		BankRespData bankRespData = bankResponse.getBankRespData();
		paymentFlowUpdate.setRawRespCode(responseCode.getRawRespCode());
		paymentFlowUpdate.setMappedRespCode(responseCode.getMappedRespCode().getCode());
		paymentFlowUpdate.setRawRespMsg(bankRespData.getRespMsg());
		paymentFlowUpdate.setReceiveDate(new Date());
		paymentFlowUpdate.setBankFlowId(bankRespData.getQid());
		logger.debug("3. update payment flow");
		paymentFlowMapper.update(paymentFlowUpdate);

		PaymentSummaryEntity paymentSummaryUpdate = new PaymentSummaryEntity();
		paymentSummaryUpdate.setID(paymentSummary.getID());
		paymentSummaryUpdate.setStatus(paymentFlowUpdate.getStatus());
		logger.debug("4. update payment summary");
		paymentSummaryMapper.update(paymentSummary);

		logger.debug("5. notify  paymentService the result");

		PayResponse payResponse = new PayResponse();
		payResponse.setSrcTxnId(paymentSummary.getSrcPayOrder().getSrcTxnId());
		payResponse.setPaySummaryId(paymentSummary.getID());
		payResponse.setRespCode(responseCode);
		payResponse.setPayAmount(paymentSummary.getBankAmount());
		payResponse.setPayCurrency(paymentSummary.getBankCurrency());
		paymentService.notifyPayResponse(payResponse);

		logger.debug("6. remove status cache");
		PayStatusCache.getInstance().remove(paymentFlow.getPaySummaryId());

		logger.debug("7. notify risk system");
		// TODO
	}

	@Override
	public void postSend(BankResponse response) throws ServiceException, RuntimeServiceException {

		logger.debug("do the post send ");
	}
}
