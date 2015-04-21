package com.aurfy.haze.service.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.conf.HazeDefaultConfig;
import com.aurfy.haze.service.conf.OverwritableConfigReader;
import com.aurfy.haze.utils.ReflectionUtils;

public abstract class BaseCacheProvider<T extends Serializable> extends OverwritableConfigReader implements
		HazeCacheProvider<T> {

	private static final Logger logger = LoggerFactory.getLogger(BaseCacheProvider.class);
	private Class<T> cacheObjClass;

	public BaseCacheProvider() {
		super(HazeDefaultConfig.getServiceCacheProps());
	}

	public CacheConfig getConfigByConvention() {
		Class<T> clazz = ReflectionUtils.getGenericParameterType(getClass(), 0);
		if (clazz == null) {
			throw new RuntimeException(
					"get generic type failed, please specify by using getConfigByConvention(Class<T> clazz) !");
		}
		return getConfigByConvention(clazz);
	}

	public CacheConfig getConfigByConvention(Class<T> clazz) {

		this.cacheObjClass = clazz;
		super.calculateKeyPrefixes();

		CacheConfig config = new CacheConfig();
		try {
			config.setMaxSize(getAsLong("maxSize"));
			config.setExpireDuration(getAsLong("expireDuration"));
			config.setTimeUnit(getAsEnum("timeUnit", TimeUnit.class));
			return config;
		} catch (Exception e) {
			final String msg = "get CacheConfig by convention failed";
			logger.error(msg, e);
			throw new RuntimeException(msg);
		}
	}

	@Override
	protected List<String> getKeyPrefixesByConvention() {
		List<String> prefixes = new ArrayList<String>(10);
		prefixes.add(cacheObjClass.getName()); // full name first
		prefixes.add(cacheObjClass.getSimpleName()); // short name second
		// not necessary to add default
		return prefixes;
	}

}
