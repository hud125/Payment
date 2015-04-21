package com.aurfy.haze.core.model.settlement;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum DepositSettleStatusEnum {

	/**
	 * 保证金结算状态：1未结算/2已结算/3不结算 <br />
	 * 当发生退货时，退货那笔的保证金为0，DS=null。revise_flow中增加一笔S日=退货日的保证金退还（正数），同时原始交易的该状态从1转为3
	 */

	NOTSETTLE,

	SETTLED,

	NOSETTLE

}
