package com.aurfy.haze.web.spring;

import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.aurfy.haze.web.controller.HomeController;
import com.aurfy.haze.web.perm.interceptor.PermissionInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class })
public class WebConfig extends WebConfigTemplate {

	private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		super.addInterceptors(registry);

		// TODO:add pathMatcher for PermissionInterceptor
		logger.info("adding PermissionInterceptor...");
		registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/listUsers");
		System.out.println("AllInterceptor");

		/*
		 * registry.addInterceptor(new LocaleInterceptor()); registry.addInterceptor(new
		 * ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**"); registry.addInterceptor(new
		 * SecurityInterceptor()).addPathPatterns("/secure/*");
		 */
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setProviderClass(HibernateValidator.class);
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

}