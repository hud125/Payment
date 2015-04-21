package com.aurfy.haze.dao.perm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.perm.PermEntryEntity;
import com.aurfy.haze.entity.perm.PermObjectEntity;
import com.aurfy.haze.entity.perm.PermOperationEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class PermEntryMapperTest extends PersistUnitTest {

	@Autowired
	private PermObjectMapper objMapper;

	@Autowired
	private PermOperationMapper operMapper;

	@Autowired
	private PermEntryMapper mapper;

	@Autowired
	private RowScopeMapper rowMapper;

	@Autowired
	private ColumnScopeMapper columnMapper;

	public PermEntryEntity newPermEntry() {
		PermEntryEntity entry = new PermEntryEntity();
		// no entry id set
		PermObjectEntity obj = new PermObjectMapperTest().newPermObject();
		String oldId = obj.getID();
		int count = objMapper.insert(obj);
		String newId = obj.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);

		PermOperationEntity oper = new PermOperationMapperTest().newPermOperation();
		oldId = oper.getID();
		count = operMapper.insert(oper);
		newId = oper.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);

		entry.setID(SecurityUtils.UUID());
		entry.setEntryKey("Home_listMerchants");
		entry.setDisplayName("获取用户列表");
		entry.setObject(obj);
		entry.setOperation(oper);
		entry.setComments("test");
		entry.setCreateDate(new Date());
		entry.setUpdateDate(entry.getCreateDate());
		return entry;
	}

	public PermEntryEntity getNewPermEntry() {
		PermEntryEntity entry = newPermEntry();
		String oldId = entry.getID();
		int count = mapper.insert(entry);
		String newId = entry.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return entry;
	}

	@Test
	@Transactional
	public void TestInsertPermEntry() {
		getNewPermEntry();
	}

	@Test
	@Transactional
	public void testSelectPermEntry() {
		// get permEntry by the given id
		PermEntryEntity entry = getNewPermEntry();
		PermEntryEntity entry2 = mapper.selectOne(entry.getID());
		assertNotNull(entry2);
		assertTrue(entry.equals(entry2));
	}

	@Test
	@Transactional
	public void testUpdatePermEntry() {
		PermEntryEntity entry = getNewPermEntry();
		PermEntryEntity newentry = new PermEntryEntity();
		newentry.setEntryKey("Home_listMerchants");
		newentry.setDisplayName("获取用户列表2");
		newentry.setComments("test2");
		newentry.setObject(entry.getObject());
		newentry.setOperation(entry.getOperation());
		newentry.setCreateDate(new Date());
		newentry.setUpdateDate(newentry.getCreateDate());
		newentry.setID(entry.getID());

		int count = mapper.update(newentry);
		PermEntryEntity newentry2 = mapper.selectOne(newentry.getID());
		assertTrue(count == 1);
		assertTrue(!entry.equals(newentry));
		assertTrue(newentry.equals(newentry2));
	}

	@Test
	@Transactional
	public void testDeletePermEntry() {
		PermEntryEntity entry = getNewPermEntry();
		// delete the new permEntry
		int count = mapper.delete(entry.getID());
		assertTrue(count == 1);
	}

}
