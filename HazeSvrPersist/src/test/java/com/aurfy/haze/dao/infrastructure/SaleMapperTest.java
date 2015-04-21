package com.aurfy.haze.dao.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.configuration.rate.RateRoleClassifierEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.configuration.rate.RateConfigMapper;
import com.aurfy.haze.dao.configuration.rate.RateConfigMapperTest;
import com.aurfy.haze.dao.infra.UserMapper;
import com.aurfy.haze.dao.infra.mer.SaleMapper;
import com.aurfy.haze.entity.configuration.rate.RateConfigEntity;
import com.aurfy.haze.entity.infra.UserEntity;
import com.aurfy.haze.entity.infra.mer.SaleEntity;
import com.aurfy.haze.utils.SecurityUtils;
import com.aurfy.haze.utils.StringUtils;

public class SaleMapperTest extends PersistUnitTest {

	@Autowired
	private RateConfigMapper rateConfMapper;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Autowired
	private UserMapper userMapper;

	public SaleEntity newSaleEntity(){
		RateConfigEntity rateConf = new RateConfigMapperTest().newRateConfig();
		int index = rateConfMapper.insert(rateConf);
		assertTrue(index == 1);
		
		UserEntity user = new UserMapperTest().createUser();
		String oldId = user.getID();
		int count = userMapper.insert(user);
		String newId = user.getID();
		assertEquals(oldId, newId);
		assertTrue(count == 1);
		
		SaleEntity sale = new SaleEntity();
		sale.setID(SecurityUtils.UUID());
		sale.setUserId(user.getID());
		sale.setRateConfigId(rateConf.getID());
		sale.setComments(RandomStringUtils.randomAlphanumeric(10));
		sale.setCreateDate(new Date());
		sale.setUpdateDate(sale.getCreateDate());
		return sale;
	}
	
	public SaleEntity getSaleEntity(){
		SaleEntity sale = newSaleEntity();
		String oldId = sale.getID();
		int count = saleMapper.insert(sale);
		String newId = sale.getID();
		assertEquals(oldId, newId);
		assertTrue(count == 1);
		return sale;
	}
	
	@Test
	@Transactional
	public void tesInsert() {
		getSaleEntity();
	}

	@Test
	@Transactional
	public void testRetrieveRateConf() {
		SaleEntity sale = getSaleEntity();
		SaleEntity sale2 = saleMapper.selectOne(sale.getID());
		assertTrue(sale2.equals(sale));
	}

	@Test
	@Transactional
	public void testUpdateRateConf() {
		SaleEntity sale = getSaleEntity();

		SaleEntity sale2 = new SaleEntity();
		sale2.setID(sale.getID());
		sale2.setComments(RandomStringUtils.randomAlphanumeric(10));
		int index = saleMapper.update(sale2);
		assertTrue(index == 1);
	}

	@Test
	@Transactional
	public void testRemoveRateConf() {
		SaleEntity sale = getSaleEntity();
		int index = saleMapper.delete(sale.getID());
		assertTrue(index == 1);
	}

}
