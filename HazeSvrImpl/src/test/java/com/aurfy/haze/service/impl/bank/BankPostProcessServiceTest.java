package com.aurfy.haze.service.impl.bank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.infra.ProductClassifierEnum;
import com.aurfy.haze.core.model.infra.mer.MerchantReference;
import com.aurfy.haze.core.model.payment.CredentialTypeEnum;
import com.aurfy.haze.core.model.payment.ExpiryDate;
import com.aurfy.haze.core.model.payment.OpRateClassifier;
import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.payment.PayStatusEnum;
import com.aurfy.haze.core.model.txn.BankOrder;
import com.aurfy.haze.core.model.txn.MerchantOrder;
import com.aurfy.haze.core.model.txn.SignMethod;
import com.aurfy.haze.core.model.txn.SrcAppTypeEnum;
import com.aurfy.haze.core.model.txn.SrcPayOrder;
import com.aurfy.haze.core.model.txn.TxnStatusEnum;
import com.aurfy.haze.core.model.txn.TxnTypeEnum;
import com.aurfy.haze.dao.configuration.channel.ChannelParameterMapper;
import com.aurfy.haze.dao.payment.PaymentFlowMapper;
import com.aurfy.haze.dao.payment.PaymentSummaryMapper;
import com.aurfy.haze.dao.txn.MerTxnMapper;
import com.aurfy.haze.entity.payment.PaymentFlowEntity;
import com.aurfy.haze.entity.payment.PaymentSummaryEntity;
import com.aurfy.haze.entity.txn.MerTxnEntity;
import com.aurfy.haze.service.api.bank.BankPostProcessService;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;

public class BankPostProcessServiceTest extends ServiceUnitTest {

	@Autowired
	private BankPostProcessService bankPostProcessService;

	@Autowired
	private PaymentFlowMapper pfMapper;
	@Autowired
	private PaymentSummaryMapper psMapper;
	@Autowired
	private ChannelParameterMapper channelParameterMapper;
	@Autowired
	private MerTxnMapper merTxnMapper;

	public PaymentFlowEntity newPaymentFlowEntity(String paymentSummaryId, String srcTxnId) {
		PaymentFlowEntity pf = new PaymentFlowEntity();
		pf.setID(SecurityUtils.UUID());
		pf.setPaySummaryId(paymentSummaryId);

		SrcPayOrder srcPayOrder = new SrcPayOrder();
		srcPayOrder.setSrcAppType(SrcAppTypeEnum.PGW);
		srcPayOrder.setSrcRefId("1246dfc2-519c-4cf0-83fe-605addf36b1d");
		srcPayOrder.setSrcTxnId(srcTxnId);
		srcPayOrder.setSrcTxnType(TxnTypeEnum.PURCHASE);
		srcPayOrder.setSrcOrderId(SecurityUtils.UUID());
		srcPayOrder.setSrcCurrency(Currency.CNY);
		srcPayOrder.setSrcAmount(new BigInteger("100000"));

		pf.setSrcPayOrder(srcPayOrder);
		pf.setChannelId("cc346097-9089-4f75-95c6-3ddc41267a40");
		pf.setProviderId(SecurityUtils.UUID());

		PayCredential payCredential = new PayCredential();
		payCredential.setVirtualAccount(RandomStringUtils.randomAlphanumeric(18));
		payCredential.setCardOrg(CardOrgEnum.UNIONPAY);
		payCredential.setToken(RandomStringUtils.randomAlphanumeric(100));
		payCredential.setEncryptedCardNo(RandomStringUtils.randomAlphanumeric(10));
		payCredential.setMaskCardNo("33465654756****34524");
		payCredential.setExpiryDate(new ExpiryDate("02", "15"));
		payCredential.setCellphone("15618387186");
		payCredential.setCardHolderFullName("赵骏");
		payCredential.setCardHolderFirstName("赵");
		payCredential.setCardHolderMiddleName("");
		payCredential.setCardHolderLastName("骏");
		payCredential.setCredentialType(CredentialTypeEnum.IDCard);
		payCredential.setCredentialNo(RandomStringUtils.randomNumeric(10));

		pf.setPayCredential(payCredential);

		BankOrder bankOrder = new BankOrder();
		bankOrder.setTxtType(TxnTypeEnum.PURCHASE.toString());
		bankOrder.setAcqCode("23456");
		bankOrder.setMerId(SecurityUtils.UUID());
		bankOrder.setMcc("sdhe");
		bankOrder.setOrderId(SecurityUtils.UUID());
		bankOrder.setTerminalId(SecurityUtils.UUID());
		bankOrder.setPort("8080");
		bankOrder.setAddressId("127.0.0.1");
		bankOrder.setAmount(new BigInteger("9876543215"));
		bankOrder.setCurrency(Currency.CNY);
		bankOrder.setMsg(RandomStringUtils.randomAlphanumeric(50));
		bankOrder.setExchangeRate(new BigDecimal("0.0015"));

		pf.setBankOrder(bankOrder);
		pf.setProviderId("0d3dae31-f20f-46ba-b94b-22fb9c71a937");
		pf.setSendUrl("http://www.baidu.com");
		pf.setSendDate(new Date());
		pf.setReceiveDate(new Date());
		pf.setBankFlowId(SecurityUtils.UUID());
		pf.setBankRespDate(new Date());// must set value
		pf.setBankAuthCode("1000");
		pf.setRawRespCode("5000");
		pf.setRawRespMsg(RandomStringUtils.randomAlphanumeric(50));
		pf.setMappedRespCode("8000");
		pf.setStatus(PayStatusEnum.INITIAL);
		pf.setReconciliationStatus(false);
		pf.setRefFlowId(SecurityUtils.UUID());
		pf.setComments(RandomStringUtils.randomAlphanumeric(50));
		pf.setCreateDate(new Date());
		pf.setUpdateDate(pf.getCreateDate());
		return pf;
	}

	public PaymentSummaryEntity newPaymentSummaryEntity(String srcTxnId) {
		PaymentSummaryEntity ps = new PaymentSummaryEntity();
		ps.setID(SecurityUtils.UUID());

		SrcPayOrder srcPayOrder = new SrcPayOrder();
		srcPayOrder.setSrcAppType(SrcAppTypeEnum.PGW);
		srcPayOrder.setSrcRefId("1246dfc2-519c-4cf0-83fe-605addf36b1d");
		srcPayOrder.setSrcTxnId(srcTxnId);
		srcPayOrder.setSrcTxnType(TxnTypeEnum.PURCHASE);
		srcPayOrder.setSrcOrderId(RandomStringUtils.randomAlphanumeric(20));
		srcPayOrder.setSrcCurrency(Currency.CNY);
		srcPayOrder.setSrcAmount(new BigInteger("100000"));
		srcPayOrder.setSrcCurrency(Currency.CNY);
		srcPayOrder.setSrcTxnDate(new Date());
		srcPayOrder.setSrcTxnDay(srcPayOrder.getSrcTxnDate());

		ps.setSrcPayOrder(srcPayOrder);
		ps.setOpRateClassifier(OpRateClassifier.INNER_CHARGE);
		ps.setOpAmount(new BigInteger("12394567823434567"));
		ps.setOpCurrency(Currency.CNY);

		ps.setBankAmount(new BigInteger("9876543215"));
		ps.setBankCurrency(Currency.USD);
		ps.setIssueBank("ICBC");

		PayCredential payCredential = new PayCredential();
		payCredential.setCardOrg(CardOrgEnum.UNIONPAY);
		payCredential.setEncryptedCardNo("dsagfhhjtyujerythbjuktuyytrhfbserqwerwrtths");
		payCredential.setMaskCardNo("33465654756****34524");

		ps.setPayCredential(payCredential);
		ps.setIs3d(false);
		ps.setIsDcc(false);
		ps.setRefFlowId(SecurityUtils.UUID());
		ps.setCompleteDate(new Date());
		ps.setExchangeRate(new BigDecimal("12345678.5398"));// 必须是小数点后4位，否则数据库自动补零
		ps.setRemainAmount(new BigInteger("45678734243242"));
		ps.setRemainCurrency(Currency.BSD);
		ps.setRefSummaryId(SecurityUtils.UUID());
		ps.setStatus(PayStatusEnum.INITIAL);
		ps.setDeliveryApprovalId(RandomStringUtils.randomAlphanumeric(10));
		ps.setDeliveryDate(new Date());
		ps.setOwnerId(SecurityUtils.UUID());
		ps.setCreateDate(new Date());
		ps.setUpdateDate(ps.getCreateDate());
		return ps;
	}

	public MerTxnEntity newMerTxnEntity() {
		MerTxnEntity merTxn = new MerTxnEntity();
		merTxn.setID(SecurityUtils.UUID());
		MerchantReference merRef = new MerchantReference();
		merRef.setMerId("1246dfc2-519c-4cf0-83fe-605addf36b1f");
		merRef.setMerCode("99999999999999999999999999999999");
		MerchantOrder merOrder = new MerchantOrder();
		merOrder.setMerRef(merRef);
		merOrder.setTxnType(TxnTypeEnum.PURCHASE);
		merOrder.setCurrency(Currency.CNY);
		merOrder.setAmount(new BigInteger("100000"));
		merOrder.setOrderId(RandomStringUtils.randomAlphanumeric(20));
		merOrder.setBillDesc(RandomStringUtils.randomAlphanumeric(50));
		merOrder.setOrderDetail(RandomStringUtils.randomAlphanumeric(10));
		merOrder.setOrderDate(new Date());
		merOrder.setOrderTimezone("08");
		merOrder.setOrderTimeout(2000);
		merOrder.setCustomerIp("127.0.0.1");
		merOrder.setPayTimeout(1000);
		merOrder.setPreferredBank("ICBC");
		merOrder.setBrowserNotifyUrl("www.baidu.com");
		merOrder.setServerNotifyUrl("www.baidu.com");
		merTxn.setMerOrder(merOrder);
		merTxn.setTxnStatus(TxnStatusEnum.NEW);
		merTxn.setOwnerId(SecurityUtils.UUID());
		merTxn.setProductClassifier(ProductClassifierEnum.ExpressPay);
		merTxn.setTerminalId(SecurityUtils.UUID());
		merTxn.setProtocolVer("1.0.0");
		merTxn.setCharsetEncoding(null);
		merTxn.setSignMethod(SignMethod.SHA3);

		merTxn.setSettleRefId(SecurityUtils.UUID());
		merTxn.setPayRefId(SecurityUtils.UUID());
		merTxn.setCreateDate(new Date());
		merTxn.setUpdateDate(merTxn.getCreateDate());
		return merTxn;
	}

	@Test
	public void testOnCallback() throws RuntimeServiceException, ServiceException {

		MerTxnEntity merTxn = newMerTxnEntity();
		merTxnMapper.insert(merTxn);
		PaymentSummaryEntity paymentSummary = newPaymentSummaryEntity(merTxn.getID());
		psMapper.insert(paymentSummary);
		PaymentFlowEntity paymentFlow = newPaymentFlowEntity(paymentSummary.getID(), merTxn.getID());
		pfMapper.insert(paymentFlow);

		Map<String, String> data = new HashMap<String, String>();
		data.put("orderId", paymentFlow.getBankOrder().getOrderId());
		data.put("respCode", "00");
		data.put("respMsg", "succcess");
		data.put("qid", "123456");
		data.put("signMethod", "MD5");

		data.put("signature", "b50c6a4b5d1d65cb97e4b04c54a5b598");

		bankPostProcessService.onCallback(paymentFlow.getBankOrder().getOrderId(), data);
	}

}
