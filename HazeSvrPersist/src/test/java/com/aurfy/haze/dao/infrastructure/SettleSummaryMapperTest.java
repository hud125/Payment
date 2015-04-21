package com.aurfy.haze.dao.infrastructure;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.SettleSummaryMapper;
import com.aurfy.haze.entity.infra.SettleSummaryEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class SettleSummaryMapperTest extends PersistUnitTest {

	@Autowired
	private SettleSummaryMapper settleSummaryMapper;
	
	@Test
	@Transactional
	public void testInsert() {
		SettleSummaryEntity settleSummaryEntity = newSettleSummaryEntity();
		int size = settleSummaryMapper.countAll();
		Assert.assertTrue(settleSummaryMapper.insert(settleSummaryEntity) == 1);
		int newSize = settleSummaryMapper.countAll();
		Assert.assertTrue(size != newSize);
	}
	@Test
	@Transactional
	public void testDelete() {
		SettleSummaryEntity settleSummaryEntity = newSettleSummaryEntity();
		int size = settleSummaryMapper.countAll();
		Assert.assertTrue(settleSummaryMapper.insert(settleSummaryEntity) == 1);
		int newSize = settleSummaryMapper.countAll();
		Assert.assertTrue(size != newSize);
		Assert.assertTrue(settleSummaryMapper.delete(settleSummaryEntity.getID()) == 1);
		newSize = settleSummaryMapper.countAll();
		Assert.assertTrue(size == newSize);
	}
	@Test
	@Transactional
	public void testSelectOne() {
		SettleSummaryEntity settleSummaryEntity = newSettleSummaryEntity();
		Assert.assertTrue(settleSummaryMapper.insert(settleSummaryEntity) == 1);
		Assert.assertNotNull(settleSummaryMapper.selectOne(settleSummaryEntity.getID()));
	}
	@Test
	@Transactional
	public void testSelectAll() {
		int size = settleSummaryMapper.countAll();
		SettleSummaryEntity settleSummaryEntity = newSettleSummaryEntity();
		Assert.assertTrue(settleSummaryMapper.insert(settleSummaryEntity) == 1);
		int newSize = settleSummaryMapper.selectAll().size();
		Assert.assertTrue(size + 1 == newSize);
	}
	@Test
	@Transactional
	public void testUpdate() {
		SettleSummaryEntity settleSummaryEntity = newSettleSummaryEntity();
		String clearingCurrency = "F";
		Assert.assertTrue(settleSummaryMapper.insert(settleSummaryEntity) == 1);
		settleSummaryEntity.setSettleCurrency(clearingCurrency);
		Assert.assertTrue(settleSummaryMapper.update(settleSummaryEntity) == 1);
		SettleSummaryEntity newSettleSummaryEntity = settleSummaryMapper.selectOne(settleSummaryEntity.getID());
		Assert.assertEquals(newSettleSummaryEntity.getSettleCurrency(), clearingCurrency);
		
	}
	
	private SettleSummaryEntity newSettleSummaryEntity() {
		SettleSummaryEntity settleSummaryEntity = new SettleSummaryEntity();
		settleSummaryEntity.setID(SecurityUtils.UUID());
		settleSummaryEntity.setScheduleBatchID(SecurityUtils.UUID());
		settleSummaryEntity.setSettleDate(new Date());
		settleSummaryEntity.setMerchantID(SecurityUtils.UUID());
		settleSummaryEntity.setMerSubID("3");
		settleSummaryEntity.setSettleCurrency("T");
		settleSummaryEntity.setIncomeAmount(1l);
		settleSummaryEntity.setOperationAmount(1l);
		settleSummaryEntity.setDepositAmount(1l);
		settleSummaryEntity.setReturnDepositAmount(1l);
		settleSummaryEntity.setFreezeAmount(1l);
		settleSummaryEntity.setUnfreezeAmount(1l);
		settleSummaryEntity.setBalanceAmount(1l);
		settleSummaryEntity.setComments("Junit_" + RandomStringUtils.randomAlphanumeric(6));
		settleSummaryEntity.setCreateDate(new Date());
		settleSummaryEntity.setUpdateDate(new Date());
		return settleSummaryEntity;
	}
}
