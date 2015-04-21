package com.aurfy.haze.dao.payment;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.payment.CredentialTypeEnum;
import com.aurfy.haze.core.model.payment.ExpiryDate;
import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.payment.PayStatusEnum;
import com.aurfy.haze.core.model.txn.BankOrder;
import com.aurfy.haze.core.model.txn.SrcAppTypeEnum;
import com.aurfy.haze.core.model.txn.SrcPayOrder;
import com.aurfy.haze.core.model.txn.TxnTypeEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.payment.PaymentFlowEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class PaymentFlowMapperTest extends PersistUnitTest {

	@Autowired
	private PaymentFlowMapper pfMapper;

	public PaymentFlowEntity newPaymentFlowEntity() {
		PaymentFlowEntity pf = new PaymentFlowEntity();
		pf.setID(SecurityUtils.UUID());
		pf.setPaySummaryId(SecurityUtils.UUID());

		SrcPayOrder srcPayOrder = new SrcPayOrder();
		srcPayOrder.setSrcAppType(SrcAppTypeEnum.PGW);
		srcPayOrder.setSrcRefId("1246dfc2-519c-4cf0-83fe-605addf36b1d");
		srcPayOrder.setSrcTxnId(SecurityUtils.UUID());
		srcPayOrder.setSrcTxnType(TxnTypeEnum.PURCHASE);
		srcPayOrder.setSrcOrderId(SecurityUtils.UUID());
		srcPayOrder.setSrcCurrency(Currency.CNY);
		srcPayOrder.setSrcAmount(new BigInteger("100000"));
		
		pf.setSrcPayOrder(srcPayOrder);
		pf.setChannelId(SecurityUtils.UUID());
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
		bankOrder.setCurrency(Currency.USD);
		bankOrder.setMsg(RandomStringUtils.randomAlphanumeric(50));
		bankOrder.setExchangeRate(new BigDecimal("0.0015"));

		pf.setBankOrder(bankOrder);
		pf.setSendUrl("http://www.baidu.com");
		pf.setSendDate(new Date());
		pf.setReceiveDate(new Date());
		pf.setBankFlowId(SecurityUtils.UUID());
		pf.setBankRespDate(new Date());//must set value
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

	public PaymentFlowEntity getPaymentFlowEntity() {
		PaymentFlowEntity pf = newPaymentFlowEntity();
		int count = pfMapper.insert(pf);
		assertTrue(count == 1);
		return pf;
	}

	@Test
	@Transactional
	public void testInsert() {
		getPaymentFlowEntity();
	}
	
	@Test
	@Transactional
	public void testSelectOne(){
		PaymentFlowEntity pf = getPaymentFlowEntity();
		PaymentFlowEntity pf2 = pfMapper.selectOne(pf.getID());
		assertTrue(pf.equals(pf2));
	}

}
