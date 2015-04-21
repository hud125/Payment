package com.aurfy.haze.web.perm.interceptor;

import java.io.BufferedReader;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aurfy.haze.core.model.infra.User;
import com.aurfy.haze.core.model.perm.PermValueEnum;
import com.aurfy.haze.service.api.perm.PermissionService;
import com.aurfy.haze.service.bean.perm.PermAssignmentBean;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.web.perm.ScopePermission;
import com.aurfy.haze.web.session.SessionConfig;

public class PermissionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = LoggerFactory.getLogger(PermissionInterceptor.class);

	@Autowired
	private PermissionService permService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 1.get the entryKey of mothod
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		if (!method.isAnnotationPresent(ScopePermission.class)) {
			return true;
		}

		String entryKey = method.getAnnotation(ScopePermission.class).value();

		// 2.get the user permissions
		String body = getBody(request);
		JSONObject payload = JSONObject.fromObject(body.toString());

		if (!payload.containsKey("identication")) {
			throw new Exception("The value of identication is must.");
		}
		JSONObject identication = (JSONObject) payload.get("identication");
		String type = identication.getString("type");
		if (!"session".equals(type)) {
			throw new Exception("Type is error!");
		}
		String session_id = (String) identication.get("session_id");
		User user = SessionConfig.getUserBySession(session_id);
		if (null == user) {
			throw new Exception("The session has expired!");
		}

		// 3.judge the permission of the entryKey about this user
		PermissionService permService = ServiceClient.createPermissionService();
		PermAssignmentBean ass = permService.getAssignment(user.getID(), entryKey);
		if (null == ass) {
			throw new Exception("The model has not been assigned to this user!");
		} else if (ass.getPermValue() == PermValueEnum.DENIED) {
			throw new Exception("The user has been denied to visit this model!");
		} else if (ass.getPermValue() == PermValueEnum.GRANTED) {
			request.setAttribute("entryKey", entryKey);
		}

		return true;
	}

	public static String getBody(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
