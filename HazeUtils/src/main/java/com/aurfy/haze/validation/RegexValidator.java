package com.aurfy.haze.validation;

import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.aurfy.haze.exceptions.ValidationException;

/**
 * @author hermano
 */
public class RegexValidator extends TextValidator {

	private String pattern;
	private boolean caseSensitive;

	public RegexValidator() {

	}

	@Override
	public void init(Map<String, String> params) throws ValidationException {
		super.init(params);
		pattern = params.get("pattern");
		if (StringUtils.isBlank(pattern)) {
			throw new ValidationException("'pattern' can not be blank for " + getSimpleClassName());
		}
		String caseSensitiveStr = params.get("caseSensitive");
		if (StringUtils.isNotEmpty(caseSensitiveStr)) {
			caseSensitive = Boolean.parseBoolean(caseSensitiveStr);
		}
	}

	@Override
	public Object validate(String value) {
		Object obj = super.validateNonNull(value);
		if (obj instanceof ValidationError) {
			return obj;
		}
		//
		Pattern pattern;
		if (isCaseSensitive()) {
			pattern = Pattern.compile(getPattern());
		} else {
			pattern = Pattern.compile(getPattern(), Pattern.CASE_INSENSITIVE);
		}

		if (pattern.matcher(value).matches()) {
			return value;
		} else {
			return new ValidationError(getFieldName(), "#{field.regex.notmatch}", new Object[] { value });
		}
	}

	public String getPattern() {
		return pattern;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

}