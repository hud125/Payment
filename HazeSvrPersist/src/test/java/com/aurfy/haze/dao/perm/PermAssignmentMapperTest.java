package com.aurfy.haze.dao.perm;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.perm.PermValueEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.perm.AssigneeClassifierEnum;
import com.aurfy.haze.entity.perm.PermAssignmentEntity;
import com.aurfy.haze.entity.perm.PermEntryEntity;
import com.aurfy.haze.entity.perm.PermObjectEntity;
import com.aurfy.haze.entity.perm.PermOperationEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class PermAssignmentMapperTest extends PersistUnitTest {

	@Autowired
	private PermAssignmentMapper mapper;

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

	private PermAssignmentEntity newPermAssignment() {
		PermEntryEntity entry = newPermEntry();
		// PermEntryEntity entry = new PermEntryEntity();
		// entry.setID("126cf8bf-b31d-4061-848c-23592a7c4384");

		PermAssignmentEntity ass = new PermAssignmentEntity();
		ass.setID(SecurityUtils.UUID());
		ass.setAssigneeId("e15820e1-a99b-43e6-a5d1-9ad8ff95d2db");
		ass.setAssigneeClassifier(AssigneeClassifierEnum.USER);
		ass.setPermEntry(entry);
		ass.setPermValue(PermValueEnum.GRANTED);
		ass.setComments("junitTest");
		ass.setOwnerId("e15820e1-a99b-43e6-a5d1-9ad8ff95d2db");
		ass.setCreateDate(new Date());
		ass.setUpdateDate(ass.getCreateDate());
		return ass;
	}

	private PermAssignmentEntity getPermAssignment() {
		PermAssignmentEntity ass = newPermAssignment();
		String oldId = ass.getID();
		int count = mapper.insert(ass);
		String newId = ass.getID();
		assertTrue(count == 1);
		assertTrue(oldId.equals(newId));
		return ass;
	}

	@Test
	@Transactional
	public void TestInsertPermAssignment() {
		getPermAssignment();
	}

}
