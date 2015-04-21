package com.aurfy.haze.service.impl.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.dao.infra.ExchangeRateMapper;
import com.aurfy.haze.entity.infra.ExchangeRateEntity;
import com.aurfy.haze.service.api.infrastructure.CurrencyService;
import com.aurfy.haze.service.bean.infra.ExchangeRateBean;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.exceptions.ObjectNotFoundException;
import com.aurfy.haze.service.impl.BaseHazeService;
import com.aurfy.haze.service.impl.helper.BeanBuilder;

@Service(AOP_NAME.CURRENCY_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CurrencyServiceImpl extends BaseHazeService implements CurrencyService {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

	@Autowired
	private ExchangeRateMapper exchangeRateMapper;

	@Override
	public ExchangeRateBean getExchangeRate(Currency fromCurrency, Currency toCurrency) throws ServiceException {
		try {
			ExchangeRateEntity exchangeRateEntity = exchangeRateMapper.selectExchangeRateByCurrency(
					fromCurrency.getName(), toCurrency.getName());
			ExchangeRateBean exchangeRateBean = BeanBuilder.toExchangeRateBean(exchangeRateEntity);
			return exchangeRateBean;
		} catch (RuntimeException e) {
			logger.error("can not find exchangeRate", e);
			throw new ObjectNotFoundException("can not find currency", e);
		}
	}

}
