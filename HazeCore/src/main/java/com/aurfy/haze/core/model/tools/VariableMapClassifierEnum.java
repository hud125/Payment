package com.aurfy.haze.core.model.tools;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum VariableMapClassifierEnum {
	/**
	 * 商户以妥投方式清算时，最长妥投等待周期（过了此天数，未申明妥投也进行结算）
	 */
	MerSettleDeliveryDeadlineDay,
	
	/**
	 * 商户T+N结算周期中的N（自然日）
	 */
	MerSettlePeriodDay
}
