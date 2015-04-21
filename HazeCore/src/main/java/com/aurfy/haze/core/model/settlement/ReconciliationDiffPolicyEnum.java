package com.aurfy.haze.core.model.settlement;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

/**
 * 勾兑时针对银行差错账作何处理：1、自动退货；2、自动补单（状态更新为当天成功）
 * 
 * @author rocket
 *
 */
@UseEnumTypeHandler
public enum ReconciliationDiffPolicyEnum {

	AUTO_REFUND,

	AUTO_SUPPLEMENT
}
