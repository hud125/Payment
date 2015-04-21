package com.aurfy.haze.validation;

import java.util.Map;

import com.aurfy.haze.exceptions.ValidationException;
import com.aurfy.haze.utils.ReflectionUtils;

/**
 * @author hermano
 */
public class EnumValidator extends PseudoEnumValidator implements Validator {

	public EnumValidator() {

	}

	@Override
	public void init(Map<String, String> params) throws ValidationException {
		super.init(params);
		if (!Enum.class.isAssignableFrom(getTargetClass())) {
			throw new ValidationException("class '" + getTargetClass().getName() + "' is not of enumeration type");
		}
	}

	@Override
	protected String getParseMethodName(Map<String, String> params) throws ValidationException {
		return "valueOf";
	}

	@Override
	protected Object validateNonNull(String value) {
		Object obj;
		try {
			obj = ReflectionUtils.invokeStaticMethod(getTargetClass(), getMethod(), value, String.class);
		} catch (Exception e) {
			obj = null;
		}
		if (obj == null) {
			return new ValidationError(getFieldName(), "#{field.enum.notparsable}", new String[] { value });
		}
		return obj;
	}

}