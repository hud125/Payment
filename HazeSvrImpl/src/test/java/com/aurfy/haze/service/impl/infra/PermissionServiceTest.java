package com.aurfy.haze.service.impl.infra;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.service.api.perm.PermissionService;
import com.aurfy.haze.service.bean.perm.PermAssignmentBean;
import com.aurfy.haze.service.impl.ServiceUnitTest;

public class PermissionServiceTest extends ServiceUnitTest {

	@Autowired
	private PermissionService service;
	
	
	@Test
	@Transactional
	public void TestListPermission(){
		String userId = "e15820e1-a99b-43e6-a5d1-9ad8ff95d2db";
		List<PermAssignmentBean> permAssignmentBeans = service.listPermission(userId);
		assertTrue(permAssignmentBeans.size() > 0);
	}
	
	@Test
	@Transactional
	public void TestGetAssignment(){
		String userId = "e15820e1-a99b-43e6-a5d1-9ad8ff95d2db";
		String permEntryKey = "Console_Channel_Acquirer_list";
//		USER_PERM_PROVIDER
		List<PermAssignmentBean> permAssignmentBeans = service.listPermission(userId);
		assertTrue(permAssignmentBeans.size() > 0);
		PermAssignmentBean permAssignmentBean = service.getAssignment(userId, permEntryKey);
		assertTrue(permAssignmentBean != null);
		
	}
	
}
