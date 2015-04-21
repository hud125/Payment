package com.aurfy.haze.service.cache;

import static com.aurfy.haze.service.conf.ServiceImplConstant.COMPONENT_NAME.REDIS_CACHE_PROVIDER;

import java.io.Serializable;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.cache.LoadingCache;

@Component(REDIS_CACHE_PROVIDER)
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RedisCacheProvider<T extends Serializable> extends BaseCacheProvider<T> implements HazeCacheProvider<T> {

	private static final Logger logger = LoggerFactory.getLogger(RedisCacheProvider.class);

	public RedisCacheProvider() {
	}

	@Override
	public LoadingCache<String, T> getCache(CacheConfig config, CacheableService<T> service) {
		throw new NotImplementedException("RedisCacheProvider#getCache");
	}

}
