package com.aurfy.haze.service.impl.bank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.bank.EBankForm;
import com.aurfy.haze.core.model.bank.ThreeDInfo;
import com.aurfy.haze.core.model.bank.Token;
import com.aurfy.haze.core.model.payment.OpRateClassifier;
import com.aurfy.haze.core.model.payment.PayStatusEnum;
import com.aurfy.haze.core.model.payment.RespCode;
import com.aurfy.haze.core.model.txn.BankOrder;
import com.aurfy.haze.core.model.txn.SrcPayOrder;
import com.aurfy.haze.dao.payment.PaymentFlowMapper;
import com.aurfy.haze.dao.payment.PaymentSummaryMapper;
import com.aurfy.haze.entity.payment.PaymentFlowEntity;
import com.aurfy.haze.entity.payment.PaymentSummaryEntity;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.bank.BankService;
import com.aurfy.haze.service.bean.bank.ExpressPayRequest;
import com.aurfy.haze.service.bean.bank.ProcessReference;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.bean.payment.PayResponse;
import com.aurfy.haze.service.bean.todo.CreateFormRequest;
import com.aurfy.haze.service.bean.todo.CreateTokenRequest;
import com.aurfy.haze.service.bean.todo.ThreeDInfoRequest;
import com.aurfy.haze.service.bean.todo.ThreeDRequest;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.exceptions.DuplicateException;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.BaseHazeService;
import com.aurfy.haze.service.impl.bank.handler.BankHandler;
import com.aurfy.haze.service.impl.bank.handler.HandlerHelper;
import com.aurfy.haze.service.impl.thread.ThreadPoolProvider;
import com.aurfy.haze.utils.SecurityUtils;

@Service(AOP_NAME.BANK_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BankServiceImpl extends BaseHazeService implements BankService {

	private static final Logger logger = LoggerFactory.getLogger(BankServiceImpl.class);

	@Autowired
	private CRUDService crudService;
	@Autowired
	private PaymentFlowMapper paymentFlowMapper;
	@Autowired
	private PaymentSummaryMapper paymentSummaryMapper;

	@PostConstruct
	public void init() {
	}

	@Override
	public Token createToken(CreateTokenRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EBankForm createForm(CreateFormRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ThreeDInfo create3D(ThreeDInfoRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProcessReference do3D(ThreeDRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	private void loadPaymentSummary(PaymentSummaryEntity paymentSummary, BankOrder bankOrder,
			ExpressPayRequest request, ChannelBean channel) {
		SrcPayOrder srcPayOrder = request.getPayOrder();

		paymentSummary.setOpAmount(bankOrder.getAmount());
		paymentSummary.setOpCurrency(srcPayOrder.getSrcCurrency());
		paymentSummary.setSrcPayOrder(srcPayOrder);
		paymentSummary.setPayCredential(request.getPayCredential());
		paymentSummary.setIs3d(channel.isSupport3D());
		paymentSummary.setIsDcc(channel.isSupportDCC());
		paymentSummary.setBankAmount(bankOrder.getAmount());
		paymentSummary.setBankCurrency(srcPayOrder.getSrcCurrency());
		// TODO from where to get the exchangeRate
		paymentSummary.setOpRateClassifier(OpRateClassifier.INNER_CHARGE);
		// paymentSummary.setIssueBank("ICBC");
		paymentSummary.setExchangeRate(new BigDecimal("12345678.5398"));// 必须是小数点后4位，否则数据库自动补零
		paymentSummary.setRemainAmount(new BigInteger("45678734243242"));
		paymentSummary.setRemainCurrency(Currency.CNY);
		// paymentSummary.setDeliveryApprovalId(RandomStringUtils.randomAlphanumeric(10));
		// paymentSummary.setDeliveryDate(new Date());
		paymentSummary.setOwnerId(SecurityUtils.UUID());
		paymentSummary.setStatus(PayStatusEnum.INITIAL);
		paymentSummary.setRefSummaryId(paymentSummary.getID());
	}

	@Transactional
	@Override
	public ProcessReference doExpressPay(ExpressPayRequest request, ChannelBean channel)
			throws RuntimeServiceException, ServiceException {
		logger.debug("1. get Handler by channel provider class");
		BankHandler handler = HandlerHelper.getHandler(channel.getProviderClassifier());
		String paySummaryId = SecurityUtils.UUID();
		String paymentFlowId = SecurityUtils.UUID();
		handler.init(request, channel, paySummaryId);
		logger.debug("2. use handler to validate signature, return bank order");
		BankOrder bankOrder = handler.compose();
		logger.debug("3. create or update pay summary");
		SrcPayOrder srcPayOrder = request.getPayOrder();
		
		PaymentSummaryEntity paymentSummary = paymentSummaryMapper.selectOne(srcPayOrder.getSrcTxnId());
		if (paymentSummary == null) {
			paymentSummary = new PaymentSummaryEntity();
			paymentSummary.setID(paySummaryId);
			paymentSummary.setRefFlowId(paymentFlowId);
			loadPaymentSummary(paymentSummary, bankOrder, request, channel);
			paymentSummary.setCreateDate(new Date());
			paymentSummary.setUpdateDate(paymentSummary.getCreateDate());
			try{
				paymentSummaryMapper.insert(paymentSummary);
			} catch(DuplicateKeyException e){
				//同一个商户，同一天，订单号不能重复
				logger.error("In one day, a merchant's order can't be summit more than once!", e);
				throw new DuplicateException("In one day, a merchant's order can't be summit more than once!", e);
			}
		} else {

			if (paymentSummary.getStatus().equals(PayStatusEnum.SUCCESS)) {
				logger.error("the transaction has already been success");
				throw new DuplicateException("the transaction has already been success");
			}
			paymentSummary.setRefFlowId(paymentFlowId);
			loadPaymentSummary(paymentSummary, bankOrder, request, channel);
			paymentSummary.setUpdateDate(new Date());
			paymentSummaryMapper.update(paymentSummary);
		}

		logger.debug("4. create pay flow get fowId");
		PaymentFlowEntity flow = new PaymentFlowEntity();
		flow.setID(paymentFlowId);
		flow.setPaySummaryId(paymentSummary.getID());
		flow.setStatus(PayStatusEnum.INITIAL);
		flow.setBankOrder(bankOrder);
		flow.setSrcPayOrder(srcPayOrder);
		flow.setPayCredential(request.getPayCredential());
		flow.setChannelId(channel.getID());
		flow.setProviderId(channel.getChannelProviderId());
		flow.setRefFlowId(paymentFlowId);
		paymentFlowMapper.insert(flow);
		String processId = flow.getID();
		logger.debug("5. set task into the pool");
		BankingTask bt = new BankingTask();
		bt.setHandler(handler);
		ThreadPoolProvider.getThreadPool("bankSender").execute(new ConsumerThread(bt));
		logger.debug("6. return ProcessReference");
		ProcessReference reference = new ProcessReference();
		reference.setProcessId(processId);

		return reference;
	}
	
	@Override
	public PayResponse queryResult(ProcessReference reference) throws RuntimeServiceException, ServiceException {

		PaymentFlowEntity paymentFlow = paymentFlowMapper.selectOne(reference.getProcessId());

		PayResponse payResponse = new PayResponse();
		payResponse.setSrcTxnId(paymentFlow.getSrcPayOrder().getSrcTxnId());
		payResponse.setPaySummaryId(paymentFlow.getPaySummaryId());
		RespCode respCode = new RespCode();
		respCode.setRawRespCode(paymentFlow.getRawRespCode());
		// respCode.setMappedRespCode(paymentFlow.getMappedRespCode());
		payResponse.setRespCode(respCode);
		payResponse.setPayAmount(paymentFlow.getBankOrder().getAmount());
		payResponse.setPayCurrency(paymentFlow.getBankOrder().getCurrency());
		payResponse.setStatus(paymentFlow.getStatus());

		return payResponse;
	}

	@Override
	public PayResponse queryResultByOrderId(String orderId){
		SrcPayOrder spo = new SrcPayOrder();
		spo.setSrcOrderId(orderId);
		PaymentSummaryEntity paymentSummary = new PaymentSummaryEntity();
		paymentSummary.setSrcPayOrder(spo);
		List<PaymentSummaryEntity> paymentSummarys = (List<PaymentSummaryEntity>) paymentSummaryMapper.selectBy(paymentSummary, 0, 0);
		if(paymentSummarys.size() <= 0){
			logger.error("not found transaction result by order id.");
			throw new RuntimeServiceException("not found transaction result by order id.");
			//过滤，而且只有查找时添加上orderdate等信息？？
		}
		ProcessReference reference = new ProcessReference();
		reference.setProcessId(paymentSummarys.get(0).getRefFlowId());
		try {
			return this.queryResult(reference);
		} catch (RuntimeServiceException | ServiceException e) {
			logger.error("query transaction result by order id failed.", e);
			throw new RuntimeServiceException("query transaction result by order id failed.", e);
		}
	}
	
}
