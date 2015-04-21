package com.aurfy.haze.core.model.txn;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum TxnTypeEnum {

	/**
	 * Purchase 消费
	 */
	PURCHASE,

	/**
	 * Authorization 预授权
	 */
	AUTH,

	/**
	 * Authorization Capture 预授权完成
	 */
	AUTH_CAPTURE,

	/**
	 * Authorization Cancellation 预授权取消
	 */
	AUTH_CANCEL,

	/**
	 * Void 撤销，应根据不同的交易类型进行区分  TODO
	 */
	VOID,

	/**
	 * Refund 退货
	 */
	REFUND,

	/**
	 * 冲正
	 */
	REVERSAL,

	/**
	 * Chargeback 拒付
	 */
	CHARGEBACK,

}
