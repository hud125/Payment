package com.aurfy.haze.service.api.infrastructure;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.bean.infra.ExchangeRateBean;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.exceptions.ObjectNotFoundException;

public interface CurrencyService extends HazeService {

	/**
	 * get the exchange rate from one currency to another
	 * 
	 * @param fromCurrency
	 * @param toCurrency
	 * @return
	 * @throws ObjectNotFoundException
	 *             if there's no exchange rate configed for the given two currency
	 */
	public ExchangeRateBean getExchangeRate(Currency fromCurrency, Currency toCurrency) throws ServiceException;

}
