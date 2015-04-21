package com.aurfy.haze.core.model.settlement;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

/**
 * 周期结算，妥投结算 
 * 此项在生成清算信息时已决定，后续不再更改
 * 可参考：PaymentSummary中的deliveryDate的设计
 */
@UseEnumTypeHandler
public enum SettleConditionEnum {

	BY_PERIOD,
	
	BY_DELIVERY
}
