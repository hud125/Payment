package com.aurfy.haze.validation;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.aurfy.haze.exceptions.ValidationException;

/**
 * @author hermano
 */
public class TextValidator extends BaseValidator implements Validator {

	private int minLength;
	private int maxLength;
	protected static int EMPTY_LENGTH_VALUE = -1;

	public TextValidator() {
		super();
	}

	@Override
	public void init(Map<String, String> params) throws ValidationException {
		super.init(params);
		minLength = getInt(params, "minLength");
		maxLength = getInt(params, "maxLength");
		if (minLength > EMPTY_LENGTH_VALUE && maxLength > EMPTY_LENGTH_VALUE && minLength > maxLength) {
			throw new ValidationException("'maxLength' must be larger than or equal to 'minLength' for "
					+ getSimpleClassName());
		}
	}

	private int getInt(Map<String, String> params, String key) throws ValidationException {
		if (params.containsKey(key)) {
			try {
				int result = Integer.parseInt(params.get(key));
				if (result < 0 || result > Integer.MAX_VALUE) {
					throw new ValidationException("'" + key + "' must be in range of [0, " + Integer.MAX_VALUE
							+ ") for " + getSimpleClassName());
				}
				return result;
			} catch (NumberFormatException e) {
				throw new ValidationException("'" + key + "' must be an integer for " + getSimpleClassName(), e);
			}
		} else {
			// no limitation
			return EMPTY_LENGTH_VALUE;
		}
	}

	@Override
	protected Object validateNonNull(String value) {
		int length = StringUtils.length(value);
		if (getMinLength() > EMPTY_LENGTH_VALUE && length < getMinLength()) {
			return new ValidationError(getFieldName(), "#{field.text.minLength}", new Object[] { getMinLength() });
		}
		if (getMaxLength() > EMPTY_LENGTH_VALUE && length > getMaxLength()) {
			return new ValidationError(getFieldName(), "#{field.text.maxLength}", new Object[] { getMaxLength() });
		}
		return value;
	}

	public int getMinLength() {
		return minLength;
	}

	public int getMaxLength() {
		return maxLength;
	}

}