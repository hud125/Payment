package com.aurfy.haze.service.impl.helper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.entity.infra.CountryEntity;
import com.aurfy.haze.entity.infra.ExchangeRateEntity;
import com.aurfy.haze.entity.infra.NotificationEntity;
import com.aurfy.haze.entity.infra.UserEntity;
import com.aurfy.haze.entity.infra.mer.MerchantEntity;
import com.aurfy.haze.entity.perm.PermAssignmentEntity;
import com.aurfy.haze.entity.security.KeyStoreEntity;
import com.aurfy.haze.service.bean.Bean;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.infra.CountryBean;
import com.aurfy.haze.service.bean.infra.ExchangeRateBean;
import com.aurfy.haze.service.bean.infra.NotificationBean;
import com.aurfy.haze.service.bean.infra.UserBean;
import com.aurfy.haze.service.bean.infra.mer.MerchantBean;
import com.aurfy.haze.service.bean.perm.PermAssignmentBean;
import com.aurfy.haze.service.bean.perm.PermEntryBean;
import com.aurfy.haze.service.bean.security.KeyStoreBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.utils.ReflectionUtils;

public class BeanBuilder {

	private static final Logger logger = LoggerFactory.getLogger(BeanBuilder.class);

	public static <T> T convertBean(Class<T> clazz, Bean bean) {

		try {
			T vo = ReflectionUtils.newInstance(clazz);
			BeanUtils.copyProperties(vo, bean);
			return vo;
		} catch (Exception e) {
			logger.error("error copy properties from '{}' to '{}'", bean.getClass().getSimpleName(),
					clazz.getSimpleName(), e);
			throw new RuntimeServiceException(e);
		}
	}

	public static <T> List<T> convertBean(Class<T> clazz, List<CRUDBean> beans) {

		List<T> vos = new ArrayList<T>();
		if (beans != null) {
			for (Bean bean : beans) {
				vos.add(convertBean(clazz, bean));
			}
		}
		return vos;
	}

	public static <T> Pagination<T> convertBean(Class<T> clazz, Pagination<CRUDBean> pagination) {

		Pagination<T> changePagination = null;
		if (pagination != null) {
			changePagination = new Pagination<T>(pagination.getTotalRows(), pagination.getCurrPageNum(),
					pagination.getPageSize());
			List<T> vos = convertBean(clazz, pagination.getPageData());
			changePagination.setPageData(vos);
		}
		return changePagination;
	}

	public static UserBean toUserBean(UserEntity userEntity) {

		UserBean bean = new UserBean();
		try {
			BeanUtils.copyProperties(bean, userEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from UserEntity to UserBean", e);
			throw new RuntimeException(e);
		}

		// copy other properties

		return bean;
	}

	public static CountryBean toCountryBean(CountryEntity countryEntity) {

		CountryBean bean = new CountryBean();
		try {
			BeanUtils.copyProperties(bean, countryEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from CountryEntity to CountryBean", e);
			throw new RuntimeException(e);
		}

		// copy other properties

		return bean;
	}

	public static MerchantBean toMerchantsBean(MerchantEntity merEntity) {

		MerchantBean bean = new MerchantBean();
		try {
			BeanUtils.copyProperties(bean, merEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from MerchantsEntity to MerchantsBean", e);
			throw new RuntimeException(e);
		}

		// copy other properties

		return bean;
	}

	public static List<PermAssignmentBean> toPermAssignmentBeans(List<PermAssignmentEntity> assignEntitys) {

		List<PermAssignmentBean> assignBeans = new ArrayList<PermAssignmentBean>();
		try {
			PermAssignmentBean bean = null;
			for (PermAssignmentEntity assignEntity : assignEntitys) {
				bean = new PermAssignmentBean();
				BeanUtils.copyProperties(bean, assignEntity);
				PermEntryBean permEntryBean = new PermEntryBean();
				BeanUtils.copyProperties(permEntryBean, assignEntity.getPermEntry());
				bean.setPermEntryBean(permEntryBean);
				assignBeans.add(bean);
			}

		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from assignEntitys to assignBeans", e);
			throw new RuntimeException(e);
		}

		// copy other properties

		return assignBeans;
	}

	public static ExchangeRateBean toExchangeRateBean(ExchangeRateEntity exchangeRateEntity) {

		ExchangeRateBean bean = new ExchangeRateBean();
		try {
			BeanUtils.copyProperties(bean, exchangeRateEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from ExchangeRateEntity to ExchangeRateBean", e);
			throw new RuntimeException(e);
		}
		// copy other properties
		return bean;
	}

	public static KeyStoreBean toExchangeKeyStoreBean(KeyStoreEntity keyStoreEntity) {

		KeyStoreBean bean = new KeyStoreBean();
		try {
			BeanUtils.copyProperties(bean, keyStoreEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from KeyStoreEntity to KeyStoreBean", e);
			throw new RuntimeException(e);
		}
		// copy other properties
		return bean;
	}
	public static List<NotificationBean> toNotificatioinBean(List<NotificationEntity> notifiEntitys) {
		List<NotificationBean> notifiBeans = new ArrayList<NotificationBean>();
		try {
			NotificationBean notifi = null;
			for(NotificationEntity notifiEntity:notifiEntitys) {
				notifi = new NotificationBean();
				BeanUtils.copyProperties(notifi, notifiEntity);
				notifiBeans.add(notifi);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from NotificationEntity to NotificationBean", e);
			throw new RuntimeException(e);
		}
		// copy other properties
		return notifiBeans;
	}
}
