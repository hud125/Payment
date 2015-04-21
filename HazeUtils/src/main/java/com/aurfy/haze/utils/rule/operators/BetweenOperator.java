package com.aurfy.haze.utils.rule.operators;

import java.math.BigDecimal;

import com.aurfy.haze.utils.rule.exceptions.RuleException;

/**
 * Between
 */
public class BetweenOperator extends NumericOperator {

	private static final long serialVersionUID = -6727866372509203493L;

	public BetweenOperator() {
		super();
		setLabel("BETWEEN");
	}

	@Override
	protected boolean satisfy(BigDecimal propertyValue, BigDecimal... valuePattern) throws RuleException {
		if (valuePattern.length < 2) {
			throw new RuleException("Require two parameter for Between Operator.");
		}
		return propertyValue != null && propertyValue.compareTo(valuePattern[0]) >= 0
				&& propertyValue.compareTo(valuePattern[1]) <= 0;
	}
}
