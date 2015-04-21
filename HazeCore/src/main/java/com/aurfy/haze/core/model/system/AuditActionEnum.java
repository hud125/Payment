package com.aurfy.haze.core.model.system;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

/**
 * Audit Action Name.
 * 
 * @author hermano
 *
 */
@UseEnumTypeHandler
public enum AuditActionEnum {
	
	/**
	 * Validation
	 */
	VALIDATION,

	/**
	 * Login
	 */
	LOGIN,

	/**
	 * Approve
	 */
	APPROVE,

	/**
	 * Reject
	 */
	REJECT,
	
	/**
	 * SEND
	 */
	SEND
}
