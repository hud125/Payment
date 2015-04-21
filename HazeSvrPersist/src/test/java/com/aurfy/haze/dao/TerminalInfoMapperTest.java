package com.aurfy.haze.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.conf.TerminalInfoMapper;
import com.aurfy.haze.dao.conf.TerminalMfrMapper;
import com.aurfy.haze.entity.TerminalInfoEntity;
import com.aurfy.haze.entity.TerminalMfrEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class TerminalInfoMapperTest extends PersistUnitTest {

	@Autowired
	private TerminalInfoMapper mapper;

	@Autowired
	private TerminalMfrMapper manuMapper;

	public TerminalMfrEntity getTerManu() {
		TerminalMfrEntity entityTer = new TerminalMfrMapperTest().createTerminalManfacturer();
		String oldId = entityTer.getID();
		int count = manuMapper.insert(entityTer);
		String newId = entityTer.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return entityTer;
	}

	public TerminalInfoEntity newTerminalInfo() {
		TerminalMfrEntity ter_manu_entity = getTerManu();
		TerminalInfoEntity termInfo = new TerminalInfoEntity();
		termInfo.setID(SecurityUtils.UUID());
		termInfo.setTerminalMfr(ter_manu_entity);
		termInfo.setMerID("00000001");
		return termInfo;
	}

	public TerminalInfoEntity getNew() {
		TerminalInfoEntity term_entity = newTerminalInfo();
		String oldId = term_entity.getID();
		int count = mapper.insert(term_entity);
		String newId = term_entity.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return term_entity;
	}

	@Test
	@Transactional
	public void testInsert() {
		getNew();
	}

	@Test
	@Transactional
	public void testSelectOne() {

		TerminalInfoEntity term_entity = getNew();
		TerminalInfoEntity term_entity2 = mapper.selectOne(term_entity.getID());
		assertNotNull(term_entity2);
		assertTrue(term_entity.getID().equals(term_entity2.getID()));
	}
	
	@Test
	@Transactional
	public void testSelectAll() {
		int oldSize = mapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			getNew();
		}
		assertTrue(oldSize + randomSize == mapper.selectAll().size());
	}

	@Test
	@Transactional
	public void testCountBy() {
		TerminalInfoEntity term_entity = getNew();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			getNew();
		}
		assertNotNull(mapper.countBy(term_entity));
	}
	
	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = mapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			getNew();
		}
		assertTrue(oldSize + randomSize == mapper.countAll());
	}
	
	@Test
	@Transactional
	public void testUpdate() {
		TerminalInfoEntity term_entity = getNew();
		TerminalInfoEntity new_term_entity = new TerminalInfoEntity();
		TerminalMfrEntity entityTer = new TerminalMfrEntity();
		entityTer.setID(SecurityUtils.UUID());
		entityTer.setFactoryName("JunitName_" + RandomStringUtils.randomAlphanumeric(6));
		entityTer.setContactor("Jim");
		entityTer.setTelphone("12345678");
		entityTer.setCellphone("87654321");
		entityTer.setURL("www.aurfy.com");
		entityTer.setCreateDate(new Date());
		entityTer.setUpdateDate(entityTer.getCreateDate());

		new_term_entity.setID(term_entity.getID());
		new_term_entity.setTerminalMfr(entityTer);
		new_term_entity.setMerID("00000002");

		int count = mapper.update(new_term_entity);

		assertTrue(count == 1);

	}

	@Test
	@Transactional
	public void testDelete() {
		TerminalInfoEntity term_entity = getNew();
		int count = mapper.delete(term_entity.getID());
		assertTrue(count == 1);
	}

}
