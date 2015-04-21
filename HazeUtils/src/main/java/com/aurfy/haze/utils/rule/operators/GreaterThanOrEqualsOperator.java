package com.aurfy.haze.utils.rule.operators;

import java.math.BigDecimal;

public class GreaterThanOrEqualsOperator extends NumericOperator {

	private static final long serialVersionUID = -8331064293975818890L;

	public GreaterThanOrEqualsOperator() {
		super();
		setLabel(">=");
	}

	@Override
	protected boolean satisfy(BigDecimal propertyValue, BigDecimal... valuePattern) {
		return propertyValue != null && propertyValue.compareTo(valuePattern[0]) >= 0;
	}

}
