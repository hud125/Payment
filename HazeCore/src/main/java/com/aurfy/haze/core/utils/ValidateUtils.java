package com.aurfy.haze.core.utils;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * this class overlaps part of the functions in HazeUtils in order to reduce the redundant project dependency.
 * 
 * @author hermano
 *
 */
public class ValidateUtils {

	private static final String REGEX_MONTH = "^(0[1-9]|1[0-2])$";
	private static final String REGEX_YEAR_SHORT = "^(1[5-9]|[2-9][0-9])$";

	private static final Pattern PATTERN_MONTH = getCaseInsensitivePattern(REGEX_MONTH);
	private static final Pattern PATTERN_YEAR_SHORT = getCaseInsensitivePattern(REGEX_YEAR_SHORT);

	private ValidateUtils() {
	}

	public static Pattern getCaseInsensitivePattern(String regex) {
		return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	}

	public static boolean isValidMonth(String str) {
		return str != null && PATTERN_MONTH.matcher(str).matches();
	}

	public static boolean isValidYearShort(String str) {
		return str != null && PATTERN_YEAR_SHORT.matcher(str).matches();
	}

	/**
	 * use deprecated date API to avoid Calendar problem when converting to c# code.
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date toZeroInCurrentDay(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return new Date(date.getYear(), date.getMonth(), date.getDate(), 0, 0, 0);
	}

	public static boolean isInSameDay(Date d1, Date d2) {
		if (d1 == null && d2 == null) {
			return true;
		} else if (d1 == null || d2 == null) {
			return false;
		}
		d1 = toZeroInCurrentDay(d1);
		d2 = toZeroInCurrentDay(d2);
		if (d1.equals(d2)) {
			return true;
		} else {
			return false;
		}
	}

}
