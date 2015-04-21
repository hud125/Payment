package com.aurfy.haze.dao.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.system.ActionResultEnum;
import com.aurfy.haze.core.model.system.AuditActionEnum;
import com.aurfy.haze.core.model.system.SystemModuleEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.AuditLogMapper;
import com.aurfy.haze.dao.utils.DBWildcardUtils;
import com.aurfy.haze.entity.infra.AuditLogEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class AuditLogMapperTest extends PersistUnitTest {

	@Autowired
	private AuditLogMapper mapper;

	private static AuditLogEntity createAuditLog() {
		AuditLogEntity entityLog = new AuditLogEntity();
		entityLog.setID(SecurityUtils.UUID());
		entityLog.setModule(getRandomEnum(SystemModuleEnum.class));
		entityLog.setAction(getRandomEnum(AuditActionEnum.class));
		entityLog.setResult(getRandomEnum(ActionResultEnum.class));
		entityLog.setParam1("Junit_P1_" + RandomStringUtils.randomAlphanumeric(3));
		entityLog.setParam2("Junit_P2_" + RandomStringUtils.randomAlphanumeric(3));
		entityLog.setParam3("Junit_P3_" + RandomStringUtils.randomAlphanumeric(3));
		entityLog.setParam4("Junit_P4_" + RandomStringUtils.randomAlphanumeric(3));
		entityLog.setParam5("Junit_P5_" + RandomStringUtils.randomAlphanumeric(3));
		entityLog.setOwnerId("Junit dao tester");
		entityLog.setCreateDate(new Date());
		entityLog.setUpdateDate(entityLog.getCreateDate());
		return entityLog;
	}

	private AuditLogEntity insertOne() {
		return insertOne(createAuditLog());
	}

	private AuditLogEntity insertOne(AuditLogEntity entity) {
		int result = mapper.insert(entity);
		return result == 1 ? entity : null;
	}

	@Test
	@Transactional
	public void testSelectBy() {
		AuditLogEntity auditLogEntity = insertOne(createAuditLog());
		assertTrue(auditLogEntity != null);
		AuditLogEntity filterEntity = new AuditLogEntity();
		filterEntity.setModule(auditLogEntity.getModule());
		List<AuditLogEntity> list = mapper.selectBy(filterEntity, 0, 10);
		assertTrue(list != null);
		if (list != null) {
			assertTrue(list.size() > 0);
			if (list.size() == 1) {
				assertTrue(list.get(0).getModule().equals(auditLogEntity.getModule()));
			}
		}
		filterEntity.setModule(null);
		filterEntity.setAction(auditLogEntity.getAction());
		list = mapper.selectBy(filterEntity, 0, 10);
		assertTrue(list != null);
		if (list != null) {
			assertTrue(list.size() > 0);
			if (list.size() == 1) {
				assertTrue(list.get(0).getAction().equals(auditLogEntity.getAction()));
			}
		}
		filterEntity.setModule(null);
		filterEntity.setAction(null);
		filterEntity.setResult(auditLogEntity.getResult());
		list = mapper.selectBy(filterEntity, 0, 10);
		assertTrue(list != null);
		if (list != null) {
			assertTrue(list.size() > 0);
			if (list.size() == 1) {
				assertTrue(list.get(0).getResult().equals(auditLogEntity.getResult()));
			}
		}
		filterEntity.setModule(null);
		filterEntity.setAction(null);
		filterEntity.setResult(null);
		filterEntity.setCreateDate(new Date(auditLogEntity.getCreateDate().getTime() - 1));
		list = mapper.selectBy(filterEntity, 0, 10);
		assertTrue(list != null);
		if (list != null) {
			assertTrue(list.size() > 0);
			if (list.size() == 1) {
				assertTrue(list.get(0).getID().equals(auditLogEntity.getID()));
			}
		}
		filterEntity.setModule(null);
		filterEntity.setAction(null);
		filterEntity.setResult(null);
		filterEntity.setUpdateDate(new Date(auditLogEntity.getCreateDate().getTime() + 1));
		list = mapper.selectBy(filterEntity, 0, 10);
		assertTrue(list != null);
		if (list != null) {
			assertTrue(list.size() > 0);
			if (list.size() == 1) {
				assertTrue(list.get(0).getID().equals(auditLogEntity.getID()));
			}
		}
		filterEntity.setModule(null);
		filterEntity.setAction(null);
		filterEntity.setResult(null);
		filterEntity.setCreateDate(new Date(auditLogEntity.getCreateDate().getTime() - 1));
		filterEntity.setUpdateDate(new Date(auditLogEntity.getCreateDate().getTime() + 1));
		list = mapper.selectBy(filterEntity, 0, 10);
		assertTrue(list != null);
		if (list != null) {
			assertTrue(list.size() > 0);
			if (list.size() == 1) {
				assertTrue(list.get(0).getID().equals(auditLogEntity.getID()));
			}
		}
	}

	@Test
	@Transactional
	public void testInsertAuditLog() {
		assertTrue(insertOne() != null);
	}

	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = mapper.selectAll().size();
		assertTrue(insertOne() != null);
		int newSize = mapper.countAll();
		assertTrue(oldSize + 1 == newSize);
	}

	@Test
	@Transactional
	public void testSelectAuditLog() {
		try {
			// insert first
			AuditLogEntity expected = insertOne();
			// select afterwards
			AuditLogEntity selected = (AuditLogEntity) mapper.selectOne(expected.getID());
			assertEquals(expected, selected);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testListAuditLogs() {
		try {
			int oldSize = mapper.selectAll().size();
			int randomSize = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < randomSize; ++i) {
				insertOne();
			}
			int newSize = mapper.selectAll().size();
			assertEquals(oldSize + randomSize, newSize);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testFilterLogs() {
		try {
			final int size = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < size; ++i) {
				insertOne();
			}
			AuditLogEntity query = new AuditLogEntity();
			query.setParam1(DBWildcardUtils.escapeWildcards("Junit_P1"));
			query.setOwnerId(DBWildcardUtils.escapeWildcards("Junit dao tester"));
			// List<Entity> searchResults = mapper.selectBy(query, 0, 10);
			// assertTrue(searchResults.size() > 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}

	}

}
