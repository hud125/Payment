package com.aurfy.haze.dao.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.configuration.rate.RateConfigMapper;
import com.aurfy.haze.dao.configuration.rate.RateConfigMapperTest;
import com.aurfy.haze.dao.infra.UserMapper;
import com.aurfy.haze.dao.infra.mer.AgentMapper;
import com.aurfy.haze.dao.infra.mer.PspMapper;
import com.aurfy.haze.dao.infra.mer.SaleMapper;
import com.aurfy.haze.entity.configuration.rate.RateConfigEntity;
import com.aurfy.haze.entity.infra.UserEntity;
import com.aurfy.haze.entity.infra.mer.AgentEntity;
import com.aurfy.haze.entity.infra.mer.PspEntity;
import com.aurfy.haze.entity.infra.mer.SaleEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class PspMapperTest extends PersistUnitTest {
	
	@Autowired
	private PspMapper pspMapper;
	
	@Autowired
	private AgentMapper agentMapper;
	
	@Autowired
	private RateConfigMapper rateConfMapper;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	public PspEntity newPspEntity(){
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
		oldId = sale.getID();
		count = saleMapper.insert(sale);
		newId = sale.getID();
		assertEquals(oldId, newId);
		assertTrue(count == 1);
		
		AgentEntity agent = new AgentEntity();
		agent.setID(SecurityUtils.UUID());
		agent.setUserId(user.getID());
		agent.setRateConfigId(rateConf.getID());
		agent.setSalesId(sale.getID());
		agent.setComments(RandomStringUtils.randomAlphanumeric(10));
		agent.setCreateDate(new Date());
		agent.setUpdateDate(agent.getCreateDate());
		oldId = agent.getID();
		count = agentMapper.insert(agent);
		newId = agent.getID();
		assertEquals(oldId, newId);
		assertTrue(count == 1);
		
		PspEntity psp = new PspEntity();
		psp.setID(SecurityUtils.UUID());
		psp.setAgentId(agent.getID());
		psp.setUserId(user.getID());
		psp.setSalesId(sale.getID());
		psp.setRateConfigId(rateConf.getID());
		psp.setComments(RandomStringUtils.randomAlphanumeric(10));
		psp.setCreateDate(new Date());
		psp.setUpdateDate(psp.getCreateDate());
		return psp;
	}
	
	public PspEntity getPspEntity(){
		PspEntity psp = newPspEntity();
		String oldId = psp.getID();
		int count = pspMapper.insert(psp);
		String newId = psp.getID();
		assertEquals(oldId, newId);
		assertTrue(count == 1);
		return psp;
	}
	
	@Test
	@Transactional
	public void tesInsertPspEntity() {
		getPspEntity();
	}

	@Test
	@Transactional
	public void testRetrievePspEntity() {
		PspEntity psp = getPspEntity();
		PspEntity psp2 = pspMapper.selectOne(psp.getID());
		assertTrue(psp2.equals(psp));
	}

	@Test
	@Transactional
	public void testUpdatePspEntity() {
		PspEntity psp = getPspEntity();

		PspEntity psp2 = new PspEntity();
		psp2.setID(psp.getID());
		psp2.setComments(RandomStringUtils.randomAlphanumeric(10));
		int index = pspMapper.update(psp2);
		assertTrue(index == 1);
	}

	@Test
	@Transactional
	public void testRemovePspEntity() {
		PspEntity psp = getPspEntity();
		int index = pspMapper.delete(psp.getID());
		assertTrue(index == 1);
	}


}
