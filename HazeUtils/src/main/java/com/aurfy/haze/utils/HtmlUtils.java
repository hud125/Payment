package com.aurfy.haze.utils;

import static com.aurfy.haze.utils.StringUtils.DEFAULT_ENCODING;
import static com.aurfy.haze.utils.StringUtils.DEFAULT_JOINER;
import static com.aurfy.haze.utils.StringUtils.formatMessage;
import static org.apache.commons.lang3.StringUtils.indexOf;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.length;
import static org.apache.commons.lang3.StringUtils.split;
import static org.apache.commons.lang3.StringUtils.substring;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public final class HtmlUtils {

	private static final String HIDDEN_INPUT_TPL = "<input type=\"hidden\" id=\"{0}\" name=\"{0}\" value=\"{1}\" />";
	private static final char NEW_LINE_CHAR = '\n';

	public static String mapToHiddenInputs(Map<String, ? extends Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer buffer = new StringBuffer(map.size());
		for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
			buffer.append(mapEntryToHiddenInput(entry));
			buffer.append(NEW_LINE_CHAR);
		}
		// buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}

	private static String mapEntryToHiddenInput(Map.Entry<String, ? extends Object> entry) {
		// String key = entry.getKey() == null ? "" : StringEscapeUtils.escapeHtml(entry.getKey());
		// String value = entry.getValue() == null ? "" : StringEscapeUtils.escapeHtml(entry.getValue().toString());
		String key = entry.getKey() == null ? "" : entry.getKey();
		String value = entry.getValue() == null ? "" : entry.getValue().toString();
		return formatMessage(HIDDEN_INPUT_TPL, key, value);
	}

	public static String mapToUrl(String url, Map<String, ? extends Object> map) {

		Map<String, String> paramMap = new HashMap<String, String>();
		String urlWithoutParams = splitUrlParams(url, paramMap);

		String value;
		if (map != null) {
			for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
				if (entry.getValue() == null) {
					value = "";
				} else {
					value = encodeUrlParam(entry.getValue().toString());
				}
				paramMap.put(entry.getKey(), value);
			}
		}

		StringBuffer buffer = new StringBuffer(urlWithoutParams);

		if (paramMap.size() > 0) {
			buffer.append("?");
			buffer.append(StringUtils.joinMapValue(paramMap, DEFAULT_JOINER, true));
		}

		return buffer.toString();
	}

	private static String splitUrlParams(String url, Map<String, String> paramMap) {
		URI uri = parseUrl(url);
		String params = uri.getQuery();
		String path = uri.getPath();
		String[] paramArray = split(params, DEFAULT_JOINER);
		if (paramArray != null) {
			String key, value;
			for (String param : paramArray) {
				int index = indexOf(param, '=');
				if (index != -1) {
					key = substring(param, 0, index);
					value = substring(param, index + 1, length(param));
					value = encodeUrlParam(value);
					value = paramMap.put(key, value);
				}
			}
		}
		return path;
	}

	public static String encodeUrlParam(String value) {
		if (isEmpty(value)) {
			return value;
		}
		try {
			return URLEncoder.encode(value, DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			// TODO log error
			return value;
		}
	}

	public static String decodeUrlParam(String value) {
		if (isEmpty(value)) {
			return value;
		}
		try {
			return URLDecoder.decode(value, DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			// TODO log error
			return value;
		}
	}

	public static URI parseUrl(String url) {
		try {
			return new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}
