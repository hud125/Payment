package com.aurfy.haze.utils.rule.operators;

import static org.apache.commons.lang3.StringUtils.split;

public class TextInOperator extends TextOperator {

	private static final long serialVersionUID = 6922313852655032746L;

	public TextInOperator() {
		super();
		setLabel("IN");
	}

	@Override
	public boolean satisfy(String propertyValue, String valuePattern) {
		String[] values = split(valuePattern, ',');

		if (propertyValue == null) {
			return false;
		} else {
			for (String value : values) {
				if (value.equals(propertyValue)) {
					return true;
				}
			}
		}
		return false;
	}
}
