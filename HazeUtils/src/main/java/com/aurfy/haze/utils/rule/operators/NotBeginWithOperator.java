package com.aurfy.haze.utils.rule.operators;

public class NotBeginWithOperator extends BeginWithOperator {

	private static final long serialVersionUID = -5324002488389920105L;

	public NotBeginWithOperator() {
		super();
		setLabel("NOT_BEGIN_WITH");
	}

	@Override
	protected boolean satisfy(String propertyValue, String valuePattern) {
		return !super.satisfy(propertyValue, valuePattern);
	}

}
