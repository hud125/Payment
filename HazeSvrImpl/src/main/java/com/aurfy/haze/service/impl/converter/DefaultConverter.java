package com.aurfy.haze.service.impl.converter;

import java.beans.PropertyDescriptor;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.entity.Entity;
import com.aurfy.haze.service.bean.Bean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.impl.helper.MapperRegistry;
import com.aurfy.haze.utils.ReflectionUtils;

/**
 * default converter using property copy.
 * 
 * @author hermano
 *
 */
class DefaultConverter implements Converter {

	private static final Logger logger = LoggerFactory.getLogger(DefaultConverter.class);

	public DefaultConverter() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Bean entity2Bean(Class<? extends Bean> beanClass, Entity... entities) throws RuntimeServiceException {
		if (entities.length == 0) {
			return null;
		}
		try {
			Bean dest = ReflectionUtils.newInstance(beanClass);

			PropertyUtilsBean propUtil = new PropertyUtilsBean();

			// Copy the properties, converting as necessary
			if (entities[0] instanceof DynaBean) {
				DynaProperty[] origDescriptors = ((DynaBean) entities[0]).getDynaClass().getDynaProperties();
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					// Need to check isReadable() for WrapDynaBean
					// (see Jira issue# BEANUTILS-61)
					if (propUtil.isReadable(entities[0], name) && propUtil.isWriteable(dest, name)) {
						Object value = ((DynaBean) entities[0]).get(name);
						BeanUtils.copyProperty(dest, name, value);
					}
				}
			} else if (entities[0] instanceof Map) {
				// Map properties are always of type <String, Object>
				Map<String, Object> propMap = (Map<String, Object>) entities[0];
				for (Map.Entry<String, Object> entry : propMap.entrySet()) {
					String name = entry.getKey();
					if (propUtil.isWriteable(dest, name)) {
						BeanUtils.copyProperty(dest, name, entry.getValue());
					}
				}
			} else /* if (entities[0] is a standard JavaBean) */{
				PropertyDescriptor[] origDescriptors = propUtil.getPropertyDescriptors(entities[0]);
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if ("class".equals(name)) {
						continue; // No point in trying to set an object's class
					}
					if (propUtil.isReadable(entities[0], name) && propUtil.isWriteable(dest, name)) {
						try {
							Object value = propUtil.getSimpleProperty(entities[0], name);
							// nested call to convert entity to bean
							// TODO: should consider List or Map case
							if (value != null && value instanceof Entity) {
								Class<? extends Entity> fieldEntityClass = (Class<? extends Entity>) value.getClass();
								Class<? extends Bean> fieldBeanClass = MapperRegistry.getBeanClass(fieldEntityClass);
								if (fieldBeanClass != null) {
									Converter c = ConverterHelper.getConverter(fieldEntityClass);
									Bean fieldBeanValue = c.entity2Bean(fieldBeanClass, (Entity) value);
									BeanUtils.copyProperty(dest, name, fieldBeanValue);
									continue;
								}
							}
							BeanUtils.copyProperty(dest, name, value);
						} catch (NoSuchMethodException e) {
							// Should not happen
						}
					}
				}
			}
			return dest;
		} catch (Exception e) {
			logger.error("error copy properties from '{}' to '{}'", entities[0].getClass().getSimpleName(),
					beanClass.getSimpleName(), e);
			throw new RuntimeServiceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Entity bean2Entity(Class<? extends Entity> entityClass, Bean... beans) throws RuntimeServiceException {
		if (beans.length == 0) {
			return null;
		}
		try {
			Entity dest = ReflectionUtils.newInstance(entityClass);

			PropertyUtilsBean propUtil = new PropertyUtilsBean();

			// Copy the properties, converting as necessary
			if (beans[0] instanceof DynaBean) {
				DynaProperty[] origDescriptors = ((DynaBean) beans[0]).getDynaClass().getDynaProperties();
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					// Need to check isReadable() for WrapDynaBean
					// (see Jira issue# BEANUTILS-61)
					if (propUtil.isReadable(beans[0], name) && propUtil.isWriteable(dest, name)) {
						Object value = ((DynaBean) beans[0]).get(name);
						BeanUtils.copyProperty(dest, name, value);
					}
				}
			} else if (beans[0] instanceof Map) {
				// Map properties are always of type <String, Object>
				Map<String, Object> propMap = (Map<String, Object>) beans[0];
				for (Map.Entry<String, Object> entry : propMap.entrySet()) {
					String name = entry.getKey();
					if (propUtil.isWriteable(dest, name)) {
						BeanUtils.copyProperty(dest, name, entry.getValue());
					}
				}
			} else /* if (beans[0] is a standard JavaBean) */{
				PropertyDescriptor[] origDescriptors = propUtil.getPropertyDescriptors(beans[0]);
				for (int i = 0; i < origDescriptors.length; i++) {
					String name = origDescriptors[i].getName();
					if ("class".equals(name)) {
						continue; // No point in trying to set an object's class
					}
					if (propUtil.isReadable(beans[0], name) && propUtil.isWriteable(dest, name)) {
						try {
							Object value = propUtil.getSimpleProperty(beans[0], name);
							// nested call to convert bean to entity
							// TODO: should consider List or Map case
							if (value != null && value instanceof Bean) {
								Class<? extends Bean> fieldBeanClass = (Class<? extends Bean>) value.getClass();
								Class<? extends Entity> fieldEntityClass = MapperRegistry
										.getEntityClass(fieldBeanClass);
								if (fieldEntityClass != null) {
									Converter c = ConverterHelper.getConverter(fieldBeanClass);
									Entity fieldEntityValue = c.bean2Entity(fieldEntityClass, (Bean) value);
									BeanUtils.copyProperty(dest, name, fieldEntityValue);
									continue;
								}
							}
							BeanUtils.copyProperty(dest, name, value);
						} catch (NoSuchMethodException e) {
							// Should not happen
						}
					}
				}
			}
			return dest;
		} catch (Exception e) {
			logger.error("error copy properties from '{}' to '{}'", beans[0].getClass().getSimpleName(),
					entityClass.getSimpleName(), e);
			throw new RuntimeServiceException(e);
		}
	}

}
