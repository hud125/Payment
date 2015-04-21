package com.aurfy.haze.service.impl.tools;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http client (v4.3) builder<br/>
 * not responsible for close the client.
 * 
 * @author hermano
 *
 */
public abstract class HttpClientBuilder {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientBuilder.class);

	private PoolingHttpClientConnectionManager connMgr;
	private RequestConfig reqConfig;

	public HttpClientBuilder() {
		super();
	}

	private void build() {

		int maxConn = getMaxConn();
		int maxConnPerRoute = getMaxConnPerRoute();
		int timeout = getConnTimeout();

		// initialize PoolingHttpClientConnectionManager for stand-alone connection
		connMgr = new PoolingHttpClientConnectionManager();
		// Increase max total connection to maxConn
		connMgr.setMaxTotal(maxConn);
		// Increase default max connection per route to maxConnPerRoute
		connMgr.setDefaultMaxPerRoute(maxConnPerRoute);

		reqConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout)
				.setSocketTimeout(timeout).build();

		logger.info("initialize HttpClientBuilder, maxConn={}&maxConnPerRoute={}&timeout={}", maxConn, maxConnPerRoute,
				timeout);
	}

	public CloseableHttpClient createHttpClient() {
		this.build();
		return HttpClients.custom().setConnectionManager(connMgr).setDefaultRequestConfig(reqConfig).build();
	}

	/**
	 * close the internal HttpClientConnectionManager
	 * 
	 * @param throwException
	 *            if exception should be thrown while closing
	 */
	public void closeHttpConnManager(boolean throwException) {
		try {
			connMgr.close();
			logger.info("ConnectionManager in HttpClientBuilder closed.");
		} catch (Throwable t) {
			logger.warn("error closing ConnectionManager in HttpClientBuilder", t);
			if (throwException) {
				throw new RuntimeException("error closing ConnectionManager in HttpClientBuilder", t);
			}
		}
	}

	protected abstract int getMaxConn();

	protected abstract int getMaxConnPerRoute();

	protected abstract int getConnTimeout();

}
