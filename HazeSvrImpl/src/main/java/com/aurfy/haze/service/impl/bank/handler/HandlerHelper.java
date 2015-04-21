package com.aurfy.haze.service.impl.bank.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.core.model.configuration.channel.ChannelProviderClassifier;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.utils.StringUtils;

/**
 * @author hermano
 */
public final class HandlerHelper {

	private static final Logger logger = LoggerFactory.getLogger(HandlerHelper.class);

	public static BankHandler getHandler(ChannelProviderClassifier providerClassifier) {
		String handlerClass = providerClassifier.getHandlerClass();
		BankHandler handler;
		try {
			handler = BankHandler.class.cast(Class.forName(handlerClass).newInstance());
			return handler;
		} catch (Exception e) {
			final String msg = StringUtils.formatMessage("init bank handler '{}' failed", handlerClass);
			logger.error(msg, e);
			throw new RuntimeServiceException(msg, e);
		}
	}

}
