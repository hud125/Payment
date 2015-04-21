package com.aurfy.haze.utils.rule.operators;

public class IsNotNullOperator extends IsNullOperator {

	private static final long serialVersionUID = -19876308606843817L;

	public IsNotNullOperator() {
		super();
		setLabel("IS_NOT_NULL");
	}

	@Override
	public boolean satisfy(String propertyValue) {
		return !super.satisfy(propertyValue);
	}
}
