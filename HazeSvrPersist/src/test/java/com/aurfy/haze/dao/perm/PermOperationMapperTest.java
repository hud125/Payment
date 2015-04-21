package com.aurfy.haze.dao.perm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.perm.PermOperationEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class PermOperationMapperTest extends PersistUnitTest {

	@Autowired
	private PermOperationMapper mapper;

	public PermOperationEntity newPermOperation() {
		PermOperationEntity op = new PermOperationEntity();
		// no op id set
		op.setID(SecurityUtils.UUID());
		op.setOperationName("listMerchants");
		op.setDisplayName("获取客户列表");
		op.setComments("test");
		op.setCreateDate(new Date());
		op.setUpdateDate(op.getCreateDate());
		return op;
	}

	public PermOperationEntity getNewPermOperation() {
		PermOperationEntity op = newPermOperation();
		String oldId = op.getID();
		int count = mapper.insert(op);
		String newId = op.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return op;
	}

	@Test
	@Transactional
	public void TestInsertPermOperation() {
		getNewPermOperation();
	}

	@Test
	@Transactional
	public void testSelectPermOperation() {
		// get permOperation by the given id
		PermOperationEntity op = getNewPermOperation();
		PermOperationEntity op2 = mapper.selectOne(op.getID());
		assertNotNull(op);
		assertTrue(op.equals(op2));
	}

	@Test
	@Transactional
	public void testUpdatePermOperation() {
		PermOperationEntity op = getNewPermOperation();
		PermOperationEntity newop = new PermOperationEntity();
		newop.setOperationName("listUsers2");
		newop.setDisplayName("获取用户列表2");
		newop.setComments("test2");
		newop.setCreateDate(new Date());
		newop.setUpdateDate(newop.getCreateDate());
		newop.setID(op.getID());

		int count = mapper.update(newop);
		PermOperationEntity newop2 = mapper.selectOne(newop.getID());
		assertTrue(count == 1);
		assertTrue(!op.equals(newop));
		assertTrue(newop.equals(newop2));
	}

	@Test
	@Transactional
	public void testDeletePermOperation() {
		PermOperationEntity op = getNewPermOperation();
		// delete the new permOperation
		int count = mapper.delete(op.getID());
		assertTrue(count == 1);
	}

}
