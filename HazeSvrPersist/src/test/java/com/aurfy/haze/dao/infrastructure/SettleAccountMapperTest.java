package com.aurfy.haze.dao.infrastructure;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.SettleAccountMapper;
import com.aurfy.haze.entity.infra.SettleAccountEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class SettleAccountMapperTest extends PersistUnitTest {

	@Autowired
	private SettleAccountMapper settleAccountMapper;
	
	@Test
	@Transactional
	public void testInsert() {
		SettleAccountEntity settleAccountEntity = newSettleAccountEntity();
		int size = settleAccountMapper.countAll();
		Assert.assertTrue(settleAccountMapper.insert(settleAccountEntity) == 1);
		int newSize = settleAccountMapper.countAll();
		Assert.assertTrue(size != newSize);
	}
	@Test
	@Transactional
	public void testDelete() {
		SettleAccountEntity settleAccountEntity = newSettleAccountEntity();
		int size = settleAccountMapper.countAll();
		Assert.assertTrue(settleAccountMapper.insert(settleAccountEntity) == 1);
		int newSize = settleAccountMapper.countAll();
		Assert.assertTrue(size != newSize);
		Assert.assertTrue(settleAccountMapper.delete(settleAccountEntity.getID()) == 1);
		newSize = settleAccountMapper.countAll();
		Assert.assertTrue(size == newSize);
	}
	@Test
	@Transactional
	public void testSelectOne() {
		SettleAccountEntity settleAccountEntity = newSettleAccountEntity();
		Assert.assertTrue(settleAccountMapper.insert(settleAccountEntity) == 1);
		Assert.assertNotNull(settleAccountMapper.selectOne(settleAccountEntity.getID()));
	}
	@Test
	@Transactional
	public void testSelectAll() {
		int size = settleAccountMapper.countAll();
		SettleAccountEntity settleAccountEntity = newSettleAccountEntity();
		Assert.assertTrue(settleAccountMapper.insert(settleAccountEntity) == 1);
		int newSize = settleAccountMapper.selectAll().size();
		Assert.assertTrue(size + 1 == newSize);
	}
	@Test
	@Transactional
	public void testUpdate() {
		SettleAccountEntity settleAccountEntity = newSettleAccountEntity();
		String clearingCurrency = "F";
		Assert.assertTrue(settleAccountMapper.insert(settleAccountEntity) == 1);
		settleAccountEntity.setSettleCurrency(clearingCurrency);
		Assert.assertTrue(settleAccountMapper.update(settleAccountEntity) == 1);
		SettleAccountEntity newSettleAccountEntity = settleAccountMapper.selectOne(settleAccountEntity.getID());
		Assert.assertEquals(newSettleAccountEntity.getSettleCurrency(), clearingCurrency);
		
	}
	private SettleAccountEntity newSettleAccountEntity() {
		SettleAccountEntity settleAccountEntity = new SettleAccountEntity();
		settleAccountEntity.setID(SecurityUtils.UUID());
		settleAccountEntity.setScheduleBatchID(SecurityUtils.UUID());
		settleAccountEntity.setSettleDate(new Date());
		settleAccountEntity.setMerchantID(SecurityUtils.UUID());
		settleAccountEntity.setMerSubID("3");
		settleAccountEntity.setSettleCurrency("T");
		settleAccountEntity.setSumIncomeAmount(1l);
		settleAccountEntity.setSumOperationAmount(1l);
		settleAccountEntity.setSumDepositAmount(1l);
		settleAccountEntity.setSumReturnDepositAmount(1l);
		settleAccountEntity.setSumFreezeAmount(1l);
		settleAccountEntity.setSumUnfreezeAmount(1l);
		settleAccountEntity.setSumBalanceAmount(1l);
		settleAccountEntity.setOnwayDeposit(1l);
		settleAccountEntity.setCreateDate(new Date());
		settleAccountEntity.setUpdateDate(new Date());
		return settleAccountEntity;
	}
}
