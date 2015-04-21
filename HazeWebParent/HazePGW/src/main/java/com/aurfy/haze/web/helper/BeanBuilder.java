package com.aurfy.haze.web.helper;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.configuration.channel.BankBean;
import com.aurfy.haze.service.bean.txn.MerTxnBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.utils.ReflectionUtils;
import com.aurfy.haze.web.vo.VO;
import com.aurfy.haze.web.vo.txn.ExpressPayReqVO;

public class BeanBuilder {

	private static final Logger logger = LoggerFactory.getLogger(BeanBuilder.class);

	public static MerTxnBean toMerTxnBean(ExpressPayReqVO merTxnVO) {

		MerTxnBean bean = new MerTxnBean();
		try {
			BeanUtils.copyProperties(bean, merTxnVO);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from MerTxnVO to MerTxnBean", e);
			throw new RuntimeException(e);
		}

		// copy other properties

		return bean;
	}

	public static <T extends CRUDBean> T vo2Bean(Class<T> beanClass, VO... vos) {
		if (vos.length == 0) {
			return null;
		}
		try {
			T bean = ReflectionUtils.newInstance(beanClass);
			BeanUtils.copyProperties(bean, vos[0]);
			return bean;
		} catch (Exception e) {
			logger.error("error copy properties from '{}' to '{}'", vos[0].getClass().getSimpleName(),
					beanClass.getSimpleName(), e);
			throw new RuntimeServiceException(e);
		}
	}
}
