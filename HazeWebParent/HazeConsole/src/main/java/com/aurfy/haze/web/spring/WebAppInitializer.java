package com.aurfy.haze.web.spring;

import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;

@Order(1)
public class WebAppInitializer extends WebAppInitializerTemplate {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { ConsoleConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

}
