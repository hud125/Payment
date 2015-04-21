package com.aurfy.haze.service.api.perm;

import java.util.List;

import com.aurfy.haze.core.model.perm.ViewScope;
import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.bean.perm.PermAssignmentBean;

public interface PermissionService extends HazeService {

	/**
	 * Validate a given user against a certain permission entry.
	 * 
	 * @param userId
	 *            the unique user id
	 * @param permEntryKey
	 *            the key for the permission entry
	 * @return a permission assignment (might be denied).
	 */
	public PermAssignmentBean getAssignment(String userId, String permEntryKey);

	/**
	 * Get all permission assignments for a specified user (including denied permission).
	 * 
	 * @param userId
	 * @return
	 */
	public List<PermAssignmentBean> listPermission(String userId);
	
	/**
	 * Get viewScope by the given entryKey
	 * 
	 * @param entryKey
	 * @return
	 */
	public ViewScope getViewScopeByEntryKey(String entryKey);

}
