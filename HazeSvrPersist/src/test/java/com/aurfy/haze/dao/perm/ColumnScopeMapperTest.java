package com.aurfy.haze.dao.perm;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.perm.ColumnScopeEntity;
import com.aurfy.haze.entity.perm.PermEntryEntity;
import com.aurfy.haze.entity.perm.PermObjectEntity;
import com.aurfy.haze.entity.perm.PermOperationEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class ColumnScopeMapperTest extends PersistUnitTest {

	@Autowired
	private ColumnScopeMapper mapper;

	@Autowired
	private PermObjectMapper objMapper;

	@Autowired
	private PermOperationMapper operMapper;

	@Autowired
	private PermEntryMapper entryMapper;

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
		entry.setEntryKey("Merchant_list");
		entry.setDisplayName("获取商户列表");
		entry.setObject(obj);
		entry.setOperation(oper);
		entry.setComments("test");
		entry.setCreateDate(new Date());
		entry.setUpdateDate(entry.getCreateDate());
		oldId = entry.getID();
		count = entryMapper.insert(entry);
		newId = entry.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return entry;
	}

	public ColumnScopeEntity newColumnScopeEntity() {
		PermEntryEntity entry = newPermEntry();
		// PermEntryEntity entry = new PermEntryEntity();
		// entry.setID("126cf8bf-b31d-4061-848c-23592a7c4384");

		ColumnScopeEntity columnScope = new ColumnScopeEntity();
		columnScope.setID(SecurityUtils.UUID());
		columnScope.setPropertyName("密码");
		columnScope.setColumnName("encrypted_passwd");
		columnScope.setComments("test1");
		columnScope.setCreateDate(new Date());
		columnScope.setUpdateDate(columnScope.getCreateDate());
		columnScope.setPermEntryId(entry.getID());
		return columnScope;
	}

	public ColumnScopeEntity getInsertColumnScope() {
		ColumnScopeEntity columnScope = newColumnScopeEntity();
		String oldId = columnScope.getID();
		int count = mapper.insert(columnScope);
		String newId = columnScope.getID();
		assertTrue(count == 1);
		assertTrue(oldId.equals(newId));
		return columnScope;
	}

	@Test
	@Transactional
	public void TestInsertColumnScope() {
		getInsertColumnScope();
	}

	@Test
	@Transactional
	public void TestSelectColumnScope() {
		ColumnScopeEntity column = getInsertColumnScope();
		ColumnScopeEntity newColumn = mapper.selectOne(column.getID());
		newColumn.setPermEntryKey(null);
		assertTrue(column.equals(newColumn));
	}

	@Test
	@Transactional
	public void TestUpdateColumnScope() {
		ColumnScopeEntity column = getInsertColumnScope();
		ColumnScopeEntity newColumn = mapper.selectOne(column.getID());
		int count = mapper.update(newColumn);
		assertTrue(count == 1);
		newColumn.setPermEntryKey(null);
		assertTrue(column.equals(newColumn));
	}

	@Test
	@Transactional
	public void testDeleteColumnScope() {
		ColumnScopeEntity column = getInsertColumnScope();
		// delete the new city
		int count = mapper.delete(column.getID());
		assertTrue(count == 1);
	}

	@Test
	@Transactional
	public void testListColumnScopes() {
		getInsertColumnScope();
		List<ColumnScopeEntity> ColumnScopes = mapper.selectAll();
		assertTrue(ColumnScopes.size() > 0);
		System.out.println(ColumnScopes.size());
	}
}
