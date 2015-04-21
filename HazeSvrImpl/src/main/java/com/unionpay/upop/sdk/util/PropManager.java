package com.unionpay.upop.sdk.util;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PropManager {

	private static Map<String, PropLoader> propLoaderCache = new ConcurrentHashMap<String, PropLoader>();

	private PropManager() {
	}

	/**
	 * 重新加载properties
	 */
	public static void reloadAll() throws IOException {
		for (PropLoader loader : propLoaderCache.values()) {
			loader.reload();
		}
	}

	public static PropLoader getSdkInstance() {
		return getInstance("sdk.properties");
	}

	public static PropLoader getMerInstance() {
		return getInstance("merchant.properties");
	}

	public static synchronized PropLoader getInstance(String filename) {
		if (isDevelopment()) {
			try {
				reloadAll();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		PropLoader instance = propLoaderCache.get(filename);
		if (instance == null) {
			instance = new PropLoader(filename);
			propLoaderCache.put(filename, instance);
		}
		return instance;
	}

	private static boolean isDevelopment() {
		PropLoader instance = propLoaderCache.get("sdk.properties");
		;
		if (instance == null) {
			instance = new PropLoader("sdk.properties");
			propLoaderCache.put("sdk.properties", instance);
		}
		return Boolean.parseBoolean(instance.getProperty("development"));

	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PropLoader instance = PropManager.getSdkInstance();
		System.out.println(instance.getProperty("version"));
		System.out.println(instance.getProperty("charset"));

		PropLoader queryInstance = PropManager.getInstance("QuickPayQuery.properties");
		System.out.println(queryInstance.getProperty("merAbbr"));
		System.out.println(queryInstance.getProperty("merId"));
	}

}
