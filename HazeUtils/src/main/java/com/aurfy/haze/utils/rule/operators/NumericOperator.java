package com.aurfy.haze.utils.rule.operators;

import java.math.BigDecimal;

import static com.aurfy.haze.utils.ReflectionUtils.invokeGetter;

import com.aurfy.haze.utils.rule.ValueType;
import com.aurfy.haze.utils.rule.exceptions.RuleException;

public abstract class NumericOperator extends BasicOperator {

	private static final long serialVersionUID = 2153353595891869234L;

	public NumericOperator() {
		super();
		setValueType(ValueType.Numeric);
	}

	protected BigDecimal[] getNumericValues(String value) throws RuleException {
		if (value == null) {
			throw new RuleException("Property value can not be null.");
		} else {
			String[] strs = value.split("\\s*,\\s*");
			BigDecimal[] result = new BigDecimal[strs.length];
			for (int i = 0; i < strs.length; i++) {
				result[i] = new BigDecimal(strs[i]);
			}
			return result;
		}
	}

	protected abstract boolean satisfy(BigDecimal propertyValue, BigDecimal... valuePattern) throws RuleException;

	@Override
	public boolean satisfy(Object object, String propertyName, String valuePattern) throws RuleException {
		try {
			// TODO: Format long type
			BigDecimal propertyValue = new BigDecimal(invokeGetter(object, propertyName).toString());
			if (isValueRequired()) {
				return satisfy(propertyValue, getNumericValues(valuePattern));
			} else {
				return satisfy(propertyValue);
			}
		} catch (Exception e) {
			return defaultExceptionHandle(e);
		}
	}
}
