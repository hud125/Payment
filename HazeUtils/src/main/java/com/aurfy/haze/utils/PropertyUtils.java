package com.aurfy.haze.utils;

import java.util.Properties;

public final class PropertyUtils {

	public static Properties getProperties(String resource, Object caller) {
		Class<?> clazz = caller == null ? null : caller.getClass();
		return getProperties(resource, clazz);
	}

	public static Properties getProperties(String resource, Class<?> clazz) {
		ClassLoader loader = clazz == null ? null : clazz.getClassLoader();
		return ResourceUtils.getResourceAsProperty(resource, loader);
	}

	public static String readString(Properties props, String key) {
		return props.getProperty(key);
	}

	public static int readInteger(Properties props, String key) {
		return Integer.parseInt(readString(props, key));
	}

	public static boolean readBoolean(Properties props, String key) {
		return Boolean.parseBoolean(readString(props, key));
	}
	
	public static long readLong(Properties props, String key) {
		return Long.parseLong(readString(props, key));
	}
	
	public static float readFloat(Properties props, String key) {
		return Float.parseFloat(readString(props,key));
	}

}
