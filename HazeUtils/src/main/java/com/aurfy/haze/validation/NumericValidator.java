package com.aurfy.haze.validation;

import java.math.BigDecimal;
import java.util.Map;

import com.aurfy.haze.exceptions.ValidationException;

/**
 * @author hermano
 */
public class NumericValidator extends BaseValidator implements Validator {

	private BigDecimal min;
	private BigDecimal max;

	public NumericValidator() {

	}

	@Override
	public void init(Map<String, String> params) throws ValidationException {
		super.init(params);

		min = getBigDecimal(params, "min");
		max = getBigDecimal(params, "max");

		if (min != null && max != null && max.subtract(min).signum() < 0) {
			throw new ValidationException("'max' must be larger than or equal to 'min' for " + getSimpleClassName());
		}

	}

	private BigDecimal getBigDecimal(Map<String, String> params, String key) throws ValidationException {
		if (params.containsKey(key)) {
			try {
				BigDecimal bd = new BigDecimal(params.get(key));
				return bd;
			} catch (NumberFormatException e) {
				throw new ValidationException("'" + key + "' contains an valid number for " + getSimpleClassName(), e);
			}
		} else {
			// no limitation
			return null;
		}
	}

	@Override
	protected Object validateNonNull(String value) {
		try {
			BigDecimal bd = new BigDecimal(value);
			if (bd.subtract(min).signum() < 0) {
				return new ValidationError(getFieldName(), "#{field.numeric.min}", new Object[] { getMin() });
			}
			if (bd.subtract(max).signum() > 0) {
				return new ValidationError(getFieldName(), "#{field.numeric.max}", new Object[] { getMax() });
			}
			return bd;
		} catch (NumberFormatException e) {
			return new ValidationError(getFieldName(), "#{field.numeric.invalid}", new Object[] { value });
		}
	}

	public BigDecimal getMin() {
		return min;
	}

	public BigDecimal getMax() {
		return max;
	}
}