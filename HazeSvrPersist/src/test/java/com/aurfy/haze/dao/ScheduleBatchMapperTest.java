package com.aurfy.haze.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.conf.ScheduleBatchMapper;
import com.aurfy.haze.entity.common.HelpQAEntity;
import com.aurfy.haze.entity.infra.ScheduleBatchEntity;
import com.aurfy.haze.entity.infra.WithdrawlAccountEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class ScheduleBatchMapperTest extends PersistUnitTest {

	@Autowired
	private ScheduleBatchMapper scheduleBatchMapper;

	public ScheduleBatchEntity getScheduleBatchEntity() {
		ScheduleBatchEntity scheduleBatchEntity = new ScheduleBatchEntity();
		scheduleBatchEntity.setID(SecurityUtils.UUID());
		scheduleBatchEntity.setJobId("0000001");
		;
		scheduleBatchEntity.setScheduleDate(new Date());
		scheduleBatchEntity.setLogs("This is the test logs");
		scheduleBatchEntity.setCreateDate(new Date());
		scheduleBatchEntity.setUpdateDate(new Date());
		return scheduleBatchEntity;
	}

	@Test
	@Transactional
	public void testCreate() {
		ScheduleBatchEntity scheduleBatchEntity = getScheduleBatchEntity();
		int index = scheduleBatchMapper.insert(scheduleBatchEntity);
		assertTrue(index == 1);
	}

	@Test
	@Transactional
	public void testRetrieve() {
		ScheduleBatchEntity scheduleBatchEntity = getScheduleBatchEntity();
		int index = scheduleBatchMapper.insert(scheduleBatchEntity);
		assertTrue(index == 1);
		ScheduleBatchEntity scheduleBatchEntity2 = scheduleBatchMapper.selectOne(scheduleBatchEntity.getID());
		assertTrue(scheduleBatchEntity.equals(scheduleBatchEntity2));
	}

	@Test
	@Transactional
	public void testRetrieveAll() {
		int oldSize = scheduleBatchMapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for (int i = 0; i < randomSize; i++) {
			scheduleBatchMapper.insert(getScheduleBatchEntity());
		}
		assertTrue(oldSize + randomSize == scheduleBatchMapper.selectAll().size());
	}
	
	@Test
	@Transactional
	public void testCountBy() {
		ScheduleBatchEntity scheduleBatchEntity = getScheduleBatchEntity();
		int index = scheduleBatchMapper.insert(scheduleBatchEntity);
		assertTrue(index == 1);
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			scheduleBatchMapper.insert(getScheduleBatchEntity());
		}
		assertNotNull(scheduleBatchMapper.countBy(scheduleBatchEntity));
	}

	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = scheduleBatchMapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for (int i = 0; i < randomSize; i++) {
			scheduleBatchMapper.insert(getScheduleBatchEntity());
		}
		assertTrue(oldSize + randomSize == scheduleBatchMapper.countAll());
	}

	@Test
	@Transactional
	public void testUpdate() {
		ScheduleBatchEntity scheduleBatchEntity = getScheduleBatchEntity();
		int index = scheduleBatchMapper.insert(scheduleBatchEntity);
		assertTrue(index == 1);
		String logs = "This is another test logs";
		scheduleBatchEntity.setLogs(logs);
		index = scheduleBatchMapper.update(scheduleBatchEntity);
		assertTrue(index == 1);
		scheduleBatchEntity = scheduleBatchMapper.selectOne(scheduleBatchEntity.getID());
		assertEquals(scheduleBatchEntity.getLogs(), logs);
	}

	@Test
	@Transactional
	public void testRemove() {
		ScheduleBatchEntity scheduleBatchEntity = getScheduleBatchEntity();
		int index = scheduleBatchMapper.insert(scheduleBatchEntity);
		assertTrue(index == 1);
		index = scheduleBatchMapper.delete(scheduleBatchEntity.getID());
		assertTrue(index == 1);
	}

}
