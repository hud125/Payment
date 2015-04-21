package com.aurfy.haze.core.model.configuration.rate;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum RateAlgorithmEnum {

	/**
	 * 计费算法：按百分比，按固定金额，按百分比+固定金额（退货时不退固定金额部分），按阶梯等
	 */
	
	PERCENTAGE,
	
	FIXEDAMOUNT,
	
	PERCENTAGEPLUSFIXEDAMOUNT,
	
	STEPPED
	
	
}
