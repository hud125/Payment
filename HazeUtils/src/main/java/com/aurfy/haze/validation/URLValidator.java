package com.aurfy.haze.validation;

import java.util.Map;

import com.aurfy.haze.exceptions.ValidationException;
import com.aurfy.haze.utils.RegexUtils;

/**
 * @author hermano
 */
public class URLValidator extends TextValidator {

	public URLValidator() {

	}

	@Override
	public void init(Map<String, String> params) throws ValidationException {
		super.init(params);
	}

	@Override
	protected Object validateNonNull(String value) {
		Object obj = super.validateNonNull(value);
		if (obj instanceof ValidationError) {
			return obj;
		}
		// Q: need to decode URL first?
		if (RegexUtils.isValidUrl(value)) {
			return value;
		} else {
			return new ValidationError(getFieldName(), "#{field.url.invalid}");
		}
	}

}