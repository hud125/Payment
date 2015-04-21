package com.aurfy.haze.service.impl.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.aurfy.haze.conf.HazeDefaultConfig;
import com.aurfy.haze.service.conf.OverwritableConfigReader;

/**
 * a convenient thread pool provider which wraps up spring class
 * {@link org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor ThreadPoolTaskExecutor}.
 * 
 * @author hermano
 *
 */
public final class ThreadPoolProvider {

	private static final Logger logger = LoggerFactory.getLogger(ThreadPoolProvider.class);

	private static Map<String, ThreadPoolTaskExecutor> pools = new ConcurrentHashMap<String, ThreadPoolTaskExecutor>(3);

	private ThreadPoolProvider() {
	}

	/**
	 * <p>
	 * get the thread pool by name<br/>
	 * will try to create a new one if no pool is associated with that name
	 * </p>
	 * 
	 * @param poolName
	 * @return
	 */
	public static ThreadPoolTaskExecutor getThreadPool(String poolName) {
		if (OverwritableConfigReader.DEFAULT_KEY_PREFIX.equalsIgnoreCase(poolName)) {
			throw new RuntimeException("keyword 'default' is reserved for thread pool.");
		}
		if (!pools.containsKey(poolName)) {
			synchronized (pools) {
				if (!pools.containsKey(poolName)) {
					ThreadPoolTaskExecutor executor = createPool(poolName);
					pools.put(poolName, executor);
				}
			}
		}
		return pools.get(poolName);
	}

	/**
	 * create a new thread pool with the given name.
	 * 
	 * @param poolName
	 * @return
	 */
	private static ThreadPoolTaskExecutor createPool(String poolName) {
		logger.debug("creating thread pool '{}'...", poolName);
		ThreadPoolConfigReader reader = new ThreadPoolConfigReader(HazeDefaultConfig.getThreadPoolProps(), poolName);
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(reader.getAsInteger("corePoolSize"));
		pool.setMaxPoolSize(reader.getAsInteger("maxPoolSize"));
		pool.setQueueCapacity(reader.getAsInteger("queueCapacity"));
		pool.setKeepAliveSeconds(reader.getAsInteger("keepAliveSeconds"));
		pool.setWaitForTasksToCompleteOnShutdown(reader.getAsBoolean("waitForJobsToCompleteOnShutdown"));
		pool.setAwaitTerminationSeconds(reader.getAsInteger("awaitTerminationSeconds"));
		pool.setRejectedExecutionHandler(reader.getHandler(reader.getAsString("rejectedExecutionHandler")));
		pool.setThreadGroupName(reader.getAsString("threadGroupName"));
		pool.setBeanName(reader.getAsString("beanName"));
		pool.initialize();
		logger.info("thread pool '{}' created", poolName);
		return pool;
	}

	/**
	 * <p>
	 * shutdown all the thread pools<br/>
	 * the waiting behavior is based on the configuration
	 * </p>
	 */
	public static void shutdownAll() {
		for (Map.Entry<String, ThreadPoolTaskExecutor> entry : pools.entrySet()) {
			logger.info("shutting down thread pool '{}'...", entry.getKey());
			try {
				entry.getValue().shutdown();
			} catch (Exception e) {
				// ignore
			}
		}
		pools.clear();
		logger.info("all thread pools shutdown.");
	}

}

/**
 * internal class for reading thread pool properties.
 * 
 * @author hermano
 *
 */
class ThreadPoolConfigReader extends OverwritableConfigReader {

	private String poolName;

	public ThreadPoolConfigReader(Properties props, String poolName) {
		super(props);
		this.poolName = poolName;
	}

	@Override
	protected List<String> getKeyPrefixesByConvention() {
		List<String> result = new ArrayList<String>(1);
		result.add(poolName);
		return result;
	}

	public RejectedExecutionHandler getHandler(String handlerName) {
		switch (handlerName) {
		case "AbortPolicy": {
			return new ThreadPoolExecutor.AbortPolicy();
		}
		case "CallerRunsPolicy": {
			return new ThreadPoolExecutor.CallerRunsPolicy();
		}
		case "DiscardOldestPolicy": {
			return new ThreadPoolExecutor.DiscardOldestPolicy();
		}
		case "DiscardPolicy": {
			return new ThreadPoolExecutor.DiscardPolicy();
		}
		default: {
			throw new RuntimeException("non supported RejectedExecutionHandler: " + handlerName);
		}
		}
	}
}
