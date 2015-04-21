package com.aurfy.haze.service.impl.tools;

public class DefaultHttpClientBuilder extends HttpClientBuilder {

	private static final int DEFAULT_MAX_CONN = 1000;
	private static final int DEFAULT_MAX_CONN_PER_ROUTE = 10;
	private static final int DEFAULT_CONN_TIMEOUT = 15000;

	private final int maxConn;
	private final int maxConnPerRoute;
	private final int timeout;

	public DefaultHttpClientBuilder() {
		this(DEFAULT_MAX_CONN, DEFAULT_MAX_CONN_PER_ROUTE, DEFAULT_CONN_TIMEOUT);
	}

	public DefaultHttpClientBuilder(int maxConn, int maxConnPerRoute, int timeout) {
		this.maxConn = maxConn;
		this.maxConnPerRoute = maxConnPerRoute;
		this.timeout = timeout;
	}

	@Override
	protected int getMaxConn() {
		return this.maxConn;
	}

	@Override
	protected int getMaxConnPerRoute() {
		return this.maxConnPerRoute;
	}

	@Override
	protected int getConnTimeout() {
		return this.timeout;
	}

}
