package com.unionpay.upop.sdk.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Set;

import com.aurfy.haze.utils.ResourceUtils;

public class PropLoader {

	private static final String DEFAULT_CONF_FOLDER = "unionpay/";

	private String filename;
	private Properties prop;

	public PropLoader(String filename) {
		try {
			loadProperties(filename);
		} catch (Throwable t) {
			t.printStackTrace();
			throw new RuntimeException("fail to load " + filename);
		}
	}

	private void loadProperties(String filename) throws IOException {
		prop = new Properties();
		InputStream in =ResourceUtils.getResourceAsStream(DEFAULT_CONF_FOLDER + filename, Thread.currentThread().getContextClassLoader());
//		InputStream in = new FileInputStream(Thread.currentThread().getContextClassLoader()
//				.getResource(DEFAULT_CONF_FOLDER + filename).getFile());
		prop.load(in);
		this.filename = filename;
	}

	/**
	 * 重新加载properties
	 */
	public void reload() throws IOException {
		loadProperties(filename);
	}

	public String getProperty(String name) {
		// 如果此时value是中文，则应该是乱码
		String value = prop.getProperty(name);
		if (value != null) {
			// 编码转换，从ISO8859-1转向指定编码
			try {
				value = new String(value.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	public Set<String> propertyNames() {
		return prop.stringPropertyNames();
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		PropLoader instance = new PropLoader("sdk.properties");
		System.out.println(instance.getProperty("version"));
		System.out.println(instance.prop.stringPropertyNames());

	}

}
