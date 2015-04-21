package com.aurfy.haze.service.cache;

import java.util.Map;

import com.google.common.cache.CacheLoader;

/**
 * cache loader for database, using haze service.
 * 
 * @author hermano
 *
 * @param <T>
 */
public class DBCacheLoader<T> extends CacheLoader<String, T> {

	private CacheableService<T> service;

	public DBCacheLoader(CacheableService<T> service) {
		super();
		this.service = service;
	}

	@Override
	public T load(String key) throws Exception {
		return service.singleLoad4Cache(key);
	}

	@Override
	public Map<String, T> loadAll(Iterable<? extends String> keys) throws Exception {
		return service.bulkLoad4Cache(keys);
	}

}
