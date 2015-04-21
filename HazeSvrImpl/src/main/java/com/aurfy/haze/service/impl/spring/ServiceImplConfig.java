package com.aurfy.haze.service.impl.spring;

import static com.aurfy.haze.service.conf.ServiceImplConstant.COMPONENT_NAME.DEFAULT_CACHE_PROVIDER;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.aurfy.haze.service.cache.HazeCacheProvider;
import com.aurfy.haze.service.cache.InMemCacheProvider;

@Configuration
@ComponentScan(basePackageClasses = { HazeCacheProvider.class })
public class ServiceImplConfig {

	// @Autowired
	// @Bean
	// public <T extends Serializable> InMemCacheProvider<T> newInMemCacheProvider(InMemCacheProvider<T> provider) {
	// return provider;
	// }
	//
	// @Autowired
	// @Bean
	// public <T extends Serializable> RedisCacheProvider<T> newRedisCacheProvider(RedisCacheProvider<T> provider) {
	// return provider;
	// }

	@Autowired
	@Bean(name = DEFAULT_CACHE_PROVIDER)
	public <T extends Serializable> HazeCacheProvider<T> newHazeCacheProvider(InMemCacheProvider<T> provider) {
		// return new InMemCacheProvider<T>();
		return provider;
	}

}
