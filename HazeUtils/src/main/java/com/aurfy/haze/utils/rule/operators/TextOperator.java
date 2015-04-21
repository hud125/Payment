package com.aurfy.haze.utils.rule.operators;

import com.aurfy.haze.utils.ReflectionUtils;
import com.aurfy.haze.utils.rule.ValueType;
import com.aurfy.haze.utils.rule.exceptions.RuleException;

public abstract class TextOperator extends BasicOperator {

	private static final long serialVersionUID = 6681252876717136688L;

	public TextOperator() {
		super();
		setValueType(ValueType.Text);
	}

	protected boolean satisfy(String propertyValue, String valuePattern) throws RuleException {
		return false;
	}

	protected boolean satisfy(String propertyValue) throws RuleException {
		return false;
	}

	@Override
	public boolean satisfy(Object object, String propertyName, String valuePattern) throws RuleException {
		try {
			Object objValue = ReflectionUtils.invokeGetter(object, propertyName);
			String propertyValue = objValue == null ? null : objValue.toString();
			if (isValueRequired()) {
				return satisfy(propertyValue, valuePattern);
			} else {
				return satisfy(propertyValue);
			}
		} catch (Exception e) {
			return defaultExceptionHandle(e);
		}
	}

}
