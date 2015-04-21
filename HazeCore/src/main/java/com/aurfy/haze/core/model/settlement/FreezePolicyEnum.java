package com.aurfy.haze.core.model.settlement;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum FreezePolicyEnum {

	// 资金冻结策略（for 退货&拒付）：当日冻结（清算时自动操作）；不自动冻结

	AUTO_FREEZE,

	NO_FREEZE

}
