package com.aurfy.haze.validation;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.text.SimpleDateFormat;
import java.util.Map;

import com.aurfy.haze.exceptions.ValidationException;
import com.aurfy.haze.utils.DateUtils;

/**
 * @author hermano
 */
public class DateValidator extends BaseValidator implements Validator {

	private String format;

	public DateValidator() {

	}

	@Override
	public void init(Map<String, String> params) throws ValidationException {
		super.init(params);

		this.format = params.get("format");
		if (isEmpty(format)) {
			throw new ValidationException("parameter 'format' is required for " + getSimpleClassName());
		}
	}

	@Override
	protected Object validateNonNull(String value) {
		// try to convert to the specify format
		try {
			return DateUtils.parseDate(new SimpleDateFormat(getFormat()), value, true);
		} catch (Exception ex) {
			return new ValidationError(getFieldName(), "#{field.date.invalid}", new Object[] { value });
		}
	}

	public String getFormat() {
		return format;
	}

}