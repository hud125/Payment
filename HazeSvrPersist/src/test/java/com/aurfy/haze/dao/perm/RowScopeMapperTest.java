package com.aurfy.haze.dao.perm;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.perm.PermEntryEntity;
import com.aurfy.haze.entity.perm.PermObjectEntity;
import com.aurfy.haze.entity.perm.PermOperationEntity;
import com.aurfy.haze.entity.perm.RowScopeEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class RowScopeMapperTest extends PersistUnitTest {

	@Autowired
	private RowScopeMapper mapper;

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

	public RowScopeEntity newRowScopeEntity() {
		PermEntryEntity entry = newPermEntry();

		RowScopeEntity rowScope = new RowScopeEntity();
		rowScope.setID(SecurityUtils.UUID());
		rowScope.setEntityName("test");
		rowScope.setTableClause("AAAAA");
		rowScope.setWhereClause("AAAAA");
		rowScope.setComments("test1");
		rowScope.setCreateDate(new Date());
		rowScope.setUpdateDate(rowScope.getCreateDate());
		rowScope.setPermEntryId(entry.getID());
		return rowScope;
	}

	public RowScopeEntity getInsertRowScope() {
		RowScopeEntity rowScope = newRowScopeEntity();
		String oldId = rowScope.getID();
		int count = mapper.insert(rowScope);
		String newId = rowScope.getID();
		assertTrue(count == 1);
		assertTrue(oldId.equals(newId));
		return rowScope;
	}

	@Test
	@Transactional
	public void TestInsertRowScope() {
		getInsertRowScope();
	}

	@Test
	@Transactional
	public void TestSelectRowScope() {
		RowScopeEntity row = getInsertRowScope();
		RowScopeEntity newRow = mapper.selectOne(row.getID());
		newRow.setPermEntryKey(null);
		assertTrue(row.equals(newRow));
	}

	@Test
	@Transactional
	public void TestUpdateRowScope() {
		RowScopeEntity row = getInsertRowScope();
		RowScopeEntity newRow = mapper.selectOne(row.getID());
		int count = mapper.update(newRow);
		assertTrue(count == 1);
		newRow.setPermEntryKey(null);
		assertTrue(row.equals(newRow));
	}

	@Test
	@Transactional
	public void testDeleteRowScope() {
		RowScopeEntity row = getInsertRowScope();
		// delete the new city
		int count = mapper.delete(row.getID());
		assertTrue(count == 1);
	}

	@Test
	@Transactional
	public void testListRowScopes() {
		getInsertRowScope();
		int count = mapper.countAll();
		assertTrue(count > 0);
		List<RowScopeEntity> RowScopes = mapper.selectAll();
		assertTrue(RowScopes.size() > 0);
		
		assertTrue(RowScopes.size() == count);
	}
}
