package com.aurfy.haze.utils.rule.operators;

import static com.aurfy.haze.utils.DateUtils.parseDateLongCompact;
import static com.aurfy.haze.utils.ReflectionUtils.invokeGetter;

import java.util.Date;

import com.aurfy.haze.utils.rule.ValueType;
import com.aurfy.haze.utils.rule.exceptions.RuleException;

public class DateOperator extends BasicOperator {

	private static final long serialVersionUID = 4583120271672942252L;

	public DateOperator() {
		super();
		setValueType(ValueType.Date);
		setLabel("DATE_BETWEEN");
	}

	protected Date[] getDateValues(String value) throws RuleException {
		if (value == null) {
			throw new RuleException("Property value can not be null.");
		} else {
			String[] strs = value.split("\\s*,\\s*");
			if (strs.length != 2) {
				throw new RuleException("Require two parameters for Between Operator.");
			}
			Date[] result = new Date[strs.length];
			for (int i = 0; i < strs.length; i++) {
				// -1 means no limit
				if (strs[i].equals("-1")) {
					result[i] = null;
				} else {
					result[i] = parseDateLongCompact(strs[i]);
				}
			}
			return result;
		}
	}

	protected boolean satisfy(Date propertyValue, Date... valuePattern) throws RuleException {
		if (propertyValue == null) {
			return false;
		} else {
			Date beginDate = valuePattern[0];
			Date endDate = valuePattern[1];
			boolean isEqualOrAfterBeginDate = beginDate == null ? true : propertyValue.equals(beginDate)
					|| propertyValue.after(beginDate);
			boolean isEqualOrBeforeEndDate = endDate == null ? true : propertyValue.equals(endDate)
					|| propertyValue.before(endDate);
			return isEqualOrAfterBeginDate && isEqualOrBeforeEndDate;
		}
	}

	@Override
	public boolean satisfy(Object object, String propertyName, String valuePattern) throws RuleException {
		try {
			Date propertyValue = (Date) invokeGetter(object, propertyName);
			if (isValueRequired()) {
				return satisfy(propertyValue, getDateValues(valuePattern));
			} else {
				return false;
			}
		} catch (Exception e) {
			return defaultExceptionHandle(e);
		}
	}
}
