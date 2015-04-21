package com.aurfy.haze.service.cache;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.concurrent.ExecutionException;

import org.apache.commons.io.input.ReaderInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.serializer.DefaultDeserializer;

import redis.clients.jedis.Jedis;

import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.utils.IOUtils;
import com.google.common.cache.AbstractLoadingCache;
import com.google.common.cache.LoadingCache;

/**
 * cache that use redis as underlying storage, keys are always to be String.
 * 
 * @author hermano
 *
 * @param <T>
 */
public class RedisCache<T extends Serializable> extends AbstractLoadingCache<String, T> implements
		LoadingCache<String, T> {

	private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

	private CacheConfig config;
	private DBCacheLoader<T> cacheLoader;

	public RedisCache(CacheConfig config, DBCacheLoader<T> cacheLoader) {
		this.config = config;
		this.cacheLoader = cacheLoader;
	}

	/**
	 * get object from cache if it is present. will not try to retrieve data from data loader.
	 * 
	 * @param key
	 *            the cache key in {@link java.lang.String String} format
	 * @return the cached value, or <code>null</code> if cache not hit.
	 * @throws RuntimeServiceException
	 *             if call redis cache error, or deserialization failed.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getIfPresent(Object key) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.borrow();
			String v = jedis.get(key.toString());
			if (v == null) {
				return null;
			}
			// InputStream in = IOUtils.toInputStream(v, "UTF-8");
			InputStream in = null;
			try {
				in = new BufferedInputStream(new ReaderInputStream(new StringReader(v)));
				return (T) new DefaultDeserializer().deserialize(in);
			} finally {
				IOUtils.close(in);
			}
		} catch (Exception e) {
			final String msg = "can not deserialize object";
			logger.warn(msg, e);
			throw new RuntimeServiceException(msg, e);
		} finally {
			if (jedis != null) {
				RedisPool.returnBack(jedis);
			}
		}
	}

	/**
	 * get object from cache. will try to load data from source if its not present.
	 */
	@Override
	public T get(String key) throws ExecutionException {
		T cacheValue = getIfPresent(key);
		// cache not hit, try to get from data loader
		if (cacheValue == null) {
			try {
				cacheValue = cacheLoader.load(key);
				// TODO: put to jedis for storage
			} catch (Exception e) {
				logger.error("error loading cache for key {}", key);
			}
		}
		return cacheValue;
	}
}
