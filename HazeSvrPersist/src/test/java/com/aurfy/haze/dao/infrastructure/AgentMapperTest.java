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
import com.aurfy.haze.dao.infra.mer.SaleMapper;
import com.aurfy.haze.entity.configuration.rate.RateConfigEntity;
import com.aurfy.haze.entity.infra.UserEntity;
import com.aurfy.haze.entity.infra.mer.AgentEntity;
import com.aurfy.haze.entity.infra.mer.SaleEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class AgentMapperTest extends PersistUnitTest {
	
	@Autowired
	private AgentMapper agentMapper;
	
	@Autowired
	private RateConfigMapper rateConfMapper;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	public AgentEntity newAgentEntity(){
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
		return agent;
	}
	
	public AgentEntity getAgentEntity(){
		AgentEntity agent = newAgentEntity();
		String oldId = agent.getID();
		int count = agentMapper.insert(agent);
		String newId = agent.getID();
		assertEquals(oldId, newId);
		assertTrue(count == 1);
		return agent;
	}
	
	@Test
	@Transactional
	public void tesInsertAgentEntity() {
		getAgentEntity();
	}

	@Test
	@Transactional
	public void testRetrieveAgentEntity() {
		AgentEntity agent = getAgentEntity();
		AgentEntity agent2 = agentMapper.selectOne(agent.getID());
		assertTrue(agent2.equals(agent));
	}

	@Test
	@Transactional
	public void testUpdateAgentEntity() {
		AgentEntity agent = getAgentEntity();

		AgentEntity agent2 = new AgentEntity();
		agent2.setID(agent.getID());
		agent2.setComments(RandomStringUtils.randomAlphanumeric(10));
		int index = agentMapper.update(agent2);
		assertTrue(index == 1);
	}

	@Test
	@Transactional
	public void testRemoveAgentEntity() {
		AgentEntity agent = getAgentEntity();
		int index = agentMapper.delete(agent.getID());
		assertTrue(index == 1);
	}

}
