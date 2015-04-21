package com.aurfy.haze.service.impl.helper;

import static com.aurfy.haze.service.conf.ServiceImplConstant.COMPONENT_NAME.MAPPER_REGISTRY;
import static com.aurfy.haze.utils.ScannerUtils.scan4Subclasses;
import static org.apache.commons.lang3.StringUtils.removeEnd;
import static org.apache.commons.lang3.StringUtils.uncapitalize;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.entity.Entity;
import com.aurfy.haze.service.bean.Bean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.utils.ScannerUtils;

/**
 * Haze mapper registry by using spring application context.
 * 
 * @author hermano
 *
 */
@Component(MAPPER_REGISTRY)
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MapperRegistry {

	private static final Logger logger = LoggerFactory.getLogger(MapperRegistry.class);
	private static final String BEAN_SUFFIX = "Bean";
	private static final String ENTITY_SUFFIX = "Entity";
	private static final String MAPPER_SUFFIX = "Mapper";

	private static Map<String, RegistryEntry> registryMapping = new ConcurrentHashMap<String, RegistryEntry>();
	private static boolean earlyFail = true;

	@Resource
	private ApplicationContext appContext;

	static {
		createMapping();
		addCustomMapping();

		StringBuffer buffer = new StringBuffer("create mapping for MapperRegistry: \n");
		for (RegistryEntry entry : registryMapping.values()) {
			buffer.append('\t');
			buffer.append(StringUtils.rightPad(entry.getPojoName(), 20));
			buffer.append("=> [BeanClass=");
			buffer.append(getClassName(entry.getBeanClass()));
			buffer.append(", entityClass=");
			buffer.append(getClassName(entry.getEntityClass()));
			buffer.append(", mapperClass=");
			buffer.append(getClassName(entry.getMapperClass()));
			buffer.append(", mapperName=");
			buffer.append(entry.getMapperName());
			buffer.append(']');
			buffer.append('\n');
		}
		logger.debug(buffer.toString());
	}

	private static void checkEarlyFail(String msg, Object... objects) {
		FormattingTuple ft = MessageFormatter.arrayFormat(msg, objects);
		logger.error(ft.getMessage(), ft.getThrowable());
		if (earlyFail) {
			throw new RuntimeServiceException(ft.getMessage(), ft.getThrowable());
		}
	}

	/**
	 * create mapper,bean,entity mapping using scanner.
	 */
	@SuppressWarnings("unchecked")
	private static void createMapping() {

		Set<Class<?>> mappers = ScannerUtils.scan(CRUDMapper.class, CRUDMapper.class.getPackage().getName(),
				MapperEntity.class, true);

		// bean classes
		Set<Class<? extends Bean>> beanClasses = scan4Subclasses(Bean.class, Bean.class.getPackage().getName(), false);
		Map<String, Class<? extends Bean>> beanMap = new HashMap<String, Class<? extends Bean>>(beanClasses.size());
		// explain: possible key overwrite for identical bean name, but we won't have this scenario in reality, so just
		// ignore this.
		for (Class<? extends Bean> clazz : beanClasses) {
			beanMap.put(bean2PojoName(clazz), clazz);
		}
		logger.debug("scanned bean classes: {}", beanMap.keySet());

		Class<? extends Entity> entityClass;
		RegistryEntry entry;
		String pojoName;
		for (Class<?> mapperClass : mappers) {
			entityClass = null;
			entry = new RegistryEntry();

			MapperEntity me = mapperClass.getAnnotation(MapperEntity.class);
			if (me == null) {
				checkEarlyFail("no MapperEntity annotation found for {}", mapperClass.getSimpleName());
			} else {
				entityClass = me.value();
			}
			pojoName = entity2PojoName(entityClass);
			entry.setPojoName(pojoName);
			entry.setBeanClass(beanMap.get(pojoName));

			// required CRUDBean missing
			if (entry.getBeanClass() == null && me.CRUDBeanRequired()) {
				checkEarlyFail("no bean class found for {}", pojoName);
			}
			// non-required CRUDBean present
			else if (entry.getBeanClass() != null && me.CRUDBeanRequired() == false) {
				logger.warn("non-required CRUDBean '{}Bean' found", pojoName);
			}

			entry.setEntityClass(entityClass);
			entry.setMapperClass((Class<? extends CRUDMapper>) mapperClass);
			// mapper name
			String aopMapperName = null;
			Component ac = mapperClass.getAnnotation(Component.class);
			if (ac != null) {
				aopMapperName = ac.value();
			}
			if (StringUtils.isEmpty(aopMapperName)) {
				aopMapperName = getMapperNameByConvention(pojoName);
			}
			entry.setMapperName(aopMapperName);
			registryMapping.put(pojoName, entry);
		}

	}

	/**
	 * add any custom case for mapping
	 */
	private static void addCustomMapping() {
		// registryMapping.put(pojoName, entry);
	}

	public static String entity2PojoName(Class<? extends Entity> entityClass) {
		return removeEnd(entityClass.getSimpleName(), ENTITY_SUFFIX);
	}

	public static String bean2PojoName(Class<? extends Bean> beanClass) {
		return removeEnd(beanClass.getSimpleName(), BEAN_SUFFIX);
	}

	private static String getMapperNameByConvention(String pojoName) {
		return uncapitalize(pojoName) + MAPPER_SUFFIX;
	}

	private MapperRegistry() {
	}

	/**
	 * get mapper by its AOP name
	 * 
	 * @param mapperName
	 * @return
	 */
	public CRUDMapper get(String mapperName) {
		return (CRUDMapper) appContext.getBean(mapperName);
	}

	/**
	 * get mapper by its corresponding entity class
	 * 
	 * @param entity
	 * @return
	 */
	public CRUDMapper get(Class<? extends Entity> entityClass) {
		return get(getMapperNameByConvention(entity2PojoName(entityClass)));
	}

	public static Class<? extends Entity> getEntityClass(Class<? extends Bean> beanClass) {
		String pojo = bean2PojoName(beanClass);
		if (registryMapping.containsKey(pojo)) {
			return registryMapping.get(pojo).getEntityClass();
		}
		return null;
	}

	public static Class<? extends Bean> getBeanClass(Class<? extends Entity> entityClass) {
		String pojo = entity2PojoName(entityClass);
		if (registryMapping.containsKey(pojo)) {
			return registryMapping.get(pojo).getBeanClass();
		}
		return null;
	}

	private static String getClassName(Class<?> clazz) {
		return clazz == null ? "<null>" : clazz.getName();
	}

}
