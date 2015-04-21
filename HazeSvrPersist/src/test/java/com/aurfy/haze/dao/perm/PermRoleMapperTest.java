package com.aurfy.haze.dao.perm;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.perm.PermRoleEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class PermRoleMapperTest extends PersistUnitTest {

	@Autowired
	private PermRoleMapper mapper;
	
	public PermRoleEntity newRoleEntity(){
		PermRoleEntity role = new PermRoleEntity();
		role.setID(SecurityUtils.UUID());
		role.setName("商户"+RandomStringUtils.randomAlphanumeric(6));
		role.setComments("test1");
		role.setCreateDate(new Date());
		role.setUpdateDate(role.getCreateDate());
		return role;
	}
	
	public PermRoleEntity getInsertRole(){
		PermRoleEntity role = newRoleEntity();
		String oldId = role.getID();
		int count = mapper.insert(role);
		String newId = role.getID();
		assertTrue(count == 1);
		assertTrue(oldId.equals(newId));
		return role;
	}
	
	@Test
	@Transactional
	public void TestInsertRole(){
		getInsertRole();
	}
	
	@Test
	@Transactional
	public void TestSelectRole(){
		PermRoleEntity role = getInsertRole();
		PermRoleEntity newRole = mapper.selectOne(role.getID());
		assertTrue(newRole.equals(role));
	}
	
	@Test
	@Transactional
	public void TestUpdateRole(){
		PermRoleEntity role = getInsertRole();
		PermRoleEntity newRole = mapper.selectOne(role.getID());
		int count = mapper.update(role);
		assertTrue(count == 1);
		assertTrue(newRole.equals(role));
	}
	
	
}
