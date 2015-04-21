package com.aurfy.haze.web.session;

import java.util.HashMap;
import java.util.UUID;

import com.aurfy.haze.service.bean.infra.UserBean;

public class SessionConfig {
	
	/**
	 * 这里将session和view_per_filter分开存储，是为了将来扩展起来方便，比如做成集群式session等
	 */
	public static HashMap<String, UserBean> _sessionMap = new HashMap<String, UserBean>();
	
	public static String setSession(UserBean user){
		String uuid = UUID.randomUUID().toString();
		synchronized (_sessionMap) {
			_sessionMap.put(uuid, user);
		}
		return uuid;
	}
	
	public static UserBean getUserBySession(String session_id){
		UserBean user = null;
		synchronized (_sessionMap) {
			user = _sessionMap.get(session_id);
		}
		return user;
	}
	
}
