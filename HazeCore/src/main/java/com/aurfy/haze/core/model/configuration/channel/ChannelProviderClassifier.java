package com.aurfy.haze.core.model.configuration.channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler("com.aurfy.haze.dao.handler.ChannelProviderClassifierHadler")
public class ChannelProviderClassifier {

	private static Map<String, ChannelProviderClassifier> map = new ConcurrentHashMap<String, ChannelProviderClassifier>(
			10);

	public static final ChannelProviderClassifier UNIONPAY_EXPRESS_PAY = new ChannelProviderClassifier("UP_EXPRESS",
			"com.aurfy.haze.service.impl.bank.handler.UnionPayExpressHandler");
	public static final ChannelProviderClassifier UNIONPAY_SECURE_PAY = new ChannelProviderClassifier("UP_SECURE",
			"com.aurfy.haze.service.impl.bank.handler.UnionPaySecureHandler");

	private String code;
	private String handlerClass;

	private ChannelProviderClassifier(String code, String handlerClass) {
		this.code = code;
		this.handlerClass = handlerClass;
		map.put(code, this);
	}

	public static ChannelProviderClassifier parseByCode(String code) {
		return map.get(code);// TODO
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getHandlerClass() {
		return handlerClass;
	}

	public void setHandlerClass(String className) {
		this.handlerClass = className;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((handlerClass == null) ? 0 : handlerClass.hashCode());
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
		ChannelProviderClassifier other = (ChannelProviderClassifier) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (handlerClass == null) {
			if (other.handlerClass != null)
				return false;
		} else if (!handlerClass.equals(other.handlerClass))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelProviderClassifier [code=");
		builder.append(code);
		builder.append(", handlerClass=");
		builder.append(handlerClass);
		builder.append("]");
		return builder.toString();
	}

}
