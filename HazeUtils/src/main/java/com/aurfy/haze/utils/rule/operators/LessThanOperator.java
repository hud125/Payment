package com.aurfy.haze.utils.rule.operators;

import java.math.BigDecimal;

public class LessThanOperator extends NumericOperator {

	private static final long serialVersionUID = -6039824731143535983L;

	public LessThanOperator() {
		super();
		setLabel("<");
	}

	@Override
	protected boolean satisfy(BigDecimal propertyValue, BigDecimal... valuePattern) {
		return propertyValue != null && propertyValue.compareTo(valuePattern[0]) < 0;
	}

}
