package com.aurfy.haze.core.model.payment;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum PayStatusEnum {
	// 当需要自动或人工处理某些流水时（如需退货等），将此标志位置为特殊条件，方便查询
	// 状态值：初始；等待；成功；失败
	
	/**
	 * Initial 初始
	 */
	INITIAL,

	/**
	 * Pending 等待
	 */
	PENDING,

	/**
	 * 成功
	 */
	SUCCESS,

	/**
	 * 失败
	 */
	FAIL,

}
