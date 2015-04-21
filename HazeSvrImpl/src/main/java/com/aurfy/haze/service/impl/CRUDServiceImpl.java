package com.aurfy.haze.service.impl;

import static com.aurfy.haze.service.conf.ServiceImplConstant.COMPONENT_NAME.DEFAULT_CACHE_PROVIDER;
import static com.aurfy.haze.utils.StringUtils.formatMessage;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.entity.Entity;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.infra.UserBean;
import com.aurfy.haze.service.cache.CacheableService;
import com.aurfy.haze.service.cache.HazeCacheProvider;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.exceptions.ObjectNotFoundException;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.converter.Converter;
import com.aurfy.haze.service.impl.converter.ConverterHelper;
import com.aurfy.haze.service.impl.helper.MapperRegistry;
import com.aurfy.haze.utils.ReflectionUtils;
import com.aurfy.haze.utils.SecurityUtils;
import com.google.common.cache.LoadingCache;

@Service(AOP_NAME.CRUD_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CRUDServiceImpl extends BaseHazeService implements CRUDService, CacheableService<CRUDBean> {

	private static final Logger logger = LoggerFactory.getLogger(CRUDServiceImpl.class);

	@Autowired
	private MapperRegistry registry;

	@Autowired
	@Qualifier(DEFAULT_CACHE_PROVIDER)
	private HazeCacheProvider<UserBean> cacheProvider;

	private LoadingCache<String, UserBean> cache;

	public CRUDServiceImpl() {
	}

	@PostConstruct
	private void initCache() {
		// cache = cacheProvider.getCache(cacheProvider.getConfigByConvention(UserBean.class), this);
	}

	/**
	 * update and set ID/createDate/updateDate to default value if necessary.<br />
	 * std = standard
	 * 
	 * @param entity
	 */
	public static void updateStdProperties(Entity entity) {
		updateProperty(entity, "ID", SecurityUtils.UUID(), String.class);
		updateDateProperties(entity);
	}

	public static void updateDateProperties(Entity entity) {
		updateProperty(entity, "createDate", new Date(), Date.class);
		updateProperty(entity, "updateDate", new Date(), Date.class);
	}

	public static void updateProperty(Entity entity, String propertyName, Object newValue, Class<?> parameterTypes) {
		if (updateRequired(entity, propertyName)) {
			try {
				ReflectionUtils.invokeSetter(entity, propertyName, newValue, parameterTypes);
			} catch (Exception e) {
				logger.warn("set{} failed for {}", capitalize(propertyName), entity.getClass().getSimpleName(), e);
			}
		}
	}

	/**
	 * check if auto-update is required for the given property.
	 * 
	 * @param entity
	 * @param propertyName
	 * @return <code>true</code> if ID/createDate/updateDate is null or empty, <code>false</code> otherwise.
	 */
	public static <T> boolean updateRequired(Entity entity, String propertyName) {
		try {
			T t = ReflectionUtils.invokeGetter(entity, propertyName);
			if (t == null) {
				return true;
			} else if (t instanceof String) {
				return isBlank((String) t);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public CRUDBean create(CRUDBean bean) throws ServiceException, RuntimeServiceException {

		Class<? extends Entity> entityClass = MapperRegistry.getEntityClass(bean.getClass());
		Converter converter = ConverterHelper.getConverter(bean.getClass());
		CRUDMapper mapper = registry.get(entityClass);

		Entity entity = converter.bean2Entity(entityClass, bean);
		updateStdProperties(entity);

		int size = mapper.insert(entity);
		logger.debug("create new {}, affected row={}", entityClass.getSimpleName(), size);

		bean = (CRUDBean) converter.entity2Bean(bean.getClass(), entity);
		logger.trace("updated {}: {}", bean.getClass().getSimpleName(), bean.toString());

		return bean;
	}

	// TODO: set update_date automatically; retrieve from cache
	@Override
	@Transactional
	public CRUDBean retrieve(Class<? extends CRUDBean> beanClass, String id) throws ServiceException,
			RuntimeServiceException {

		Class<? extends Entity> entityClass = MapperRegistry.getEntityClass(beanClass);
		Converter converter = ConverterHelper.getConverter(beanClass);
		CRUDMapper mapper = registry.get(entityClass);

		Entity entity = mapper.selectOne(id);

		if (entity == null) {
			final String msg = formatMessage("{}(id={}) not found.", beanClass.getSimpleName(), id);
			logger.warn(msg);
			throw new ObjectNotFoundException(msg);
		}
		CRUDBean bean = (CRUDBean) converter.entity2Bean(beanClass, entity);
		logger.trace("retrieve {}: {}", beanClass.getSimpleName(), bean.toString());

		return bean;

		// try {
		// return cache.get(id);
		// } catch (ExecutionException e) {
		// throw new RuntimeServiceException("TODO");
		// }

	}

	// TODO: retrieve from cache
	@Override
	@Transactional
	public Pagination<CRUDBean> retrieve(Class<? extends CRUDBean> beanClass, int pageNum, int pageSize)
			throws ServiceException, RuntimeServiceException {
		Class<? extends Entity> entityClass = MapperRegistry.getEntityClass(beanClass);
		Converter converter = ConverterHelper.getConverter(beanClass);
		CRUDMapper mapper = registry.get(entityClass);

		Pagination<CRUDBean> pagination = new Pagination<CRUDBean>(mapper.countAll(), pageNum, pageSize);
		Entity filter = ReflectionUtils.newInstance(entityClass);
		List<Entity> entities = mapper.selectBy(null, pagination.getStartRow(), pagination.getPageSize());

		if (entities == null || entities.size() == 0) {
			logger.warn("no pagination data found for {}: {}", beanClass.getSimpleName(), pagination.toString());
			pagination.setPageData(new ArrayList<CRUDBean>(0));
		} else {
			List<CRUDBean> data = new ArrayList<CRUDBean>(entities.size());
			CRUDBean bean = null;
			for (Entity entity : entities) {
				bean = (CRUDBean) converter.entity2Bean(beanClass, entity);
				data.add(bean);
				logger.trace("retrieve {}: {}", beanClass.getSimpleName(), bean.toString());
			}
			pagination.setPageData(data);
		}
		return pagination;
	}

	// TODO: retrieve from cache
	@Override
	@Transactional
	public List<CRUDBean> retrieveAll(Class<? extends CRUDBean> beanClass) throws ServiceException,
			RuntimeServiceException {

		Class<? extends Entity> entityClass = MapperRegistry.getEntityClass(beanClass);
		Converter converter = ConverterHelper.getConverter(beanClass);
		CRUDMapper mapper = registry.get(entityClass);

		List<Entity> entities = mapper.selectAll();
		if (entities == null || entities.size() == 0) {
			logger.warn("no data found for {}", beanClass.getSimpleName());
			return new ArrayList<CRUDBean>(0);
		}

		List<CRUDBean> result = new ArrayList<CRUDBean>(entities.size());
		CRUDBean bean = null;
		for (Entity entity : entities) {
			bean = (CRUDBean) converter.entity2Bean(beanClass, entity);
			result.add(bean);
			logger.trace("retrieve {}: {}", beanClass.getSimpleName(), bean.toString());
		}

		return result;
	}

	// TODO: retrieve from cache
	@Override
	@Transactional
	public Pagination<CRUDBean> retrieveBy(Class<? extends CRUDBean> beanClass, CRUDBean bean, int pageNum, int pageSize)
			throws ServiceException, RuntimeServiceException {
		Class<? extends Entity> entityClass = MapperRegistry.getEntityClass(bean.getClass());
		Converter converter = ConverterHelper.getConverter(entityClass);
		Entity entity = converter.bean2Entity(entityClass, bean);
		CRUDMapper mapper = registry.get(entityClass);

		Pagination<CRUDBean> pagination = new Pagination<CRUDBean>(mapper.countBy(entity), pageNum, pageSize);
		List<Entity> entities = mapper.selectBy(entity, pagination.getStartRow(), pagination.getPageSize());

		if (entities == null || entities.size() == 0) {
			logger.warn("no pagination data found for {}: {}", beanClass.getSimpleName(), pagination.toString());
			pagination.setPageData(new ArrayList<CRUDBean>(0));
		} else {
			List<CRUDBean> data = new ArrayList<CRUDBean>(entities.size());
			CRUDBean newBean = null;
			for (Entity newEntity : entities) {
				newBean = (CRUDBean) converter.entity2Bean(beanClass, newEntity);
				data.add(newBean);
				logger.trace("retrieve {}: {}", beanClass.getSimpleName(), newBean.toString());
			}
			pagination.setPageData(data);
		}
		return pagination;
	}

	@Override
	@Transactional
	public CRUDBean update(CRUDBean bean) throws ServiceException, RuntimeServiceException {

		Class<? extends Entity> entityClass = MapperRegistry.getEntityClass(bean.getClass());
		Converter converter = ConverterHelper.getConverter(bean.getClass());
		CRUDMapper mapper = registry.get(entityClass);

		Entity entity = converter.bean2Entity(entityClass, bean);
		// ID will always be kept
		updateDateProperties(entity);

		int size = mapper.update(entity);
		logger.debug("update {}, affected row={}", entityClass.getSimpleName(), size);
		bean = (CRUDBean) converter.entity2Bean(bean.getClass(), entity);
		logger.trace("updated {}: {}", bean.getClass().getSimpleName(), bean.toString());

		return bean;
	}

	@Override
	public boolean delete(Class<? extends CRUDBean> beanClass, String id) throws ServiceException,
			RuntimeServiceException {

		Class<? extends Entity> entityClass = MapperRegistry.getEntityClass(beanClass);
		CRUDMapper mapper = registry.get(entityClass);

		int size = mapper.delete(id);
		logger.debug("delete {}, affected row={}", beanClass.getSimpleName(), size);

		return size == 1;
	}

	@Override
	public CRUDBean singleLoad4Cache(String key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, CRUDBean> bulkLoad4Cache(Iterable<? extends String> keys) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
