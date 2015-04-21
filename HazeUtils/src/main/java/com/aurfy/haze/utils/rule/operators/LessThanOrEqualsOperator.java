package com.aurfy.haze.utils.rule.operators;

import java.math.BigDecimal;

public class LessThanOrEqualsOperator extends NumericOperator {

	private static final long serialVersionUID = -2297457881257710025L;

	public LessThanOrEqualsOperator() {
		super();
		setLabel("<=");
	}

	@Override
	protected boolean satisfy(BigDecimal propertyValue, BigDecimal... valuePattern) {
		return propertyValue != null && propertyValue.compareTo(valuePattern[0]) <= 0;
	}

}
