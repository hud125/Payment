package com.aurfy.haze.dao.infrastructure;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.ClearingSummaryMapper;
import com.aurfy.haze.entity.infra.ClearingSummaryEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class ClearingSummaryMapperTest extends PersistUnitTest {
	
	@Autowired
	private ClearingSummaryMapper clearingSummaryMapper;
	@Test
	@Transactional
	public void testInsert() {
		ClearingSummaryEntity clearingSummaryEntity = newClearingSummaryEntity();
		int size = clearingSummaryMapper.countAll();
		Assert.assertTrue(clearingSummaryMapper.insert(clearingSummaryEntity) == 1);
		int newSize = clearingSummaryMapper.countAll();
		Assert.assertTrue(size != newSize);
	}
	@Test
	@Transactional
	public void testDelete() {
		ClearingSummaryEntity clearingSummaryEntity = newClearingSummaryEntity();
		int size = clearingSummaryMapper.countAll();
		Assert.assertTrue(clearingSummaryMapper.insert(clearingSummaryEntity) == 1);
		int newSize = clearingSummaryMapper.countAll();
		Assert.assertTrue(size != newSize);
		Assert.assertTrue(clearingSummaryMapper.delete(clearingSummaryEntity.getID()) == 1);
		newSize = clearingSummaryMapper.countAll();
		Assert.assertTrue(size == newSize);
	}
	@Test
	@Transactional
	public void testSelectOne() {
		ClearingSummaryEntity clearingSummaryEntity = newClearingSummaryEntity();
		Assert.assertTrue(clearingSummaryMapper.insert(clearingSummaryEntity) == 1);
		Assert.assertNotNull(clearingSummaryMapper.selectOne(clearingSummaryEntity.getID()));
	}
	@Test
	@Transactional
	public void testSelectAll() {
		int size = clearingSummaryMapper.countAll();
		ClearingSummaryEntity clearingSummaryEntity = newClearingSummaryEntity();
		Assert.assertTrue(clearingSummaryMapper.insert(clearingSummaryEntity) == 1);
		int newSize = clearingSummaryMapper.selectAll().size();
		Assert.assertTrue(size + 1 == newSize);
	}
	@Test
	@Transactional
	public void testUpdate() {
		ClearingSummaryEntity clearingSummaryEntity = newClearingSummaryEntity();
		String clearingCurrency = "F";
		Assert.assertTrue(clearingSummaryMapper.insert(clearingSummaryEntity) == 1);
		clearingSummaryEntity.setClearingCurrency(clearingCurrency);
		Assert.assertTrue(clearingSummaryMapper.update(clearingSummaryEntity) == 1);
		ClearingSummaryEntity newClearingSummaryEntity = clearingSummaryMapper.selectOne(clearingSummaryEntity.getID());
		Assert.assertEquals(newClearingSummaryEntity.getClearingCurrency(), clearingCurrency);
		
	}
	private ClearingSummaryEntity newClearingSummaryEntity() {
		ClearingSummaryEntity clearingSummaryEntity = new ClearingSummaryEntity();
		clearingSummaryEntity.setID(SecurityUtils.UUID());
		clearingSummaryEntity.setScheduleBatchID(SecurityUtils.UUID());
		clearingSummaryEntity.setClearingDate(new Date());
		clearingSummaryEntity.setMerchantID(SecurityUtils.UUID());
		clearingSummaryEntity.setMerSubID("3");
		clearingSummaryEntity.setClearingCurrency("T");
		clearingSummaryEntity.setExchangeRate(0.1f);
		clearingSummaryEntity.setIncomeAmount(1l);
		clearingSummaryEntity.setOpAmount(1l);
		clearingSummaryEntity.setDepositAmount(1l);
		clearingSummaryEntity.setBalanceAmount(1l);
		clearingSummaryEntity.setComments("Junit_" + RandomStringUtils.randomAlphanumeric(6));
		clearingSummaryEntity.setCreateDate(new Date());
		clearingSummaryEntity.setUpdateDate(new Date());
		return clearingSummaryEntity;
	}
	
}
