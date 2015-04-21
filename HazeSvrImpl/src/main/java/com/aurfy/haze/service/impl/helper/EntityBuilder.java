package com.aurfy.haze.service.impl.helper;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.entity.configuration.channel.BankEntity;
import com.aurfy.haze.entity.infra.CountryEntity;
import com.aurfy.haze.entity.infra.ExchangeRateEntity;
import com.aurfy.haze.entity.infra.UserEntity;
import com.aurfy.haze.entity.infra.VariableMapEntity;
import com.aurfy.haze.entity.infra.mer.MerchantEntity;
import com.aurfy.haze.entity.security.KeyStoreEntity;
import com.aurfy.haze.service.bean.configuration.channel.BankBean;
import com.aurfy.haze.service.bean.infra.CountryBean;
import com.aurfy.haze.service.bean.infra.ExchangeRateBean;
import com.aurfy.haze.service.bean.infra.UserBean;
import com.aurfy.haze.service.bean.infra.VariableMapBean;
import com.aurfy.haze.service.bean.infra.mer.MerchantBean;
import com.aurfy.haze.service.bean.security.KeyStoreBean;

public class EntityBuilder{

	private static final Logger logger = LoggerFactory.getLogger(EntityBuilder.class);

	public static UserEntity toUserEntity(UserBean userBean) {

		UserEntity entity = new UserEntity();
		try {
			BeanUtils.copyProperties(entity, userBean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from UserBean to UserEntity", e);
			throw new RuntimeException(e);
		}

		// copy other properties

		return entity;
	}
	
	public static CountryEntity toCountryEntity(CountryBean countryBean) {

		CountryEntity entity = new CountryEntity();
		try {
			BeanUtils.copyProperties(entity, countryBean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from CountryBean to CountryEntity", e);
			throw new RuntimeException(e);
		}

		// copy other properties

		return entity;
	}
	
	
	
	public static MerchantEntity toMerchantsEntity(MerchantBean merBean) {

		MerchantEntity entity = new MerchantEntity();
		try {
			BeanUtils.copyProperties(entity, merBean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from MerchantsBean to MerchantsEntity", e);
			throw new RuntimeException(e);
		}
		// copy other properties
		return entity;
	}
	

	public static BankEntity toBankEntity(BankBean bankBean) {

		BankEntity entity = new BankEntity();
		try {
			BeanUtils.copyProperties(entity, bankBean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from BankBean to BankEntity", e);
			throw new RuntimeException(e);
		}
		// copy other properties
		return entity;
	}
	
	public static <T> T toEntity(T entity, Object bean) {

		try {
			BeanUtils.copyProperties(entity, bean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from Bean to Entity", e);
			throw new RuntimeException(e);
		}
		// copy other properties
		return entity;
	}
	
	public static VariableMapEntity toVarMapEntity(VariableMapBean varMapBean) {

		VariableMapEntity entity = new VariableMapEntity();
		try {
			BeanUtils.copyProperties(entity, varMapBean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from MerchantsBean to MerchantsEntity", e);
			throw new RuntimeException(e);
		}
		// copy other properties
		return entity;
	}
	
	public static ExchangeRateEntity toExchangeRateEntity (ExchangeRateBean exchangeRateBean){
		ExchangeRateEntity entity = new ExchangeRateEntity();
		try{
			BeanUtils.copyProperties(entity, exchangeRateBean);
		}
		 catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("error copy properties from ExchangeRateBean to ExchangeRateEntity", e);
				throw new RuntimeException(e);
		}
		// copy other properties
		return entity;
	}
	
	public static KeyStoreEntity toKeyStoreEntity(KeyStoreBean keyStoreBean) {

		KeyStoreEntity entity = new KeyStoreEntity();
		try {
			BeanUtils.copyProperties(entity, keyStoreBean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from KeyStoreBean to KeyStoreEntity", e);
			throw new RuntimeException(e);
		}

		// copy other properties

		return entity;
	}
	
}
