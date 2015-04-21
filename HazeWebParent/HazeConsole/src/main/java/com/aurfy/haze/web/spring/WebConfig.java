package com.aurfy.haze.web.spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import com.aurfy.haze.web.controller.HomeController;
import com.aurfy.haze.web.interceptor.LoginInterceptor;
import com.aurfy.haze.web.perm.interceptor.PermissionInterceptor;
import com.aurfy.haze.web.velocity.CacheDirective;
import com.aurfy.haze.web.velocity.PageDirective;
import com.aurfy.haze.web.velocity.PermissionDirective;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

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
		InterceptorRegistration loginRegistry = registry.addInterceptor(new LoginInterceptor());
		loginRegistry.addPathPatterns(new String[] { "/*", "/*/*" });
		loginRegistry.excludePathPatterns(new String[] { "/tologin", "/login", "/logout", "/code" });

		registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/listUsers");
		System.out.println("AllInterceptor");

		/*
		 * registry.addInterceptor(new LocaleInterceptor()); registry.addInterceptor(new
		 * ThemeInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**"); registry.addInterceptor(new
		 * SecurityInterceptor()).addPathPatterns("/secure/*");
		 */
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/skin/js/**").addResourceLocations("/skin/js/").setCachePeriod(31556926);
		registry.addResourceHandler("/skin/css/**").addResourceLocations("/skin/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/skin/image/**").addResourceLocations("/skin/image/").setCachePeriod(31556926);
	}

	@Bean
	public CustomSimpleMappingExceptionResolver getCustomSimpleMappingExceptionResolver() {
		CustomSimpleMappingExceptionResolver resolver = new CustomSimpleMappingExceptionResolver();
		resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return resolver;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setProviderClass(HibernateValidator.class);
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

	@SuppressWarnings("static-access")
	@Bean
	public VelocityConfigurer getVelocityConfig() {
		VelocityConfigurer velocityConfig = new VelocityConfigurer();
		velocityConfig.setResourceLoaderPath(getViewFolder());
		velocityConfig.setConfigLocation(getSpringResourceLoader().getResource(
				CLASSPATH_PREFIX + VELOCITY_CONFIG_LOCATION));
		Map<String, Object> velocityPropertiesMap = new HashMap<String, Object>();
		velocityPropertiesMap.put("input.encoding", DEFAULT_ENCODING);
		velocityPropertiesMap.put("output.encoding", DEFAULT_ENCODING);
		velocityPropertiesMap.put(
				"userdirective",
				PageDirective.class.getName() + "," + PermissionDirective.class.getName() + ","
						+ CacheDirective.class.getName());
		velocityConfig.setVelocityPropertiesMap(velocityPropertiesMap);

		return velocityConfig;
	}

	@Bean
	public DefaultKaptcha getCaptchaProducer() {
		DefaultKaptcha captchaProducer = new DefaultKaptcha();
		Properties props = new Properties();
		props.setProperty("kaptcha.border", "no");
		props.setProperty("kaptcha.border.color", "105,179,90");
		props.setProperty("kaptcha.textproducer.font.color", "blue");
		props.setProperty("kaptcha.image.width", "125");
		props.setProperty("kaptcha.image.height", "45");
		props.setProperty("kaptcha.textproducer.font.size", "no");
		props.setProperty("kaptcha.session.key", "45");
		props.setProperty("kaptcha.textproducer.char.length", "4");
		props.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");

		Config config = new Config(new Properties());
		captchaProducer.setConfig(config);

		return captchaProducer;
	}
}
