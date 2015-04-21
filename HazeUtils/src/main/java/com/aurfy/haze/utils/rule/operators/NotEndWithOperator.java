package com.aurfy.haze.utils.rule.operators;

public class NotEndWithOperator extends EndWithOperator {

	private static final long serialVersionUID = -5142644328356025247L;

	public NotEndWithOperator() {
		super();
		setLabel("NOT_END_WITH");
	}

	@Override
	public boolean satisfy(String propertyValue, String valuePattern) {
		return !super.satisfy(propertyValue, valuePattern);
	}
}
