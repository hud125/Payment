package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum DeliveryStatusEnum {

	/**
	 * Processing
	 */
	PROCESSING,

	/**
	 * Succeed
	 */
	SUCCEED,

	/**
	 * Failed
	 */
	FAILED

}
