package com.aurfy.haze.service.cache;

import java.util.concurrent.TimeUnit;

/**
 * wrapper class for cache config.
 * 
 * @author hermano
 *
 */
public final class CacheConfig {

	private long maxSize;
	private long expireDuration;
	private TimeUnit timeUnit;

	public CacheConfig() {
	}

	public CacheConfig(long maxSize, long expireDuration, TimeUnit timeUnit) {
		super();
		this.maxSize = maxSize;
		this.expireDuration = expireDuration;
		this.timeUnit = timeUnit;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}

	public long getExpireDuration() {
		return expireDuration;
	}

	public void setExpireDuration(long expireDuration) {
		this.expireDuration = expireDuration;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (expireDuration ^ (expireDuration >>> 32));
		result = prime * result + (int) (maxSize ^ (maxSize >>> 32));
		result = prime * result + ((timeUnit == null) ? 0 : timeUnit.hashCode());
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
		CacheConfig other = (CacheConfig) obj;
		if (expireDuration != other.expireDuration)
			return false;
		if (maxSize != other.maxSize)
			return false;
		if (timeUnit != other.timeUnit)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CacheConfig [maxSize=");
		builder.append(maxSize);
		builder.append(", expireDuration=");
		builder.append(expireDuration);
		builder.append(", timeUnit=");
		builder.append(timeUnit);
		builder.append("]");
		return builder.toString();
	}

}
