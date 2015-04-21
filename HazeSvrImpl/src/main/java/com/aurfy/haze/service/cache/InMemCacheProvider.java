package com.aurfy.haze.service.cache;

import static com.aurfy.haze.service.conf.ServiceImplConstant.COMPONENT_NAME.INMEM_CACHE_PROVIDER;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

/**
 * builder for creating in-memory loading cache. <br/>
 * cache that use memory to store objects, keys are always to be String.
 * 
 * @author hermano
 *
 * @param <T>
 */
@Component(INMEM_CACHE_PROVIDER)
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class InMemCacheProvider<T extends Serializable> extends BaseCacheProvider<T> implements HazeCacheProvider<T> {

	private static final Logger logger = LoggerFactory.getLogger(InMemCacheProvider.class);
	private static Map<Key, LoadingCache<String, ?>> storage = new ConcurrentHashMap<Key, LoadingCache<String, ?>>();

	private InMemCacheProvider() {
	}

	@Override
	public LoadingCache<String, T> getCache(CacheConfig config, CacheableService<T> service) {
		return getSingletonCache(config, service);
	}

	/**
	 * <p>
	 * get the cache as Singleton. <br />
	 * If the same cache instance, identified by <code>CacheConfig</code> and type of <code>HazeService</code>, is not
	 * previous created, then it will be created. otherwise, the cache instance from memory will be returned.
	 * </p>
	 * 
	 * @param config
	 * @param service
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> LoadingCache<String, T> getSingletonCache(CacheConfig config, CacheableService<T> service) {
		Key key = new Key(config, service.getClass());
		if (storage.containsKey(key)) {
			return (LoadingCache<String, T>) storage.get(key);
		} else {
			synchronized (InMemCacheProvider.class) {
				LoadingCache<String, T> cache = createCache(config, service);
				storage.put(key, cache);
				return cache;
			}
		}
	}

	/**
	 * create a new loading cache.
	 * 
	 * @param config
	 * @param service
	 * @return
	 */
	protected static <T> LoadingCache<String, T> createCache(CacheConfig config, CacheableService<T> service) {
		return CacheBuilder.newBuilder().maximumSize(config.getMaxSize())
				.expireAfterWrite(config.getExpireDuration(), config.getTimeUnit())
				.removalListener(new RemovalListener<String, T>() {
					@Override
					public void onRemoval(RemovalNotification<String, T> notification) {
						logger.debug("cache removed from memory, key={}, type={}, cause={}", notification.getKey(),
								"TODO", notification.getCause().toString());
					}
				}).build(new DBCacheLoader<T>(service));
	}

	/**
	 * cleanup cache instances <br />
	 * TODO: system level listener must call this before application shutdown
	 */
	public static void cleanup() {
		if (MapUtils.isNotEmpty(storage)) {
			synchronized (InMemCacheProvider.class) {
				for (LoadingCache<String, ?> cache : storage.values()) {
					cache.invalidateAll();
					cache.cleanUp();
				}
			}
		}
	}

	private static class Key {

		private CacheConfig config;
		private Class<?> clazz;

		public Key(CacheConfig config, Class<?> clazz) {
			super();
			this.config = config;
			this.clazz = clazz;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
			result = prime * result + ((config == null) ? 0 : config.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (clazz == null) {
				if (other.clazz != null)
					return false;
			} else if (!clazz.equals(other.clazz))
				return false;
			if (config == null) {
				if (other.config != null)
					return false;
			} else if (!config.equals(other.config))
				return false;
			return true;
		}
	}

}
