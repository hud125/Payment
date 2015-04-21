package com.aurfy.haze.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.aurfy.haze.service.bean.infra.UserBean;
import com.aurfy.haze.web.constant.WebConstant;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		UserBean userContext = (UserBean) request.getSession().getAttribute(WebConstant.Session.USER_CONTEXT);
		if (userContext == null) {
			response.sendRedirect(request.getContextPath() + "/tologin");
			return false;
		}

		return true;
	}
}
