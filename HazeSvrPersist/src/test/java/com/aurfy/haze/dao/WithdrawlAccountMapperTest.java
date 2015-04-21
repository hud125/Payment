package com.aurfy.haze.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.dao.conf.WithdrawlAccountMapper;
import com.aurfy.haze.entity.common.HelpQAEntity;
import com.aurfy.haze.entity.infra.WithdrawlAccountEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class WithdrawlAccountMapperTest extends PersistUnitTest{
    
	@Autowired
	private WithdrawlAccountMapper withdrawlAccountMapper;
	
	
	public WithdrawlAccountEntity getWithdrawlAccountEntity(){
		WithdrawlAccountEntity withdrawlAccountEntity = new WithdrawlAccountEntity();
		withdrawlAccountEntity.setID(SecurityUtils.UUID());
		withdrawlAccountEntity.setSumWithdrawlAmount(12345);
		withdrawlAccountEntity.setWithdrawlCurrency(Currency.USD);
		withdrawlAccountEntity.setCreateDate(new Date());
		withdrawlAccountEntity.setUpdateDate(new Date());
		return withdrawlAccountEntity;
	}
	
	@Test
	@Transactional
	public void testCreate() {
		WithdrawlAccountEntity withdrawlAccountEntity = getWithdrawlAccountEntity();
		int index = withdrawlAccountMapper.insert(withdrawlAccountEntity);
		assertTrue(index == 1);
	}
	
	@Test
	@Transactional
	public void testRetrieve() {
		WithdrawlAccountEntity withdrawlAccountEntity = getWithdrawlAccountEntity();
		int index = withdrawlAccountMapper.insert(withdrawlAccountEntity);
		assertTrue(index == 1);
		WithdrawlAccountEntity withdrawlAccountEntity2 = withdrawlAccountMapper.selectOne(withdrawlAccountEntity.getID());
		assertTrue(withdrawlAccountEntity.equals(withdrawlAccountEntity2));
	}
	
	
	@Test
	@Transactional
	public void testRetrieveAll() {
		int oldSize = withdrawlAccountMapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			withdrawlAccountMapper.insert(getWithdrawlAccountEntity());
		}
		assertTrue(oldSize + randomSize == withdrawlAccountMapper.selectAll().size());
	}
	
	@Test
	@Transactional
	public void testCountBy() {
		WithdrawlAccountEntity withdrawlAccountEntity = getWithdrawlAccountEntity();
		int index = withdrawlAccountMapper.insert(withdrawlAccountEntity);
		assertTrue(index == 1);
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			withdrawlAccountMapper.insert(getWithdrawlAccountEntity());
		}
		assertNotNull(withdrawlAccountMapper.countBy(withdrawlAccountEntity));
	}
	
	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = withdrawlAccountMapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			withdrawlAccountMapper.insert(getWithdrawlAccountEntity());
		}
		assertTrue(oldSize + randomSize == withdrawlAccountMapper.countAll());
	}
	
	@Test
	@Transactional
	public void testUpdate() {
		WithdrawlAccountEntity withdrawlAccountEntity = getWithdrawlAccountEntity();
		int index = withdrawlAccountMapper.insert(withdrawlAccountEntity);
		assertTrue(index == 1);
		long amount = 54321;
		withdrawlAccountEntity.setSumWithdrawlAmount(amount);
		index = withdrawlAccountMapper.update(withdrawlAccountEntity);
		assertTrue(index == 1);
		withdrawlAccountEntity = withdrawlAccountMapper.selectOne(withdrawlAccountEntity.getID());
		assertEquals(withdrawlAccountEntity.getSumWithdrawlAmount(), amount);
	}
	
	@Test
	@Transactional
	public void testRemove() {
		WithdrawlAccountEntity withdrawlAccountEntity = getWithdrawlAccountEntity();
		int index = withdrawlAccountMapper.insert(withdrawlAccountEntity);
		assertTrue(index == 1);
		index = withdrawlAccountMapper.delete(withdrawlAccountEntity.getID());
		assertTrue(index == 1);
	}	
}
