package com.aurfy.haze.core.model.system;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

/**
 * Action Result.
 * 
 * @author hermano
 *
 */
@UseEnumTypeHandler
public enum ActionResultEnum {

	/**
	 * Success
	 */
	SUCCESS,

	/**
	 * Failure
	 */
	FAILURE,

	/**
	 * Unknown
	 */
	UNKNOWN
}
