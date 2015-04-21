package com.aurfy.haze.dao.settlement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.infra.mer.MerchantReference;
import com.aurfy.haze.core.model.payment.OpRateClassifier;
import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.settlement.ClearingStatusEnum;
import com.aurfy.haze.core.model.settlement.DepositSettleStatusEnum;
import com.aurfy.haze.core.model.settlement.FreezePolicyEnum;
import com.aurfy.haze.core.model.settlement.SettleConditionEnum;
import com.aurfy.haze.core.model.settlement.SettleStatusEnum;
import com.aurfy.haze.core.model.txn.BankOrder;
import com.aurfy.haze.core.model.txn.MerchantOrder;
import com.aurfy.haze.core.model.txn.TxnTypeEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.common.HelpQAEntity;
import com.aurfy.haze.entity.settlement.SettleFlowEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class SettleFlowMapperTest extends PersistUnitTest {

	@Autowired
	private SettleFlowMapper sfMapper;
	
	public SettleFlowEntity newSettleFlow() {
		SettleFlowEntity sf = new SettleFlowEntity();
		sf.setID(SecurityUtils.UUID());
		
		MerchantReference merRef = new MerchantReference();
		merRef.setMerId("1246dfc2-519c-4cf0-83fe-605addf36b1d");
		merRef.setAgentId(SecurityUtils.UUID());
		merRef.setSalesId(SecurityUtils.UUID());
		merRef.setPspId(SecurityUtils.UUID());
		
		MerchantOrder merOrder = new MerchantOrder();
		merOrder.setMerRef(merRef);
		merOrder.setOrderId(RandomStringUtils.randomAlphanumeric(20));
		merOrder.setCurrency(Currency.CNY);
		merOrder.setAmount(new BigInteger("100000"));
		sf.setMerOrder(merOrder);
		sf.setPaymentSummaryId(SecurityUtils.UUID());
		sf.setPayFlowId(SecurityUtils.UUID());
		sf.setChannelId(SecurityUtils.UUID());
		sf.setAcquirerId(SecurityUtils.UUID());
		
		PayCredential payCredential = new PayCredential();
		payCredential.setCardOrg(CardOrgEnum.UNIONPAY);
		payCredential.setEncryptedCardNo(RandomStringUtils.randomAlphanumeric(10));
		payCredential.setMaskCardNo("33465654756****34524");
		payCredential.setVirtualAccount(RandomStringUtils.randomAlphanumeric(18));
		sf.setPayCredential(payCredential);
		sf.setTxnId(SecurityUtils.UUID());
		
		BankOrder bankOrder = new BankOrder();
		bankOrder.setTxtType(TxnTypeEnum.PURCHASE.toString());
		bankOrder.setMerId(SecurityUtils.UUID());
		bankOrder.setOrderId(SecurityUtils.UUID());
		bankOrder.setExchangeRate(new BigDecimal("0.0115"));//数据库为decimal(18,4)	
		bankOrder.setAmount(new BigInteger("9876543215"));
		bankOrder.setCurrency(Currency.USD);
		sf.setBankOrder(bankOrder);
		
		sf.setBankFlowId(RandomStringUtils.randomAlphanumeric(20));
		sf.setPayDate(new Date());
		sf.setClearingExchangeRate(new BigDecimal("0.0050"));//数据库为decimal(18,4)	
		sf.setClearingAmount(new BigInteger("10000"));
		sf.setClearingCurrency(Currency.CNY);
		sf.setOpRateClassifier(OpRateClassifier.INNER_CHARGE);
		sf.setOpAmount(new BigInteger("45566"));
		sf.setDepositAmount(new BigInteger("234566"));
		sf.setScheduleBatchId(SecurityUtils.UUID());
		sf.setClearingDate(new Date());
		sf.setClearingStatus(ClearingStatusEnum.REQUIRED);
		sf.setFreezePolicy(FreezePolicyEnum.AUTO_FREEZE);
		sf.setSettleDate(new Date());
		sf.setSettleStatus(SettleStatusEnum.REQUIRED);
		sf.setSettleCondition(SettleConditionEnum.BY_PERIOD);
		sf.setDeliveryDate(new Date());
		sf.setDepositScheduleBatchId(SecurityUtils.UUID());
		sf.setDepositSettleDate(new Date());
		sf.setDepositSettleStatus(DepositSettleStatusEnum.SETTLED);
		sf.setComments(RandomStringUtils.randomAlphanumeric(50));
		sf.setCreateDate(new Date());
		sf.setUpdateDate(sf.getCreateDate());
		return sf;
	}
	
	public SettleFlowEntity getSettleFlow(){
		SettleFlowEntity sf = newSettleFlow();
		String oldId = sf.getID();
		int count = sfMapper.insert(sf);
		assertTrue(count == 1);
		String newId = sf.getID();
		assertTrue(oldId.equals(newId));
		return sf;
	}
	
	
	/*@Test
	@Transactional
	public void testInsert() {
		getSettleFlow();
	}
	
	@Test
	@Transactional
	public void testSelectOne() {
		SettleFlowEntity sf = getSettleFlow();
		SettleFlowEntity sf2 = sfMapper.selectOne(sf.getID());
		assertTrue(sf2 != null);
		assertTrue(sf.equals(sf2));
	}
	
	@Test
	@Transactional
	public void testRetrieveAll() {
		int oldSize = sfMapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			getSettleFlow();
		}
		assertTrue(oldSize + randomSize == sfMapper.selectAll().size());
	}
	
	@Test
	@Transactional
	public void testCountBy() {
		SettleFlowEntity sf = getSettleFlow();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			getSettleFlow();
		}
		assertNotNull(sfMapper.countBy(sf));
	}
	
	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = sfMapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			getSettleFlow();
		}
		assertTrue(oldSize + randomSize == sfMapper.countAll());
	}
	
	@Test
	@Transactional
	public void testUpdate() {
		SettleFlowEntity sf = getSettleFlow();
		SettleFlowEntity sf2 = new SettleFlowEntity();
		sf2.setID(sf.getID());
		sf2.setUpdateDate(new Date());
		int index = sfMapper.update(sf2);
		assertTrue(index == 1);
		SettleFlowEntity sf3 = sfMapper.selectOne(sf2.getID());
		assertTrue(sf2 != null);
		assertTrue(!sf.getUpdateDate().equals(sf3.getUpdateDate()));
	}
	
	@Test
	@Transactional
	public void testDelete() {
		SettleFlowEntity sf = getSettleFlow();
		int index = sfMapper.delete(sf.getID());
		assertTrue(index == 1);
		SettleFlowEntity sf2 = sfMapper.selectOne(sf.getID());
		assertTrue(sf2 == null);
	}*/
	
	
	
}
