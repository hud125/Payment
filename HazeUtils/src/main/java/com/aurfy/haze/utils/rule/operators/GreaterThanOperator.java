package com.aurfy.haze.utils.rule.operators;

import java.math.BigDecimal;

public class GreaterThanOperator extends NumericOperator {

	private static final long serialVersionUID = -3463847033676595818L;

	public GreaterThanOperator() {
		super();
		setLabel(">");
	}

	@Override
	protected boolean satisfy(BigDecimal propertyValue, BigDecimal... valuePattern) {
		return propertyValue != null && propertyValue.compareTo(valuePattern[0]) > 0;
	}

}
