package com.aurfy.haze.dao.perm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.UserMapper;
import com.aurfy.haze.dao.infrastructure.UserMapperTest;
import com.aurfy.haze.dao.perm.PermRelationMapper;
import com.aurfy.haze.dao.perm.PermRoleMapper;
import com.aurfy.haze.entity.infra.UserEntity;
import com.aurfy.haze.entity.perm.PermRelationEntity;
import com.aurfy.haze.entity.perm.PermRoleEntity;

public class PermRelationMapperTest extends PersistUnitTest {

	@Autowired
	private PermRelationMapper mapper;
	
	@Autowired
	private PermRoleMapper roleMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	private PermRelationEntity newPermRelationEntity(){
		PermRoleEntity role = new PermRoleMapperTest().newRoleEntity();
		String oldId = role.getID();
		int count = roleMapper.insert(role);
		String newId = role.getID();
		assertTrue(count == 1);
		assertTrue(oldId.equals(newId));
		
		UserEntity user = new UserMapperTest().createUser();
		// no user id set
		oldId = user.getID();
		count = userMapper.insert(user);
		newId = user.getID();
		assertEquals(oldId, newId);
		assertTrue(count == 1);
		
		PermRelationEntity prel = new PermRelationEntity();
		prel.setRole(role);
		prel.setUser(user);
		
		return prel;
	}

	private PermRelationEntity getPermRelationEntity(){
		PermRelationEntity prel = newPermRelationEntity();
		int count = mapper.insert(prel);
		assertTrue(count == 1);
		return prel;
	}
	
	@Test
	@Transactional
	public void TestInsertPermRelation(){
		getPermRelationEntity();
	}
	
	@Test
	@Transactional
	public void TestSelectPermRelationByUser(){
		PermRelationEntity prel = getPermRelationEntity();
		PermRelationEntity newPrel = mapper.selectPermRelationByUser(prel);
		assertTrue(prel.equals(newPrel));
	}
	
	@Test
	@Transactional
	public void TestSelectPermRelationByRole(){
		PermRelationEntity prel = getPermRelationEntity();
		PermRelationEntity newPrel = mapper.selectPermRelationByRole(prel);
		assertTrue(prel.equals(newPrel));
	}
	
	@Test
	@Transactional
	public void TestUpdatePermRelationByUser(){
		PermRelationEntity prel = getPermRelationEntity();
		PermRelationEntity newPrel = newPermRelationEntity();
		newPrel.setUser(prel.getUser());
		int count = mapper.updatePermRelationByUser(newPrel);
		assertTrue(count == 1);
		assertTrue(!prel.equals(newPrel));
	}
	
	@Test
	@Transactional
	public void TestDeletePermRelationByUserAndRole(){
		PermRelationEntity prel = getPermRelationEntity();
		int count = mapper.deletePermRelationByUserAndRole(prel);
		assertTrue(count == 1);
	}
	
	
	
}
