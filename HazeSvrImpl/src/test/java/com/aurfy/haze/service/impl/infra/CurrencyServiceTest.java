package com.aurfy.haze.service.impl.infra;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.infra.ExchangeRateBean;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;

public class CurrencyServiceTest extends ServiceUnitTest {
	
	@Autowired
	private CRUDService currencyService;
	
	public ExchangeRateBean newExchangeRate(){
		ExchangeRateBean exchangerate = new ExchangeRateBean();
		exchangerate.setID(UUID.randomUUID().toString());
		exchangerate.setExchangeName("USA_to_CNY");
		exchangerate.setFromCurrency(Currency.USD);
		exchangerate.setToCurrency(Currency.CNY);
		exchangerate.setBuyRate(new BigDecimal("6.0000"));
		exchangerate.setCashBuyRate(new BigDecimal("6.0000"));
		exchangerate.setSellRate(new BigDecimal("6.0000"));
		exchangerate.setCashSellRate(new BigDecimal("6.0000"));
		exchangerate.setActive(true);
		exchangerate.setComments("This is test ExchangeRate");
		exchangerate.setOwnerId("Sam");
	    exchangerate.setCreateDate(new Date());
		exchangerate.setUpdateDate(exchangerate.getCreateDate());
		return exchangerate;
	}
	
	public ExchangeRateBean getExchangeRate() throws RuntimeServiceException, ServiceException {
		ExchangeRateBean exchangerate = newExchangeRate();
		String oldId = exchangerate.getID();
		currencyService.create(exchangerate);
		String newId = exchangerate.getID();
		assert (oldId.equals(newId));
		return exchangerate;
	}
	
	@Test
	@Transactional
	public void testInsertExchangeRate() throws RuntimeServiceException, ServiceException {
		getExchangeRate();
	}
	
	public void testGetExchangeRate() throws ServiceException{
		ExchangeRateBean exchangerate = newExchangeRate();
		currencyService.create(exchangerate);
		Currency fromCurrency = exchangerate.getFromCurrency();
		Currency toCurrency = exchangerate.getToCurrency();
//		ExchangeRateBean tempexchangerate = currencyService.getExchangeRate(fromCurrency, toCurrency);
//		Assert.assertTrue(tempexchangerate.equals(exchangerate));
	}	
}
