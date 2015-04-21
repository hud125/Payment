package com.aurfy.haze.web.spring;

import org.springframework.core.annotation.Order;

@Order(1)
public class WebAppInitializer extends WebAppInitializerTemplate {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { BGWConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

}
