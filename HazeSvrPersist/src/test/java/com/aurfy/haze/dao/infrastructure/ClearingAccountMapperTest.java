package com.aurfy.haze.dao.infrastructure;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.ClearingAccountMapper;
import com.aurfy.haze.entity.infra.ClearingAccountEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class ClearingAccountMapperTest extends PersistUnitTest {

	
	@Autowired
	private ClearingAccountMapper clearingAccountMapper;
	
	@Test
	@Transactional
	public void testInsert() {
		ClearingAccountEntity clearingAccountEntity = newClearingAccountEntity();
		int size = clearingAccountMapper.countAll();
		Assert.assertTrue(clearingAccountMapper.insert(clearingAccountEntity) == 1);
		int newSize = clearingAccountMapper.countAll();
		Assert.assertTrue(size != newSize);
	}
	@Test
	@Transactional
	public void testDelete() {
		ClearingAccountEntity clearingAccountEntity = newClearingAccountEntity();
		int size = clearingAccountMapper.countAll();
		Assert.assertTrue(clearingAccountMapper.insert(clearingAccountEntity) == 1);
		int newSize = clearingAccountMapper.countAll();
		Assert.assertTrue(size != newSize);
		Assert.assertTrue(clearingAccountMapper.delete(clearingAccountEntity.getID()) == 1);
		newSize = clearingAccountMapper.countAll();
		Assert.assertTrue(size == newSize);
	}
	@Test
	@Transactional
	public void testSelectOne() {
		ClearingAccountEntity clearingAccountEntity = newClearingAccountEntity();
		Assert.assertTrue(clearingAccountMapper.insert(clearingAccountEntity) == 1);
		Assert.assertNotNull(clearingAccountMapper.selectOne(clearingAccountEntity.getID()));
	}
	@Test
	@Transactional
	public void testSelectAll() {
		int size = clearingAccountMapper.countAll();
		ClearingAccountEntity clearingAccountEntity = newClearingAccountEntity();
		Assert.assertTrue(clearingAccountMapper.insert(clearingAccountEntity) == 1);
		int newSize = clearingAccountMapper.selectAll().size();
		Assert.assertTrue(size + 1 == newSize);
	}
	@Test
	@Transactional
	public void testUpdate() {
		ClearingAccountEntity clearingAccountEntity = newClearingAccountEntity();
		String clearingCurrency = "F";
		Assert.assertTrue(clearingAccountMapper.insert(clearingAccountEntity) == 1);
		clearingAccountEntity.setClearingCurrency(clearingCurrency);
		Assert.assertTrue(clearingAccountMapper.update(clearingAccountEntity) == 1);
		ClearingAccountEntity newClearingAccountEntity = clearingAccountMapper.selectOne(clearingAccountEntity.getID());
		Assert.assertEquals(newClearingAccountEntity.getClearingCurrency(), clearingCurrency);
		
	}
	private ClearingAccountEntity newClearingAccountEntity() {
		ClearingAccountEntity clearingAccountEntity = new ClearingAccountEntity();
		clearingAccountEntity.setID(SecurityUtils.UUID());
		clearingAccountEntity.setScheduleBatchID(SecurityUtils.UUID());
		clearingAccountEntity.setClearingDate(new Date());
		clearingAccountEntity.setMerchantID(SecurityUtils.UUID());
		clearingAccountEntity.setMerSubID("3");
		clearingAccountEntity.setClearingCurrency("T");
		clearingAccountEntity.setSumIncomeAmount(1l);
		clearingAccountEntity.setSumOperationAmount(1l);
		clearingAccountEntity.setSumDepositAmount(1l);
		clearingAccountEntity.setSumReturnDepositAmount(1l);
		clearingAccountEntity.setSumBalanceAmount(1l);
		clearingAccountEntity.setCreateDate(new Date());
		clearingAccountEntity.setUpdateDate(new Date());
		return clearingAccountEntity;
	}
	
	
}
