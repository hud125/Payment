package com.aurfy.haze.service.impl.converter;

import com.aurfy.haze.entity.Entity;
import com.aurfy.haze.service.bean.Bean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;

/**
 * the converter between entity and bean.
 * 
 * @author hermano
 *
 */
public interface Converter {

	/**
	 * convert to a service bean using mapper entity input(s).
	 * 
	 * @param beanClass
	 * @param entities
	 * @return
	 * @throws RuntimeServiceException
	 */
	public Bean entity2Bean(Class<? extends Bean> beanClass, Entity... entities) throws RuntimeServiceException;

	/**
	 * convert to a mapper entity using service bean input(s).
	 * 
	 * @param entityClass
	 * @param beans
	 * @return
	 * @throws RuntimeServiceException
	 */
	public Entity bean2Entity(Class<? extends Entity> entityClass, Bean... beans) throws RuntimeServiceException;
}
