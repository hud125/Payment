package com.aurfy.haze.utils.rule.operators;

import java.math.BigDecimal;

public class NumericNeOperator extends NumericEqOperator {

	private static final long serialVersionUID = 6580582533885813958L;

	public NumericNeOperator() {
		super();
		setLabel("!=");
	}

	@Override
	public boolean satisfy(BigDecimal propertyValue, BigDecimal... valuePattern) {
		return propertyValue != null && propertyValue.compareTo(valuePattern[0]) != 0;
	}

}
