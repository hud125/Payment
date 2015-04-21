package com.aurfy.haze.dao.common;


import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.common.HelpQAMapper;
import com.aurfy.haze.entity.common.HelpQAEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class HelpQAMapperTest extends PersistUnitTest {

	@Autowired
	private HelpQAMapper helpQAMapper;
	
	
	private HelpQAEntity getHelpQAEntity() {
		HelpQAEntity helpQAEntity = new HelpQAEntity();
		helpQAEntity.setID(SecurityUtils.UUID());
		helpQAEntity.setKey("jUnit_" + RandomStringUtils.randomAlphanumeric(7));
		helpQAEntity.setTopic("jUnit_Topic");
		helpQAEntity.setCreateDate(new Date());
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			fail(e.getMessage());
//		}
		helpQAEntity.setAnswer("jUnit_Answer");
		helpQAEntity.setUpdateDate(new Date());
		return helpQAEntity;
	}
	@Test
	@Transactional
	public void testCreate() {
		HelpQAEntity helpQAEntity = getHelpQAEntity();
		int index = helpQAMapper.insert(helpQAEntity);
		assertTrue(index == 1);
	}
	@Test
	@Transactional
	public void testRetrieve() {
		HelpQAEntity helpQAEntity = getHelpQAEntity();
		int index = helpQAMapper.insert(helpQAEntity);
		assertTrue(index == 1);
		HelpQAEntity helpQAEntity2 = helpQAMapper.selectOne(helpQAEntity.getID());
		assertTrue(helpQAEntity2.equals(helpQAEntity));
	}
	@Test
	@Transactional
	public void testRetrieveAll() {
		int oldSize = helpQAMapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			helpQAMapper.insert(getHelpQAEntity());
		}
		assertTrue(oldSize + randomSize == helpQAMapper.selectAll().size());
	}
	@Test
	@Transactional
	public void testCountBy() {
		HelpQAEntity helpQAEntity = getHelpQAEntity();
		int index = helpQAMapper.insert(helpQAEntity);
		assertTrue(index == 1);
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			helpQAMapper.insert(getHelpQAEntity());
		}
		assertNotNull(helpQAMapper.countBy(helpQAEntity));
	}
	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = helpQAMapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			helpQAMapper.insert(getHelpQAEntity());
		}
		assertTrue(oldSize + randomSize == helpQAMapper.countAll());
	}
	@Test
	@Transactional
	public void testUpdate() {
		HelpQAEntity helpQAEntity = getHelpQAEntity();
		int index = helpQAMapper.insert(helpQAEntity);
		assertTrue(index == 1);
		String str = "jUnit_TestAnswer";
		helpQAEntity.setAnswer(str);
		index = helpQAMapper.update(helpQAEntity);
		assertTrue(index == 1);
		helpQAEntity = helpQAMapper.selectOne(helpQAEntity.getID());
		assertEquals(helpQAEntity.getAnswer(), str);
	}
	@Test
	@Transactional
	public void testRemove() {
		HelpQAEntity helpQAEntity = getHelpQAEntity();
		int index = helpQAMapper.insert(helpQAEntity);
		assertTrue(index == 1);
		index = helpQAMapper.delete(helpQAEntity.getID());
		assertTrue(index == 1);
	}
}
