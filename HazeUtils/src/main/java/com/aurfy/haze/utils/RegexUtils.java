package com.aurfy.haze.utils;

import java.util.regex.Pattern;

public final class RegexUtils {

	public static final String REGEX_CHINESE_CHARS = "\u4e00-\u9fa5\\（\\）";
	public static final String REGEX_CELLPHONE = "1[3458]\\d{9}";
	public static final String REGEX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	public static final String REGEX_CARD_NUMBER = "\\d{13,19}";
	public static final String REGEX_CREDENTIAL_NAME = "[^!$%\\^&*?<>]{2,32}";
	public static final String REGEX_MD5 = "[^!$%\\^&*?<>]{2,32}";
	public static final String REGEX_BASE64 = "^(?:[a-z0-9+/]{4})*(?:[a-z0-9+/]{2}==|[a-z0-9+/]{3}=)?$";
	public static final String REGEX_MONTH = "^(0[1-9]|1[0-2])$";
	public static final String REGEX_YEAR_SHORT = "^(1[5-9]|[2-9][0-9])$";

	public static final Pattern REGEX_FOREIGN_TELEPHONE = getCaseInsensitivePattern("^\\d{8}$");;

	private static final Pattern PATTERN_URL = getCaseInsensitivePattern("^http(s)?:\\/\\/(([\\w-]+(\\.[\\w-]+)+)|localhost)(:\\d+)?(\\/[\\w-?%=+&\\.:!]*)*(#\\w+)?$");
	private static final Pattern PATTERN_PARTIAL_URL = getCaseInsensitivePattern("^(\\/[\\w-?%=+&\\.:!]*)*(#\\w+)?$");
	private static final Pattern PATTERN_CELLPHONE = getCaseInsensitivePattern("^" + REGEX_CELLPHONE + "$");
	private static final Pattern PATTERN_EMAIL = getCaseInsensitivePattern("^" + REGEX_EMAIL + "$");
	private static final Pattern PATTERN_CARD_NUMBER = getCaseInsensitivePattern("^" + REGEX_CARD_NUMBER + "$");
	private static final Pattern PATTERN_CREDENTIAL_NAME = getCaseInsensitivePattern("^" + REGEX_CREDENTIAL_NAME + "$");
	private static final Pattern PATTERN_MD5 = getCaseInsensitivePattern(REGEX_MD5);
	private static final Pattern PATTERN_BASE64 = getCaseInsensitivePattern(REGEX_BASE64);

	private static final Pattern PATTERN_MONTH = getCaseInsensitivePattern(REGEX_MONTH);
	private static final Pattern PATTERN_YEAR_SHORT = getCaseInsensitivePattern(REGEX_YEAR_SHORT);

	public static Pattern getCaseSensitivePattern(String regex) {
		return Pattern.compile(regex);
	}

	public static Pattern getCaseInsensitivePattern(String regex) {
		return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	}

	public static boolean isForeignTelephone(String number) {
		return number == null ? false : REGEX_FOREIGN_TELEPHONE.matcher(number).matches();
	}

	public static boolean isValidUrl(String url) {
		return url == null ? false : PATTERN_URL.matcher(url).matches();
	}

	public static boolean isValidPartialUrl(String partialUrl) {
		return partialUrl == null ? false : PATTERN_PARTIAL_URL.matcher(partialUrl).matches();
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		return phoneNumber == null ? false : PATTERN_CELLPHONE.matcher(phoneNumber).matches();
	}

	public static boolean isValidEmail(String email) {
		return email == null ? false : PATTERN_EMAIL.matcher(email).matches();
	}

	public static boolean isValidCardNumber(String cardNumber) {
		return cardNumber == null ? false : PATTERN_CARD_NUMBER.matcher(cardNumber).matches();
	}

	public static boolean isValidCredentialName(String credentialName) {
		return credentialName == null ? false
				: (PATTERN_CREDENTIAL_NAME.matcher(credentialName).matches() && StringUtils
						.getLength4GBKCharset(credentialName) <= 32);
	}

	/**
	 * detect if the given string represents a md5 hash
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMD5String(String str) {
		return str != null && PATTERN_MD5.matcher(str).matches();
	}

	public static boolean isBase64String(String str) {
		// some base 64 implementations may have \r\n as line separator.
		return str != null && PATTERN_BASE64.matcher(str.replace("\n", "").replace("\r", "")).matches();
	}

	public static boolean isValidMonth(String str) {
		return str != null && PATTERN_MONTH.matcher(str).matches();
	}

	public static boolean isValidYearShort(String str) {
		return str != null && PATTERN_YEAR_SHORT.matcher(str).matches();
	}
}
