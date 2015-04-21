package com.aurfy.haze.core.model.txn;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum TxnStatusEnum {

	/**
	 * invalid order, will not process
	 */
	INVALID,

	/**
	 * unpaid order
	 */
	NEW,

	/**
	 * pay in progress
	 */
	PROCESSING,

	/**
	 * unsuccessful paid order
	 */
	UNSUCCESSFUL,

	/**
	 * paid order
	 */
	PAID
}
