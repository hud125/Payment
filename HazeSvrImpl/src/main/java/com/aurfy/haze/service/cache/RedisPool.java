package com.aurfy.haze.service.cache;

import static com.aurfy.haze.utils.PropertyUtils.readInteger;
import static com.aurfy.haze.utils.PropertyUtils.readString;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.aurfy.haze.conf.HazeDefaultConfig;

public class RedisPool {

	private static JedisPool pool;
	private static final Logger logger = LoggerFactory.getLogger(RedisPool.class);

	static {
		Properties redisProps = HazeDefaultConfig.getRedisProps();
		pool = new JedisPool(new JedisPoolConfig(), readString(redisProps, "host"), readInteger(redisProps, "port"),
				readInteger(redisProps, "timeout"), readString(redisProps, "password"), readInteger(redisProps,
						"database"), readString(redisProps, "clientName"));
	}

	private RedisPool() {
	}

	/**
	 * borrow a Jedis object from the pool
	 */
	public static Jedis borrow() {
		return pool.getResource();
	}

	/**
	 * return a Jedis object to the pool
	 */
	public static void returnBack(Jedis object) {
		pool.returnResource(object);
	}

	@Override
	protected void finalize() throws Throwable {
		if (!pool.isClosed()) {
			synchronized (RedisPool.class) {
				if (!pool.isClosed()) {
					pool.close();
					logger.info("RedisPool closed.");
				}
			}
		}
		super.finalize();
	}

}
