package com.aurfy.haze.web.helper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.service.bean.Bean;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.configuration.channel.BankBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.utils.ReflectionUtils;
import com.aurfy.haze.web.vo.VO;
import com.aurfy.haze.web.vo.configuration.channel.BankVO;

public class VOBuilder {

	private static final Logger logger = LoggerFactory.getLogger(VOBuilder.class);

	public static BankVO toBankVO(BankBean bankBean) {

		BankVO vo = new BankVO();
		try {
			BeanUtils.copyProperties(vo, bankBean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("error copy properties from BankBean to BankVO", e);
			throw new RuntimeException(e);
		}
		// copy other properties
		return vo;
	}

	public static List<BankVO> toBankVO(List<BankBean> bankBeans) {

		List<BankVO> beans = new ArrayList<BankVO>();
		if (beans != null) {
			for (BankBean bean : bankBeans) {
				beans.add(toBankVO(bean));
			}
		}
		return beans;
	}

	public static Pagination<BankVO> toBankVO(Pagination<BankBean> pagination) {

		Pagination<BankVO> voPagination = null;
		if (pagination != null) {
			voPagination = new Pagination<BankVO>(pagination.getTotalRows(), pagination.getCurrPageNum(),
					pagination.getPageSize());
			List<BankVO> beans = toBankVO(pagination.getPageData());
			voPagination.setPageData(beans);
		}
		return voPagination;
	}

	public static <T extends VO> T bean2VO(Class<T> voClazz, Bean bean) {

		try {
			T vo = ReflectionUtils.newInstance(voClazz);
			BeanUtils.copyProperties(vo, bean);
			return vo;
		} catch (Exception e) {
			logger.error("error copy properties from '{}' to '{}'", bean.getClass().getSimpleName(),
					voClazz.getSimpleName(), e);
			throw new RuntimeServiceException(e);
		}
	}

	public static <T extends VO> List<T> bean2VO(Class<T> voClazz, List<CRUDBean> beans) {

		List<T> vos = new ArrayList<T>();
		if (beans != null) {
			for (Bean bean : beans) {
				vos.add(bean2VO(voClazz, bean));
			}
		}
		return vos;
	}

	// public static <T extends VO> Pagination<T> bean2VOPage(Class<T> voClazz, Pagination<Bean> pagination) {
	//
	// Pagination<T> voPagination = null;
	// if (pagination != null) {
	// voPagination = new Pagination<T>(pagination.getTotalRows(), pagination.getCurrPageNum(),
	// pagination.getPageSize());
	// List<T> vos = bean2VO(voClazz, pagination.getPageData());
	// voPagination.setPageData(vos);
	// }
	// return voPagination;
	// }

	public static <T extends VO> Pagination<T> bean2VO(Class<T> voClazz, Pagination<CRUDBean> pagination) {

		Pagination<T> voPagination = null;
		if (pagination != null) {
			voPagination = new Pagination<T>(pagination.getTotalRows(), pagination.getCurrPageNum(),
					pagination.getPageSize());
			List<T> vos = bean2VO(voClazz, pagination.getPageData());
			voPagination.setPageData(vos);
		}
		return voPagination;
	}
}
