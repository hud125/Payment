package com.aurfy.haze.dao.infra;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.ExchangeRateEntity;

@Component(AOP_MAPPER.EXCHANGE_RATE_MAPPER)
@MapperEntity(value = ExchangeRateEntity.class, CRUDBeanRequired = true)
public interface ExchangeRateMapper extends CRUDMapper {

	/**
	 * get a merchantRate associated with the given exchangeRate name.
	 * 
	 * @param exchangeRateName
	 * @return
	 */
	ExchangeRateEntity selectExchangeRateByName(@Param("exchangeName") String exchangeRateName);

	/**
	 * get a merchantRate associated with the given fromCurrency and toCurrency.
	 * 
	 * @param exchangeRateName
	 * @return
	 */
	ExchangeRateEntity selectExchangeRateByCurrency(@Param("fromCurrency") String fromCurrency,
			@Param("toCurrency") String toCurrency);

}
