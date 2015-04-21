package com.aurfy.haze.utils.rule.operators;

import static com.aurfy.haze.utils.StringUtils.contains;

public class ContainsOperator extends TextOperator {

	private static final long serialVersionUID = 4295169547413408101L;

	public ContainsOperator() {
		super();
		setLabel("CONTAINS");
	}

	@Override
	public boolean satisfy(String propertyValue, String valuePattern) {
		return propertyValue != null && contains(propertyValue, valuePattern);
	}

}
