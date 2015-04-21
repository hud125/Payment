package com.aurfy.haze.ds;

import static com.aurfy.haze.utils.StringUtils.formatMessage;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.ds.exceptions.AccessCountExceedException;
import com.aurfy.haze.ds.exceptions.AccessTimeoutException;

/**
 * <p>
 * A maintainable map for haze specific use.<br />
 * Entries in this map shall support two features:
 * <ul>
 * <li>Timeout: each entry contains a last update time,
 * {@link com.aurfy.haze.ds.exceptions.AccessCountExceedException AccessTimeoutException} will be thrown upon
 * accessing in case the value is timeout.</li>
 * <li>Access Counter: each entry maintains the accessing number of the value,
 * {@link com.aurfy.haze.ds.exceptions.AccessTimeoutException AccessCountExceedException} will be thrown upon
 * exceeding the pre-defined max counter.</li>
 * </ul>
 * All entries share the same configuration value for the timeout interval and maximum access counter.<br />
 * Entries that violate the timeout or access counter feature will be regarded as null (exceptions will be thrown in
 * certain operations).
 * </p>
 * 
 * @author Hermano
 * 
 */
public class MaintainableMap<K, V> implements Serializable {

	private static final long serialVersionUID = -4394209797899624455L;

	private static final int DEFAULT_PURGE_SIZE = 30000;
	private static final int SAFE_PURGE_SIZE = 10000;
	private static final Logger log = LoggerFactory.getLogger(MaintainableMap.class);

	private int maxCapacity;
	private float balanceFactor;
	private long timeout;
	private int maxAccessCount;
	private boolean refreshTimeOnAccess;
	private Hashtable<K, MaintainableEntry<V>> storage;

	private int purgeThreshold = 0;

	private int purgeSize = DEFAULT_PURGE_SIZE;

	protected MaintainableMap() {
		this(10000, 0.75f, 5 * 60 * 1000, 0);
	}

	/**
	 * @param maxCapacity
	 * @param balanceFactor
	 * @param timeout
	 *            小于等于0 不参考
	 * @param maxAccessCount
	 *            小于等于0 不参考
	 */
	public MaintainableMap(int maxCapacity, float balanceFactor, long timeout, int maxAccessCount,
			boolean refreshTimeOnAccess) {
		this(maxCapacity, balanceFactor, timeout, maxAccessCount, refreshTimeOnAccess, DEFAULT_PURGE_SIZE);
	}

	/**
	 * 
	 * @param maxCapacity
	 *            the max capacity of the map.
	 * @param balanceFactor
	 *            the load factor of the map.
	 * @param timeout
	 *            timeout value in milliseconds, non-positive value indicates the entries will never be timeout.
	 * @param maxAccessCount
	 *            the maximum access counter, non-positive value indicates the entries will have no limit on the counter
	 *            of being accessed.
	 * @param refreshTimeOnAccess
	 *            the default option of whether to refresh the update time on accessing.
	 * @param purgeSize
	 */
	public MaintainableMap(int maxCapacity, float balanceFactor, long timeout, int maxAccessCount,
			boolean refreshTimeOnAccess, int purgeSize) {
		super();
		this.maxCapacity = maxCapacity;
		this.balanceFactor = balanceFactor;
		this.timeout = timeout;
		this.maxAccessCount = maxAccessCount;
		this.refreshTimeOnAccess = refreshTimeOnAccess;
		this.storage = new Hashtable<K, MaintainableEntry<V>>((int) (maxCapacity * balanceFactor));

		if (purgeSize > SAFE_PURGE_SIZE) {
			this.purgeSize = purgeSize;
		}
	}

	public MaintainableMap(int maxCapacity, float balanceFactor, long timeout, int maxAccessCount) {
		this(maxCapacity, balanceFactor, timeout, maxAccessCount, false);
	}

	protected boolean needsPurge(int thresHoldOffset) {
		purgeThreshold += thresHoldOffset;
		return purgeThreshold >= purgeSize;
	}

	/**
	 * <p>
	 * Clear the out-of-date entries in the map.<br />
	 * An object is considered as out of date when matching one of the following conditions:
	 * <ul>
	 * <li>(System.currentTimeMillis() - entry.updateTime) >= timeout</li>
	 * <li>entry.accessCounter >= maxAccessCounter</li>
	 * </ul>
	 * Note: this might be a time-consuming operation, hence use with caution.
	 * </p>
	 */
	protected synchronized void purge() {
		/*
		 * Set<K> keys2Remove = new HashSet<K>(); for (Map.Entry<K, MaintainableEntry<V>> entry : storage.entrySet()) {
		 * if (entry.getValue().isTimeout(timeout) || entry.getValue().isExceedMaxAccessCount(maxAccessCount)) {
		 * keys2Remove.add(entry.getKey()); } } for (K key : keys2Remove) { storage.remove(key); }
		 */
		long startTime = System.currentTimeMillis();
		log.info(formatMessage("Reaching the purge size: {0}, purging now...The map size is: {1}",
				Integer.valueOf(purgeSize), Integer.valueOf(storage.size())));

		log.info(formatMessage("Purging... The map size is: {0}", Integer.valueOf(storage.size())));

		// purging
		Iterator<Map.Entry<K, MaintainableEntry<V>>> iterator = storage.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<K, MaintainableEntry<V>> entry = iterator.next();
			if (entry.getValue().isTimeout(timeout) || entry.getValue().isExceedMaxAccessCount(maxAccessCount)) {
				iterator.remove();
			}
		}
		log.info(formatMessage("Purging finished.  The map size is: {0}, The duration is: {1}",
				Integer.valueOf(storage.size()), Long.valueOf(System.currentTimeMillis() - startTime)));

		// reset the threshold
		purgeThreshold = 0;
	}

	/**
	 * <p>
	 * Truncate the current map.
	 * </p>
	 */
	public synchronized void clear() {
		storage.clear();
		purgeThreshold = 0;
	}

	/**
	 * <p>
	 * Get size of the map.
	 * </p>
	 * 
	 * @return the map size
	 */
	public synchronized int size() {
		if (needsPurge(0)) {
			purge();
		}
		return storage.size();
	}

	/**
	 * <p>
	 * Check if the map is empty.
	 * </p>
	 * 
	 * @return <code>true</code> if the map contains no entry, <code>false</code> otherwise.
	 */
	public synchronized boolean isEmpty() {
		if (needsPurge(0)) {
			purge();
		}
		return storage.isEmpty();
	}

	/**
	 * <p>
	 * Manually refresh the last update time.
	 * <p>
	 * 
	 * @param key
	 */
	public void refreshTime(Object key) {
		if (storage.containsKey(key)) {
			storage.get(key).setCreateTime(System.currentTimeMillis());
		}
	}

	/**
	 * <p>
	 * check if the map contains the specific value. <br />
	 * Note: this does not check timeout or access counter of the entries.
	 * </p>
	 * 
	 * @param value
	 * @return
	 */
	public synchronized boolean contains(V value) {
		if (needsPurge(0)) {
			purge();
		}
		return storage.contains(new MaintainableEntry<V>(value));
	}

	/**
	 * <p>
	 * Check if the map contains the specific key, which is identical to <code>#containsKey(key, true)</code>.
	 * </p>
	 * 
	 * @param key
	 *            the key of the map
	 * @return <code>false</code> if the specific key does NOT exist, or the map entry is already timeout or exceed the
	 *         maximum access counter; <code>true</code> otherwise.
	 * @see {@link #containsKey(Object, boolean)}
	 */
	public synchronized boolean containsKey(Object key) {
		return containsKey(key, true);
	}

	/**
	 * <p>
	 * Check if the map contains the specific key, with option to validate the entry timeout and access counter.
	 * </p>
	 * 
	 * @param key
	 *            the key of the map
	 * @param validate
	 *            <code>true</code> to validate the timeout and access counter of the entry, <code>false</code> to
	 *            return the result directly.
	 * @return <code>false</code> if the specific key does NOT exist, or a timeout/access counter violation is matched
	 *         when <i>validate</i> is set to <code>true</code>; <code>true</code> otherwise.
	 */
	public synchronized boolean containsKey(Object key, boolean validate) {
		if (validate) {
			try {
				checkTimeout(key);
			} catch (AccessTimeoutException e) {
				return false;
			}
			try {
				checkAccessCount(key);
			} catch (AccessCountExceedException e) {
				return false;
			}
		}
		return storage.containsKey(key);
	}

	/**
	 * <p>
	 * Get value of the map entry by the given key, and increase access counter by default, which is identical to
	 * <code>#get(key, true)</code>.
	 * </p>
	 * 
	 * @param key
	 *            the key of the map
	 * @return the value associated with the given key if exists, or <code>null</code> otherwise.
	 * @throws AccessTimeoutException
	 * @throws AccessCountExceedException
	 * @see {@link #get(Object, boolean)}
	 */
	public synchronized V get(Object key) throws AccessTimeoutException, AccessCountExceedException {
		return get(key, true);
	}

	/**
	 * <p>
	 * Get value of the map entry by the given key, with option to increase the access counter.
	 * </p>
	 * 
	 * @param key
	 *            the key of the map
	 * @param increaseAccessCounter
	 *            whether to increase the access counter
	 * @return the value associated with the given key if exists, or <code>null</code> otherwise.
	 * @throws AccessTimeoutException
	 * @throws AccessCountExceedException
	 */
	public synchronized V get(Object key, boolean increaseAccessCount) throws AccessTimeoutException,
			AccessCountExceedException {
		checkTimeout(key);
		checkAccessCount(key);
		if (storage.containsKey(key)) {
			if (increaseAccessCount) {
				storage.get(key).increaseAccessCount();
			}
			if (this.refreshTimeOnAccess) {
				refreshTime(key);
			}
			return storage.get(key).getObject();
		}
		return null;
	}

	/**
	 * <p>
	 * Put the key&value pair into the map. <br />
	 * Note: the update time of the entry will be updated to the current timestamp, and the access counter will be reset
	 * to zero. <br />
	 * If the specific key already exists, the value will be replaced by the given one and the old value will be
	 * returned, which is identical to <code>#put(key, value, false, false)</code>.
	 * </p>
	 * 
	 * @param key
	 *            the key of the map
	 * @param value
	 *            the value of the given key
	 * @return <code>null</code> if the specific key does NOT exist, or the old value associated with the given key.
	 * @see {@link #put(Object, Object, boolean, boolean)}
	 */
	public synchronized V put(K key, V value) {
		return put(key, value, false, false);
	}

	/**
	 * <p>
	 * Put the key&value pair into the map, with options to keep the original create time and access counter (in case of
	 * existence). <br />
	 * If the specific key already exists, the value will be replaced by the given one and the old value will be
	 * returned.
	 * </p>
	 * 
	 * @param key
	 *            the key of the map
	 * @param value
	 *            the value of the given key
	 * @param keepCreateTime
	 *            whether to keep the original create time of the entry
	 * @param keepAccessCount
	 *            whether to keep the original access counter of the entry
	 * @return <code>null</code> if the specific key does NOT exist, or the old value associated with the given key.
	 */
	public synchronized V put(K key, V value, boolean keepCreateTime, boolean keepAccessCount) {
		if (needsPurge(1)) {
			purge();
		}
		if (storage.containsKey(key)) {
			MaintainableEntry<V> oldValue = storage.get(key);
			V returnValue = oldValue.getObject();
			oldValue.setObject(value);
			if (!keepCreateTime) {
				oldValue.setCreateTime(System.currentTimeMillis());
			}
			if (!keepAccessCount) {
				oldValue.setAccessCount(0);
			}
			return returnValue;
		} else {
			storage.put(key, new MaintainableEntry<V>(value));
			return null;
		}
	}

	/**
	 * <p>
	 * Remove the map entry by the given key.
	 * </p>
	 * 
	 * @param key
	 *            the key of the map
	 * @return <code>null</code> if the specific key does not exist, or the entry is already timeout, or the entry
	 *         exceed the maximum access counter; Otherwise, the original entry value will be returned.
	 */
	public synchronized V remove(Object key) {
		if (needsPurge(-1)) {
			purge();
		}
		try {
			checkTimeout(key);
		} catch (AccessTimeoutException e) {
			return null;
		}
		try {
			checkAccessCount(key);
		} catch (AccessCountExceedException e) {
			return null;
		}
		MaintainableEntry<V> oldValue = storage.remove(key);
		return oldValue == null ? null : oldValue.getObject();
	}

	/**
	 * <p>
	 * Put all entries of the given map into the current map. All values will be treated as new. <br />
	 * In other words, the update time will be set to the current timestamp, and the access counter will be set to zero.
	 * </p>
	 * 
	 * @param maps
	 *            the map which contains the new entries to be added
	 */
	public synchronized void putAll(Map<? extends K, ? extends V> t) {
		if (needsPurge(CollectionUtils.size(t))) {
			purge();
		}
		if (t == null) {
			return;
		}
		for (Map.Entry<? extends K, ? extends V> entry : t.entrySet()) {
			storage.put(entry.getKey(), new MaintainableEntry<V>(entry.getValue()));
		}
	}

	/**
	 * <p>
	 * Get the key set of the map. <br />
	 * Note: this does not check timeout or access counter of the entries.
	 * </p>
	 * 
	 * @return key set of the map
	 */
	public synchronized Set<K> keySet() {
		if (needsPurge(0)) {
			purge();
		}
		return new HashSet<K>(storage.keySet());
	}

	/**
	 * TODO: refactor method name
	 * <p>
	 * Get the update time of the map entry associated with the given key.
	 * <p>
	 * 
	 * @param key
	 *            the key of the map
	 * @return the update time of the entry if the key exists, or <code>null</code> otherwise.
	 */
	public synchronized Long getCreateTime(Object key) {
		if (storage.containsKey(key)) {
			return storage.get(key).getCreateTime();
		} else {
			return null;
		}
	}

	/**
	 * <p>
	 * Get the access counter of the map entry associated with the given key.
	 * </p>
	 * 
	 * @param key
	 *            the key of the map
	 * @return the access counter of the entry if the key exists, or <code>-1</code> otherwise.
	 */
	public synchronized int getAccessCount(Object key) {
		if (storage.containsKey(key)) {
			return storage.get(key).getAccessCount();
		} else {
			return -1;
		}
	}

	private void checkTimeout(Object key) throws AccessTimeoutException {
		if (storage.containsKey(key) && storage.get(key).isTimeout(timeout)) {
			storage.remove(key);
			throw new AccessTimeoutException("Access Timeout for key=" + key);
		}
	}

	private void checkAccessCount(Object key) throws AccessCountExceedException {
		if (storage.containsKey(key) && storage.get(key).isExceedMaxAccessCount(maxAccessCount)) {
			storage.remove(key);
			throw new AccessCountExceedException("Access Count Exceed for key=" + key);
		}
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public float getBalanceFactor() {
		return balanceFactor;
	}

	public void setBalanceFactor(float balanceFactor) {
		this.balanceFactor = balanceFactor;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public int getMaxAccessCount() {
		return maxAccessCount;
	}

	public void setMaxAccessCount(int maxAccessCount) {
		this.maxAccessCount = maxAccessCount;
	}

	public boolean isRefreshTimeOnAccess() {
		return refreshTimeOnAccess;
	}

	public void setRefreshTimeOnAccess(boolean refreshTimeOnAccess) {
		this.refreshTimeOnAccess = refreshTimeOnAccess;
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			storage.clear();
			purgeThreshold = 0;
		} finally {
			super.finalize();
		}
	}
}
