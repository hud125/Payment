package com.aurfy.haze.core.model.payment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HazeRespCode {

	private static Map<String, HazeRespCode> map = new ConcurrentHashMap<String, HazeRespCode>();
	
	public static final HazeRespCode SUCCESS = getHazeRespCode("0000");
	public static final HazeRespCode FAILED = getHazeRespCode("9999");

	private String code;

	private HazeRespCode(String code) {
		this.code = code;
	}

	public static HazeRespCode getHazeRespCode(String code) {
		if (!map.containsKey(code)) {
			synchronized (map) {
				if (!map.containsKey(code)) {
					map.put(code, new HazeRespCode(code));
				}
			}
		}
		return map.get(code);
	}

	public String getCode() {
		return code;
	}

}
