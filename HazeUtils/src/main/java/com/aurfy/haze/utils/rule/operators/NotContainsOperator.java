package com.aurfy.haze.utils.rule.operators;

public class NotContainsOperator extends ContainsOperator {

	private static final long serialVersionUID = 4986314141047898690L;

	public NotContainsOperator() {
		super();
		setLabel("NOT_CONTAINS");
	}

	@Override
	public boolean satisfy(String propertyValue, String valuePattern) {
		return !super.satisfy(propertyValue, valuePattern);
	}

}
