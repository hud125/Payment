package com.aurfy.haze.utils.rule.operators;

import java.math.BigDecimal;

public class NumericEqOperator extends NumericOperator {

	private static final long serialVersionUID = -1771258089529993448L;

	public NumericEqOperator() {
		super();
		setLabel("=");
	}

	@Override
	protected boolean satisfy(BigDecimal propertyValue, BigDecimal... valuePattern) {
		return propertyValue != null && propertyValue.compareTo(valuePattern[0]) == 0;
	}

}
