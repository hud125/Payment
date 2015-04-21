package com.aurfy.haze.service.cache;

import java.io.Serializable;

import com.google.common.cache.LoadingCache;

public interface HazeCacheProvider<T extends Serializable> {

	/**
	 * <p>
	 * get cache configuration by naming convention. bean type will be retrieved from the generic type.
	 * </p>
	 * 
	 * @param clazz
	 * @return
	 */
	public CacheConfig getConfigByConvention();

	/**
	 * <p>
	 * get cache configuration by naming convention.
	 * </p>
	 * 
	 * @param clazz
	 *            the class name for bean.
	 * @return
	 */
	public CacheConfig getConfigByConvention(Class<T> clazz);

	/**
	 * build the cache using internal logic.
	 * 
	 * @param config
	 * @param service
	 * @return
	 */
	public LoadingCache<String, T> getCache(CacheConfig config, CacheableService<T> service);

}
