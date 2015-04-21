package com.aurfy.haze.service.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.conf.HazeDefaultConfig;
import com.aurfy.haze.utils.ReflectionUtils;
import com.aurfy.haze.utils.StringUtils;

/**
 * overwritable config file reader
 * 
 * @author hermano
 *
 */
public abstract class OverwritableConfigReader {

	private static final Logger logger = LoggerFactory.getLogger(OverwritableConfigReader.class);
	public static final String DEFAULT_KEY_PREFIX = "default";
	public static final String DEFAULT_CONNECTOR = ".";

	private Properties props;
	private List<String> keyPrefixes;

	public OverwritableConfigReader(String propertyFile) {
		this.props = HazeDefaultConfig.getProperties(propertyFile);
	}

	public OverwritableConfigReader(Properties props) {
		this.props = props;
	}

	/**
	 * try to get the value by convention
	 * 
	 * @param propertyName
	 * @param defaultValue
	 * @return
	 */
	public String getAsString(String propertyName, String defaultValue) {
		String key;
		String value;
		// just ensure its called at least once
		if (this.keyPrefixes == null || this.keyPrefixes.size() == 0) {
			calculateKeyPrefixes();
		}
		for (String prefix : this.keyPrefixes) {
			key = prefix + DEFAULT_CONNECTOR + propertyName;
			if (props.containsKey(key)) {
				value = props.getProperty(key);
				String msg = StringUtils.formatMessage("config key={}, value={}", key, value);
				logger.debug(msg);
				return value;
			}
		}
		logger.warn("no key found, use default value ({})", defaultValue);
		return defaultValue;
	}

	public String getAsString(String propertyName) {
		return getAsString(propertyName, null);
	}

	public boolean getAsBoolean(String propertyName, boolean defaultValue) {
		String s = getAsString(propertyName, null);
		return s == null ? defaultValue : Boolean.valueOf(s);
	}

	public boolean getAsBoolean(String propertyName) {
		return getAsBoolean(propertyName, false);
	}

	public int getAsInteger(String propertyName, int defaultValue) {
		String s = getAsString(propertyName, null);
		return s == null ? defaultValue : Integer.valueOf(s);
	}

	public int getAsInteger(String propertyName) {
		return getAsInteger(propertyName, 0);
	}

	public long getAsLong(String propertyName, long defaultValue) {
		String s = getAsString(propertyName, null);
		return s == null ? defaultValue : Long.valueOf(s);
	}

	public long getAsLong(String propertyName) {
		return getAsLong(propertyName, 0L);
	}

	@SuppressWarnings("unchecked")
	public <E extends Enum<?>> E getAsEnum(String propertyName, Class<E> clazz, E defaultValue) {
		String s = getAsString(propertyName, null);
		if (s == null) {
			return defaultValue;
		} else {
			Object obj = ReflectionUtils.invokeStaticMethod(clazz, "valueOf", s, String.class);
			return obj == null ? defaultValue : (E) obj;
		}
	}

	public <E extends Enum<?>> E getAsEnum(String propertyName, Class<E> clazz) {
		return getAsEnum(propertyName, clazz, null);
	}

	protected void calculateKeyPrefixes() {
		this.keyPrefixes = getKeyPrefixesByConvention();
		if (keyPrefixes == null) {
			keyPrefixes = new ArrayList<String>(1);
		}
		keyPrefixes.add(DEFAULT_KEY_PREFIX);
	}

	/**
	 * @return all acceptable key prefixes in desired order
	 */
	protected abstract List<String> getKeyPrefixesByConvention();

}
