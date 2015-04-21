package com.aurfy.haze.dao.perm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.perm.PermObjectEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class PermObjectMapperTest extends PersistUnitTest {

	@Autowired
	private PermObjectMapper mapper;

	public PermObjectEntity newPermObject() {
		PermObjectEntity obj = new PermObjectEntity();
		// no obj id set
		obj.setID(SecurityUtils.UUID());
		obj.setObjectName("Home");
		obj.setDisplayName("主页");
		obj.setComments("test");
		obj.setCreateDate(new Date());
		obj.setUpdateDate(obj.getCreateDate());
		return obj;
	}

	public PermObjectEntity getNewPermObject() {
		PermObjectEntity obj = newPermObject();
		String oldId = obj.getID();
		int count = mapper.insert(obj);
		String newId = obj.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return obj;
	}

	@Test
	@Transactional
	public void TestInsertPermObject() {
		getNewPermObject();
	}

	@Test
	@Transactional
	public void testSelectPermObject() {
		// get permObject by the given id
		PermObjectEntity obj = getNewPermObject();
		PermObjectEntity obj2 = mapper.selectOne(obj.getID());
		assertNotNull(obj);
		assertTrue(obj.equals(obj2));
	}

	@Test
	@Transactional
	public void testUpdatePermObject() {
		PermObjectEntity obj = getNewPermObject();
		PermObjectEntity newObj = new PermObjectEntity();
		newObj.setObjectName("HomeController2");
		newObj.setDisplayName("主页2");
		newObj.setComments("test2");
		newObj.setCreateDate(new Date());
		newObj.setUpdateDate(newObj.getCreateDate());
		newObj.setID(obj.getID());

		int count = mapper.update(newObj);
		PermObjectEntity newObj2 = mapper.selectOne(newObj.getID());
		assertTrue(count == 1);
		assertTrue(!obj.equals(newObj));
		assertTrue(newObj.equals(newObj2));
	}

	@Test
	@Transactional
	public void testDeletePermObject() {
		PermObjectEntity obj = getNewPermObject();
		// delete the new permObject
		int count = mapper.delete(obj.getID());
		assertTrue(count == 1);
	}

}
