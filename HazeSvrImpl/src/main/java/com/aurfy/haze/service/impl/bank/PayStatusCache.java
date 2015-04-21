package com.aurfy.haze.service.impl.bank;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.aurfy.haze.core.model.payment.PayStatusEnum;

public class PayStatusCache {

	private static PayStatusCache instance;
	/**
	 * key为paySummaryId
	 */
	private Map<String, PayStatusEnum> map = new ConcurrentHashMap<String, PayStatusEnum>();

	static {
		instance = new PayStatusCache();
	}

	private PayStatusCache() {

	}

	public static PayStatusCache getInstance() {
		return instance;
	}

	public synchronized PayStatusEnum put(String key, PayStatusEnum value) {
		return map.put(key, value);
	}

	public synchronized PayStatusEnum get(String key) {
		return map.get(key);
	}

	public synchronized PayStatusEnum remove(String key) {
		return map.remove(key);
	}
}
