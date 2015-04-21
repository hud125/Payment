package com.aurfy.haze.dao.infrastructure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.LogisticsMapper;
import com.aurfy.haze.entity.infra.LogisticsEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class LogisticsMapperTest extends PersistUnitTest{

	@Autowired
	private LogisticsMapper logisticsMapper;
	
	private LogisticsEntity getLogistics() {
		LogisticsEntity logisticsEntity = new LogisticsEntity();
		logisticsEntity.setID(SecurityUtils.UUID());
		logisticsEntity.setName("jUnitName_"+RandomStringUtils.random(1));
		logisticsEntity.setAbbreviation("jUnitAbbr_"+RandomStringUtils.random(1));
		logisticsEntity.setComments("jUnitComments_"+RandomStringUtils.random(1));
		logisticsEntity.setInquiryHandler("jUnitIH_"+RandomStringUtils.random(1));
		logisticsEntity.setUrl("jUnitURL_"+RandomStringUtils.random(1));
		logisticsEntity.setCreateDate(new Date());
		logisticsEntity.setUpdateDate(new Date());
		logisticsMapper.insert(logisticsEntity);
		return logisticsEntity;
	}
	@Test
	@Transactional
	public void testCreate() {
		LogisticsEntity logisticsEntity = getLogistics();
		Assert.assertTrue(logisticsEntity.getID() != null);
	}
	@Test
	@Transactional
	public void testDelete() {
		LogisticsEntity logisticsEntity = getLogistics();
		Assert.assertTrue(logisticsMapper.delete(logisticsEntity.getID()) == 1);
	}
	@Test
	@Transactional
	public void testSelectOne() {
		LogisticsEntity logisticsEntity = getLogistics();
		Assert.assertNotNull(logisticsMapper.selectOne(logisticsEntity.getID()));
	}
	@Test
	@Transactional
	public void testSelectAll() {
		int oldSize = logisticsMapper.selectAll().size();
		List<LogisticsEntity> idList = new ArrayList<LogisticsEntity>();
		idList.add(getLogistics());
		idList.add(getLogistics());
		idList.add(getLogistics());
		Assert.assertTrue(idList.size() + oldSize == logisticsMapper.selectAll().size());
	}
	@Test
	@Transactional
	public void testCount() {
		int oldSize = logisticsMapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			getLogistics();
		}
		Assert.assertTrue(oldSize+randomSize == logisticsMapper.countAll());
	}
	@Test
	@Transactional
	public void testCountBy() {
		LogisticsEntity logisticsEntity = getLogistics();
		LogisticsEntity logisticsEntity2 = getLogistics();
		Assert.assertNotEquals(logisticsEntity.getName(), logisticsEntity2.getName());
		Assert.assertNotNull(logisticsMapper.countBy(logisticsEntity));
	}
	@Test
	@Transactional
	public void testUpdate() {
		LogisticsEntity logisticsEntity = getLogistics();
		logisticsEntity.setName("jUnitName2_"+RandomStringUtils.randomAlphabetic(2));
		Assert.assertTrue(logisticsMapper.update(logisticsEntity) == 1);
	}
}
