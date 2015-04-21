package com.aurfy.haze.dao.payment;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.infra.mer.MerchantReference;
import com.aurfy.haze.core.model.payment.OpRateClassifier;
import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.payment.PayStatusEnum;
import com.aurfy.haze.core.model.txn.BankOrder;
import com.aurfy.haze.core.model.txn.MerchantOrder;
import com.aurfy.haze.core.model.txn.SrcAppTypeEnum;
import com.aurfy.haze.core.model.txn.SrcPayOrder;
import com.aurfy.haze.core.model.txn.TxnTypeEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.payment.PaymentSummaryEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class PaymentSummaryMapperTest extends PersistUnitTest {

	@Autowired
	private PaymentSummaryMapper psMapper;
	
	public PaymentSummaryEntity newPaymentSummaryEntity(){
		PaymentSummaryEntity ps = new PaymentSummaryEntity();
		ps.setID(SecurityUtils.UUID());
		
		SrcPayOrder srcPayOrder = new SrcPayOrder();
		srcPayOrder.setSrcAppType(SrcAppTypeEnum.PGW);
		srcPayOrder.setSrcRefId("1246dfc2-519c-4cf0-83fe-605addf36b1d");
		srcPayOrder.setSrcTxnId(SecurityUtils.UUID());
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
		ps.setExchangeRate(new BigDecimal("12345678.5398"));//必须是小数点后4位，否则数据库自动补零
		ps.setRemainAmount(new BigInteger("45678734243242"));
		ps.setRemainCurrency(Currency.BSD);
		ps.setRefSummaryId(SecurityUtils.UUID());
		ps.setStatus(PayStatusEnum.SUCCESS);
		ps.setDeliveryApprovalId(RandomStringUtils.randomAlphanumeric(10));
		ps.setDeliveryDate(new Date());
		ps.setOwnerId(SecurityUtils.UUID());
		ps.setCreateDate(new Date());
		ps.setUpdateDate(ps.getCreateDate());
		return ps;
	}
	
	public PaymentSummaryEntity getPaymentSummaryEntity(){
		PaymentSummaryEntity ps = newPaymentSummaryEntity();
		int count = psMapper.insert(ps);
		assertTrue(count == 1);
		return ps;
	}
	
	@Test
	@Transactional
	public void TestInsert(){
		getPaymentSummaryEntity();
	}
	
	@Test
	@Transactional
	public void TestSelectOne(){
		PaymentSummaryEntity ps = getPaymentSummaryEntity();
		PaymentSummaryEntity ps2 = psMapper.selectOne(ps.getID());
		assertTrue(ps.equals(ps2));
	}
	
	
}
