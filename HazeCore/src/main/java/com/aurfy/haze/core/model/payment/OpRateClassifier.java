package com.aurfy.haze.core.model.payment;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum OpRateClassifier {

	/**
	 * 内扣
	 */
	INNER_CHARGE,
	
	/**
	 * 外扣
	 */
	EXTERNAL_CHARGE
}
