package com.aurfy.haze.service.impl.configuration.channel;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.MerchantKey;
import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.dao.configuration.channel.AcquirerMapper;
import com.aurfy.haze.dao.configuration.channel.ChannelMapper;
import com.aurfy.haze.dao.configuration.channel.ChannelParameterMapper;
import com.aurfy.haze.dao.configuration.channel.ChannelProviderMapper;
import com.aurfy.haze.entity.configuration.channel.AcquirerEntity;
import com.aurfy.haze.entity.configuration.channel.ChannelEntity;
import com.aurfy.haze.entity.configuration.channel.ChannelParameterEntity;
import com.aurfy.haze.entity.configuration.channel.ChannelProviderEntity;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.configuration.channel.ChannelService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.bean.configuration.channel.ChannelParameterBean;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.exceptions.ObjectNotFoundException;
import com.aurfy.haze.service.exceptions.ParameterValidationException;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.BaseHazeService;
import com.aurfy.haze.service.impl.converter.Converter;
import com.aurfy.haze.service.impl.converter.ConverterHelper;
import com.aurfy.haze.service.impl.helper.BeanBuilder;
import com.aurfy.haze.utils.SecurityUtils;
import com.aurfy.haze.utils.StringUtils;

@Service(AOP_NAME.CHANNEL_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ChannelServiceImpl extends BaseHazeService implements ChannelService {

	@Autowired
	private ChannelMapper channelMapper;
	@Autowired
	private ChannelParameterMapper channelParameterMapper;
	@Autowired
	private AcquirerMapper acuqirerMapper;
	@Autowired
	private ChannelProviderMapper channelProviderMapper;
	@Autowired
	private CRUDService crudService;

	private static final Logger logger = LoggerFactory.getLogger(ChannelServiceImpl.class);

	@Override
	public Set<Currency> getSupportedCurrencies(MerchantKey merchantKey) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChannelBean create(ChannelBean bean) throws ServiceException, RuntimeServiceException {
		try {
			ChannelBean channelBean = (ChannelBean) bean;
			Converter converter = ConverterHelper.getConverter(bean.getClass());
			ChannelEntity channelEntity = (ChannelEntity) converter.bean2Entity(ChannelEntity.class, bean);
			if (StringUtils.isEmpty(channelEntity.getID())) {
				channelEntity.setID(SecurityUtils.UUID());
			}

			AcquirerEntity acquirer = acuqirerMapper.selectOne(channelEntity.getAcquirerId());
			if (acquirer == null) {
				throw new ObjectNotFoundException("can not find acquirer with id " + channelEntity.getAcquirerId());
			}
			channelEntity.setAcquirerName(acquirer.getAcquirerName());
			ChannelProviderEntity channelProviderEntity = channelProviderMapper.selectOne(channelEntity
					.getChannelProviderId());
			if (channelProviderEntity == null) {
				throw new ObjectNotFoundException("can not find channelProvider with id "
						+ channelEntity.getChannelProviderId());
			}
			channelEntity.setChannelProviderName(channelProviderEntity.getProviderName());
			channelEntity.setProviderClassifier(channelProviderEntity.getProviderClassifier());

			int size = channelMapper.insert(channelEntity);
			logger.debug("create new {}, affected row={}", ChannelEntity.class.getSimpleName(), size);
			List<ChannelParameterEntity> params = channelEntity.getChannelParams();
			if (CollectionUtils.isNotEmpty(params)) {
				for (ChannelParameterEntity param : params) {
					if (StringUtils.isEmpty(param.getID())) {
						param.setID(SecurityUtils.UUID());
					}
					size = channelParameterMapper.insert(param);
					logger.debug("create new {}, affected row={}", ChannelParameterEntity.class.getSimpleName(), size);
				}
			}

			return channelBean;
		} catch (RuntimeException e) {
			logger.error("create new {} faild", ChannelEntity.class.getSimpleName(), e);
			throw new RuntimeServiceException("create" + ChannelEntity.class.getSimpleName() + "failed", e);
		}
	}

	@Override
	public ChannelBean retrieve(String channlId) throws ServiceException, RuntimeServiceException {
		return retrieve(channlId, false);
	}

	@Override
	public ChannelBean retrieve(String channlId, boolean includeParams) throws ServiceException,
			RuntimeServiceException {
		try {
			ChannelEntity entity = null;
			if (includeParams) {
				entity = channelMapper.selectOneWithParams(channlId);
			} else {
				entity = channelMapper.selectOne(channlId);
			}
			if (entity == null) {
				return null;
			}
			Converter converter = ConverterHelper.getConverter(entity.getClass());
			return (ChannelBean) converter.entity2Bean(ChannelBean.class, entity);
		} catch (RuntimeException e) {
			logger.error("retrieve new {} faild", ChannelEntity.class.getSimpleName(), e);
			throw new RuntimeServiceException("find" + ChannelEntity.class.getSimpleName() + "failed with", e);
		}
	}

	@Override
	public ChannelBean update(ChannelBean bean) throws ServiceException, RuntimeServiceException {
		try {
			Converter converter = ConverterHelper.getConverter(bean.getClass());
			ChannelEntity channelEntity = (ChannelEntity) converter.bean2Entity(ChannelEntity.class, bean);
			if (StringUtils.isEmpty(channelEntity.getID())) {
				logger.error("update {} faild with no id", ChannelEntity.class.getSimpleName());
				throw new RuntimeServiceException("create" + ChannelEntity.class.getSimpleName() + "failed with no id");
			}
			ChannelEntity oldChannelEntity = channelMapper.selectOne(channelEntity.getID());
			if (oldChannelEntity == null) {
				logger.error("update {} faild with id = {}", ChannelEntity.class.getSimpleName(), channelEntity.getID());
				throw new ObjectNotFoundException("create" + ChannelEntity.class.getSimpleName() + "failed with id = "
						+ channelEntity.getID());
			}

			int size = channelMapper.update(channelEntity);
			logger.debug("update {}, affected row={}", ChannelEntity.class.getSimpleName(), size);

			ChannelEntity updateChannelEntity = channelMapper.selectOne(channelEntity.getID());
			ChannelBean updateChannelBean = null;
			if (updateChannelEntity != null) {
				updateChannelBean = (ChannelBean) converter.entity2Bean(ChannelBean.class, updateChannelEntity);
			}

			return updateChannelBean;
		} catch (RuntimeException e) {
			logger.error("update {} faild", ChannelEntity.class.getSimpleName(), e);
			throw new RuntimeServiceException("update" + ChannelEntity.class.getSimpleName() + "failed", e);
		}
	}

	@Override
	public void delete(String channelId) throws ServiceException, RuntimeServiceException {
		try {
			if (StringUtils.isEmpty(channelId)) {
				logger.error("delete {} faild with no id", ChannelEntity.class.getSimpleName());
				throw new ParameterValidationException("create" + ChannelEntity.class.getSimpleName()
						+ "failed with no id");
			}
			ChannelEntity oldChannelEntity = channelMapper.selectOne(channelId);
			if (oldChannelEntity == null) {
				logger.error("delete {} faild with id = {}", ChannelEntity.class.getSimpleName(), channelId);
				throw new ObjectNotFoundException("create" + ChannelEntity.class.getSimpleName() + "failed with id = "
						+ channelId);
			}

			int size = channelMapper.delete(channelId);
			logger.debug("delete {}, affected row={}", ChannelEntity.class.getSimpleName(), size);
			size = channelParameterMapper.deleteByChannelId(channelId);
			logger.debug("delete {}, affected row={}", ChannelParameterEntity.class.getSimpleName(), size);

		} catch (RuntimeException e) {
			logger.error("delete {} faild", ChannelEntity.class.getSimpleName(), e);
			throw new RuntimeServiceException("delete" + ChannelEntity.class.getSimpleName() + "failed", e);
		}
	}

	@Override
	public ChannelBean updateParams(String channelId, List<ChannelParameterBean> params) throws ServiceException,
			RuntimeServiceException {
		try {
			if (CollectionUtils.isEmpty(params)) {
				return null;
			}
			int size = channelParameterMapper.deleteByChannelId(channelId);
			logger.debug("delete {}, affected row={}", ChannelParameterEntity.class.getSimpleName(), size);
			Converter converter = ConverterHelper.getConverter(ChannelParameterBean.class);
			for (ChannelParameterBean param : params) {
				param.setID(SecurityUtils.UUID());
				size = channelParameterMapper.insert(converter.bean2Entity(ChannelParameterEntity.class, param));
				logger.debug("create new {}, affected row={}", ChannelParameterEntity.class.getSimpleName(), size);
			}
		} catch (RuntimeException e) {
			logger.error("update {} faild", ChannelParameterEntity.class.getSimpleName(), e);
			throw new RuntimeServiceException("update" + ChannelParameterEntity.class.getSimpleName() + "failed", e);
		}

		return null;
	}

	@Override
	public Pagination<ChannelBean> retrieve(int pageNum, int pageSize) throws ServiceException, RuntimeServiceException {
		Pagination<CRUDBean> pagination = crudService.retrieve(ChannelBean.class, pageNum, pageSize);
		return BeanBuilder.convertBean(ChannelBean.class, pagination);
	}

	@Override
	public List<ChannelBean> retrieveAll() throws ServiceException, RuntimeServiceException {
		List<CRUDBean> list = crudService.retrieveAll(ChannelBean.class);
		return BeanBuilder.convertBean(ChannelBean.class, list);
	}
}
