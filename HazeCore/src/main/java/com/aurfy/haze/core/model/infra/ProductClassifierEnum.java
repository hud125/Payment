package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum ProductClassifierEnum {
	/**
	 * Secure Pay
	 */
	SecurePay,

	/**
	 * Express Pay
	 */
	ExpressPay,

	/**
	 * Token Pay
	 */
	TokenPay,

	/**
	 * MPos Pay
	 */
	MPosPay,

}
