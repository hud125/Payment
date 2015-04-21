package com.aurfy.haze.service.impl.bank.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.conf.HazeDefaultConfig;
import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.payment.HazeRespCode;
import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.payment.PayStatusEnum;
import com.aurfy.haze.core.model.payment.RespCode;
import com.aurfy.haze.core.model.txn.BankOrder;
import com.aurfy.haze.core.model.txn.SrcPayOrder;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.dao.configuration.channel.ChannelParameterMapper;
import com.aurfy.haze.dao.payment.PaymentFlowMapper;
import com.aurfy.haze.dao.spring.SpringContext;
import com.aurfy.haze.entity.configuration.channel.ChannelParameterEntity;
import com.aurfy.haze.entity.payment.PaymentFlowEntity;
import com.aurfy.haze.service.bean.bank.BankRespData;
import com.aurfy.haze.service.bean.bank.BankResponse;
import com.aurfy.haze.service.bean.bank.ExpressPayResponse;
import com.aurfy.haze.service.bean.configuration.channel.ChannelParameterBean;
import com.aurfy.haze.service.impl.bank.PayStatusCache;
import com.aurfy.haze.utils.DateUtils;
import com.aurfy.haze.utils.StringUtils;
import com.unionpay.upop.sdk.PayRequest;
import com.unionpay.upop.sdk.SdkConf;
import com.unionpay.upop.sdk.enums.Service;
import com.unionpay.upop.sdk.util.PayUtils;

public class UnionPayExpressHandler extends BaseBankHandler implements BankHandler {

	private static final Logger logger = LoggerFactory.getLogger(UnionPayExpressHandler.class);

	private static final String UNION_PAY_EXPRESS_HANDLER_FILE = "unionpay/UnionPayExpressHandler.properties";

	private static final String PURCHASE = "01";
	private static final String signMethod = "MD5";
	private static final String version = "1.0.0";
	private static final String DEFAULT_ENCODING = "UTF-8";
	private static final String transTimeout = "600000";
	private static final String customerIp = "127.0.0.1";

	public UnionPayExpressHandler() {

	}

	@Override
	protected String getSignatureFieldName() {
		return "signature";
	}

	@Override
	protected void fillParameters() {
		PayCredential payCredential = getRequest().getPayCredential();
		SrcPayOrder payOrder = getRequest().getPayOrder();
		String bankOrderId = generateBankOrderId();
		getParameters().put("orderNumber", bankOrderId);
		getParameters().put("orderAmount", payOrder.getSrcAmount().toString());
		String merReserved = "{cardNumber=" + payCredential.getCardNo() + "&phoneNumber="
				+ payCredential.getCellphone() + "&cardCvn2=" + payCredential.getCvv() + "&cardExpire="
				+ payCredential.getExpiryDate().toYearMonth() + "}";
		getParameters().put("merReserved", merReserved);

		getParameters().put("transType", PURCHASE);
		getParameters().put("version", version);
		getParameters().put("signMethod", signMethod);
		getParameters().put("charset", DEFAULT_ENCODING);
		getParameters().put("transTimeout", transTimeout);
		getParameters().put("customerIp", customerIp);

		List<ChannelParameterBean> channelParams = getChannel().getChannelParams();
		for (ChannelParameterBean channelParamsBean : channelParams) {
			if (StringUtils.equals(channelParamsBean.getConfigKey(), KEY_MER_ID)) {
				getParameters().put("merId", channelParamsBean.getConfigValue());
			} else if (StringUtils.equals(channelParamsBean.getConfigKey(), KEY_MCC)) {
				getParameters().put("merCode", channelParamsBean.getConfigValue());
			} else if (StringUtils.equals(channelParamsBean.getConfigKey(), "merAbbr")) {
				getParameters().put("merAbbr", channelParamsBean.getConfigValue());
			} else if (StringUtils.equals(channelParamsBean.getConfigKey(), "acqCode")) {
				getParameters().put("acqCode", channelParamsBean.getConfigValue());
			} else if (StringUtils.equals(channelParamsBean.getConfigKey(), "securityKey")) {
				getParameters().put("securityKey", channelParamsBean.getConfigValue());
			} else if (StringUtils.equals(channelParamsBean.getConfigKey(), "backEndUrl")) {
				getParameters().put("backEndUrl", channelParamsBean.getConfigValue() + "/" + bankOrderId);
			} else if (StringUtils.equals(channelParamsBean.getConfigKey(), KEY_CURRENCY)) {
				getParameters()
						.put("orderCurrency", Currency.parseByName(channelParamsBean.getConfigValue()).getCode());
			}
			// else if(StringUtils.equals(channelParamsBean.getConfigKey(),KEY_ADDRESS))
			// {
			// bankOrder.setAddressId(channelParamsBean.getConfigValue());
			// }
			// else if(StringUtils.equals(channelParamsBean.getConfigKey(),KEY_AMOUT))
			// {
			// bankOrder.setAmount(channelParamsBean.getConfigValue());
			// }
		}
	}

	@Override
	protected String calculateSignature() {
		return null;
	}

	@Override
	protected BankOrder createBankOrder() {

		SrcPayOrder payOrder = getRequest().getPayOrder();
		BankOrder bankOrder = new BankOrder();
		bankOrder.setOrderId(getParameters().get("orderNumber"));
		bankOrder.setTxtType(getParameters().get("transType"));
		bankOrder.setMerId(getParameters().get("merId"));
		bankOrder.setMcc(getParameters().get("merCode"));
		bankOrder.setCurrency(Currency.parseByCode(getParameters().get("orderCurrency")));
		bankOrder.setAmount(payOrder.getSrcAmount());

		return bankOrder;
	}

	@Override
	protected BankResponse processPurchase() {
		PayRequest payRequest = new PayRequest();
		payRequest.setOrderCurrency(getParameters().get("orderCurrency"));
		payRequest.setOrderNumber(getParameters().get("orderNumber"));
		Date sendTime = new Date();
		payRequest.setOrderTime(DateUtils.formatDate(sendTime, "yyyyMMddHHmmss"));
		payRequest.setOrderAmount(getParameters().get("orderAmount"));
		payRequest.setAcqCode(getParameters().get("acqCode"));
		payRequest.setMerId(getParameters().get("merId"));
		payRequest.setMerCode(getParameters().get("merCode"));
		payRequest.setMerAbbr(getParameters().get("merAbbr"));
		payRequest.setMerReserved(getParameters().get("merReserved"));
		payRequest.setTransType(getParameters().get("transType"));
		payRequest.setBackEndUrl(getParameters().get("backEndUrl"));
		payRequest.setSecurityKey(getParameters().get("securityKey"));
		payRequest.setSignMethod(signMethod);
		payRequest.setVersion(version);
		payRequest.setCharset(DEFAULT_ENCODING);
		payRequest.setTransTimeout(transTimeout);
		// TODO change?
		payRequest.setCustomerIp(customerIp);

		String req = PayUtils.createPostDataForBackTrans(Service.BS_PAY.getParamArray(), payRequest);
		preSend(getParameters().get("orderNumber"), req, sendTime, Service.BS_PAY.getUrl());
		postRequest(getParameters().get("orderNumber"), Service.BS_PAY, payRequest, req);

		BankResponse bankResponse = new ExpressPayResponse();

		return bankResponse;
	}

	public void preSend(String orderId, String msg, Date sendDate, String sendUrl) {

		PaymentFlowMapper paymentFlowMapper = (PaymentFlowMapper) SpringContext.getBean(AOP_MAPPER.PAYMENT_FLOW_MAPPER);
		PaymentFlowEntity paymentFlow = paymentFlowMapper.selectByBankOrderId(orderId);
		PaymentFlowEntity paymentFlowUpdate = new PaymentFlowEntity();
		paymentFlowUpdate.setID(paymentFlow.getID());
		paymentFlowUpdate.setSendDate(sendDate);
		paymentFlowUpdate.setSendUrl(sendUrl);
		paymentFlowUpdate.setStatus(PayStatusEnum.PENDING);
		BankOrder bankOrder = new BankOrder();
		bankOrder.setMsg(msg);
		paymentFlowUpdate.setBankOrder(bankOrder);
		paymentFlowMapper.update(paymentFlowUpdate);

		logger.debug("put the status ");
		PayStatusCache.getInstance().put(getPaySummaryId(), PayStatusEnum.PENDING);

		logger.debug("do the pre send ");
	}

	/**
	 * 向银联发送后续交易请求
	 * 
	 */
	private void postRequest(String orderId, Service service, PayRequest payRequest, String req) {

		boolean isSuccess = false;
		String res = PayUtils.doPostQueryCmd(service.getUrl(), req, payRequest.getCharset());
		String respCode = null;
		String respMsg = null;
		if (PayUtils.isNotBlank(res)) {
			Map<String, String> response = PayUtils.getResponse(res);
			if (PayUtils.verifySignature(response, payRequest.getSecurityKey(), payRequest.getCharset())) {// 验证签名

				respCode = response.get("respCode");
				respMsg = response.get("respMsg");

				logger.debug(">>>respCode : " + respCode);
				logger.debug(">>>respMsg : " + respMsg);

				if ("00".equals(respCode)) {
					logger.debug("bank has accepted");
					isSuccess = true;
				} else {
					logger.error(respCode + ": " + respMsg);
				}

			} else {

				respMsg = "验证签名失败";
				logger.error("验证签名失败：" + response);
			}
		} else {
			respMsg = "报文格式为空";
			logger.error("报文格式为空");
		}

		if (!isSuccess) {
			PaymentFlowMapper paymentFlowMapper = (PaymentFlowMapper) SpringContext
					.getBean(AOP_MAPPER.PAYMENT_FLOW_MAPPER);
			PaymentFlowEntity paymentFlow = paymentFlowMapper.selectByBankOrderId(orderId);
			PaymentFlowEntity paymentFlowUpdate = new PaymentFlowEntity();
			paymentFlowUpdate.setID(paymentFlow.getID());
			paymentFlowUpdate.setBankRespDate(new Date());
			paymentFlowUpdate.setRawRespCode(respCode);
			paymentFlowUpdate.setRawRespMsg(respMsg);
			paymentFlowUpdate.setStatus(PayStatusEnum.FAIL);
			paymentFlowMapper.update(paymentFlowUpdate);
		}
	}

	@Override
	protected String generateBankOrderId() {
		return "union" + System.currentTimeMillis();
	}

	@Override
	public BankResponse assembleResponse(Map<String, String> data) {
		ExpressPayResponse bankResponse = new ExpressPayResponse();
		BankOrder bankOrder = new BankOrder();
		bankOrder.setOrderId(data.get("orderNumber"));
		bankResponse.setBankOrder(bankOrder);
		BankRespData bankRespData = new BankRespData();
		bankRespData.setQid(data.get("qid"));
		bankRespData.setRespMsg(data.get("dataMsg"));
		bankResponse.setBankRespData(bankRespData);
		RespCode respCode = new RespCode();
		respCode.setRawRespCode(data.get("respCode"));
		HazeRespCode hazeRespCode = HazeRespCode.FAILED;
		try {
			Properties instance = HazeDefaultConfig.getProperties(UNION_PAY_EXPRESS_HANDLER_FILE);
			String mappedCode = instance.getProperty("response.code" + "." + data.get("respCode"));
			hazeRespCode = HazeRespCode.getHazeRespCode(mappedCode);
		} catch (Exception e) {
			logger.error("have not found the code : " + data.get("respCode"));
			hazeRespCode = HazeRespCode.FAILED;
		}

		respCode.setMappedRespCode(hazeRespCode);
		bankResponse.setRespCode(respCode);

		return bankResponse;
	}

	@Override
	public boolean verifySignature(String channelId, Map<String, String> map) {

		Map<String, String> unionMap = new TreeMap<String, String>();
		for (int i = 0; i < SdkConf.notifyVo.length; i++) {
			unionMap.put(SdkConf.notifyVo[i], map.get(SdkConf.notifyVo[i]));
		}
		unionMap.put("signMethod", signMethod);
		unionMap.put("signature", map.get("signature"));

		ChannelParameterMapper channelParameterMapper = (ChannelParameterMapper) SpringContext
				.getBean(AOP_MAPPER.CHANNEL_PARAMETER_MAPPER);
		ChannelParameterEntity channelParam = channelParameterMapper.selectByKeyAndChannelId("securityKey", channelId);
		String securityKey = channelParam.getConfigValue();

		logger.debug("the parameter in verifySignature: {}", unionMap.toString());
		return PayUtils.verifySignature(unionMap, securityKey, DEFAULT_ENCODING);
	}
}
