package com.aurfy.haze.utils.rule.operators;

import static com.aurfy.haze.utils.StringUtils.endsWith;

public class EndWithOperator extends TextOperator {

	private static final long serialVersionUID = -8861576511918216867L;

	public EndWithOperator() {
		super();
		setLabel("END_WITH");
	}

	@Override
	public boolean satisfy(String propertyValue, String valuePattern) {
		return propertyValue != null && endsWith(propertyValue, valuePattern);
	}

}
