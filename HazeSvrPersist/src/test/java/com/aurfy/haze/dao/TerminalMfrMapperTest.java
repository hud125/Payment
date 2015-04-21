package com.aurfy.haze.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.conf.TerminalMfrMapper;
import com.aurfy.haze.entity.TerminalMfrEntity;
import com.aurfy.haze.entity.common.HelpQAEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class TerminalMfrMapperTest extends PersistUnitTest {

	@Autowired
	private TerminalMfrMapper mapper;

	public TerminalMfrEntity createTerminalManfacturer() {
		TerminalMfrEntity entityTer = new TerminalMfrEntity();
		entityTer.setID(SecurityUtils.UUID());
		entityTer.setFactoryName("JunitName_" + RandomStringUtils.randomAlphanumeric(6));
		entityTer.setContactor("Jim");
		entityTer.setTelphone("12345678");
		entityTer.setCellphone("87654321");
		entityTer.setURL("www.aurfy.com");
		entityTer.setCreateDate(new Date());
		entityTer.setUpdateDate(entityTer.getCreateDate());
		return entityTer;
	}

	private TerminalMfrEntity insertOne(TerminalMfrEntity entity) {
		int result = mapper.insert(entity);
		return result == 1 ? entity : null;
	}

	private TerminalMfrEntity insertOne() {
		return insertOne(createTerminalManfacturer());
	}

	@Test
	@Transactional
	public void testInsertTerminalMfr() {
		assertTrue(insertOne() != null);
	}

	@Test
	@Transactional
	public void testSelectTerminalMfr() {
		try {
			// insert first
			TerminalMfrEntity expected = insertOne();
			// select afterwards
			TerminalMfrEntity selected = (TerminalMfrEntity) mapper.selectOne(expected.getID());
			assertEquals(expected, selected);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testListTerminalMfr() {
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
	public void testFilterTerminalMfr() {
		try {
			final int size = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < size; ++i) {
				insertOne();
			}
			TerminalMfrEntity query = new TerminalMfrEntity();
			query.setURL("www.aurfy.com");
			List<TerminalMfrEntity> searchResults = mapper.selectBy(query, 1, 10);
			assertTrue(searchResults.size() > 0);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	public void testCountBy() {
		TerminalMfrEntity entityTer = createTerminalManfacturer();
		int index = mapper.insert(entityTer);
		assertTrue(index == 1);
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			mapper.insert(createTerminalManfacturer());
		}
		assertNotNull(mapper.countBy(entityTer));
	}
	
	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = mapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			mapper.insert(createTerminalManfacturer());
		}
		assertTrue(oldSize + randomSize == mapper.countAll());
	}

	@Test
	@Transactional
	public void testUpdateTerminalMfr() {
		try {

			TerminalMfrEntity olderone = insertOne();

			// update information
			TerminalMfrEntity updatedUser = createTerminalManfacturer();
			updatedUser.setID(olderone.getID());
			mapper.update(updatedUser);

			// compare
			TerminalMfrEntity selectedUser = mapper.selectOne(olderone.getID());

			assertEquals(olderone.getID(), selectedUser.getID());
			assertNotEquals(olderone.getFactoryName(), selectedUser.getFactoryName());
			assertNotEquals(olderone, selectedUser);
			assertEquals(updatedUser, selectedUser);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testDeleteTerminalMfr() {
		try {

			TerminalMfrEntity olderone = insertOne();
			String id = olderone.getID();

			mapper.delete(id);

			TerminalMfrEntity selectedUser = mapper.selectOne(id);
			assertNull(selectedUser);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}