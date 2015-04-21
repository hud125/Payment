package com.aurfy.haze.utils.rule.operators;

import com.aurfy.haze.utils.StringUtils;

public class TextEqualsOperator extends TextOperator {

	private static final long serialVersionUID = 8705892346732829649L;

	public TextEqualsOperator() {
		super();
		setLabel("EQUALS");
	}

	@Override
	public boolean satisfy(String propertyValue, String valuePattern) {
		return propertyValue != null && StringUtils.equals(propertyValue, valuePattern);
	}

}
