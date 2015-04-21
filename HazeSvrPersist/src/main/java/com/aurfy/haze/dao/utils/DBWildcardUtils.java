package com.aurfy.haze.dao.utils;

import java.util.regex.Pattern;

import com.google.common.base.Objects;

public class DBWildcardUtils {

	private static final char[] WILDCARDS = new char[] { '%', '_', '?', '#', '&', '|', '\\', '/' };
	private static final Pattern PATTERN_WILDCARDS = buildPattern();

	private static final Pattern buildPattern() {
		StringBuilder builder = new StringBuilder();
		builder.append("([");
		for (char c : WILDCARDS) {
			if (Objects.equal(c, '\\')) {
				builder.append('\\');
			}
			builder.append(c);
		}
		builder.append("])");
		return Pattern.compile(builder.toString(), Pattern.CASE_INSENSITIVE);
	}

	public static String escapeWildcards(String input) {
		if (input == null) {
			return null;
		}
		return PATTERN_WILDCARDS.matcher(input).replaceAll("\\\\$1");
	}
}
