package com.aurfy.haze.service.impl.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
//import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.dao.configuration.channel.ChannelMapper;
import com.aurfy.haze.dao.configuration.channel.ChannelMappingMapper;
import com.aurfy.haze.dao.infra.VariableMapMapper;
import com.aurfy.haze.dao.infra.mer.MerchantMapper;
import com.aurfy.haze.dao.security.KeyStoreMapper;
import com.aurfy.haze.dao.settlement.MerSettleConfigMapper;
import com.aurfy.haze.entity.configuration.channel.ChannelEntity;
import com.aurfy.haze.entity.configuration.channel.ChannelMappingEntity;
import com.aurfy.haze.entity.infra.VariableMapEntity;
import com.aurfy.haze.entity.infra.mer.MerchantEntity;
import com.aurfy.haze.entity.settlement.MerSettleConfigEntity;
import com.aurfy.haze.service.api.infrastructure.MerchantService;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.bean.infra.VariableMapBean;
import com.aurfy.haze.service.bean.infra.mer.MerchantBean;
import com.aurfy.haze.service.bean.settlement.MerSettleConfigBean;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.BaseHazeService;
import com.aurfy.haze.service.impl.converter.Converter;
import com.aurfy.haze.service.impl.converter.ConverterHelper;
import com.aurfy.haze.service.impl.helper.EntityBuilder;
import com.aurfy.haze.utils.SecurityUtils;

/**
 * 
 * @author hermano
 *
 */
@Service(AOP_NAME.MERCHANT_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MerchantServiceImpl extends BaseHazeService implements MerchantService {

	private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

	@Autowired
	private VariableMapMapper varMapMapper;

	@Autowired
	private MerchantMapper merMapper;

	@Autowired
	private ChannelMapper channelMapper;

	@Autowired
	private ChannelMappingMapper channelMappingMapper;

	@Autowired
	private MerSettleConfigMapper merSettleConfigMapper;

	@Autowired
	private KeyStoreMapper ksMapper;

	public MerchantServiceImpl() {
	}

	// @Override
	// @Transactional
	// public Pagination<MerchantBean> listMerchants(@Validated String name, int pageNum, int pageSize) {
	// try {
	// Pagination<MerchantBean> pagination = null;
	// if (!StringUtils.isNotEmpty(name)) {
	// return pagination;
	// }
	// MerchantEntity merEntity = new MerchantEntity();
	// merEntity.setName("%" + name + "%");
	// int count = merMapper.countBy(merEntity);
	// if (count == 0) {
	// return pagination;
	// }
	// pagination = new Pagination<MerchantBean>(count, pageNum, pageSize);
	// List<MerchantEntity> merEntitys = merMapper.selectBy(merEntity, pagination.getStartRow(),
	// pagination.getPageSize());
	//
	// if (CollectionUtils.isNotEmpty(merEntitys)) {
	// Converter converter = new DefaultConverter();
	// List<MerchantBean> merBeans = new ArrayList<MerchantBean>();
	// for (MerchantEntity entity : merEntitys) {
	// merBeans.add((MerchantBean) converter.entity2Bean(MerchantBean.class, entity));
	// }
	// pagination.setPageData(merBeans);
	// }
	// return pagination;
	// } catch (RuntimeException e) {
	// logger.error("can not find merchants", e);
	// throw new RuntimeServiceException("can not find merchants", e);
	// }
	// }
	//
	// @Override
	// @Transactional
	// public MerchantBean insertMerchant(MerchantBean merBean) {
	// try {
	// Converter converter = new DefaultConverter();
	// MerchantEntity merEntity = (MerchantEntity) converter.bean2Entity(MerchantEntity.class, merBean);
	// int rows = merMapper.insert(merEntity);
	// logger.debug("create new merchant, affected row={}, username={}, id={}", rows, merEntity.getName(),
	// merEntity.getID());
	// return (MerchantBean) converter.entity2Bean(MerchantBean.class, merEntity);
	// } catch (RuntimeException e) {
	// logger.error("create merchant failed", e);
	// throw new RuntimeServiceException("create merchant failed", e);
	// }
	// }

	/**
	 * TODO:1.VariableMapBean作为公共的配置类，不能直接暴露在service层。比如说， 接口定义为insertMerchant(MerchantBean merBean,
	 * MerchantSettleConfig msc)， 再将msc中的内容转成varMapBean，最后call相应的mapper
	 * 2.这里由于数据库已建表mer_settle_conf，即已经将mer的settle配置独立出来， 直接用就行了，没必要再用varMapBean
	 */
	@Override
	@Transactional
	public MerchantBean insertMerchant(MerchantBean merBean, VariableMapBean varMapBean) {
		try {
			MerchantEntity merEntity = EntityBuilder.toMerchantsEntity(merBean);
			int rows = merMapper.insert(merEntity);
			logger.debug("create new merchant, affected row={}, username={}, id={}", rows, merEntity.getName(),
					merEntity.getID());
			Converter converter = ConverterHelper.getConverter(VariableMapEntity.class);
			VariableMapEntity varMapEntity = (VariableMapEntity) converter.bean2Entity(VariableMapEntity.class,
					varMapBean);
			varMapMapper.insert(varMapEntity);
			return (MerchantBean) converter.entity2Bean(MerchantBean.class, merEntity);
		} catch (RuntimeException e) {
			logger.error("create merchant failed", e);
			throw new RuntimeServiceException("create merchant failed", e);
		}
	}

	@Override
	@Transactional
	public boolean addChannelMapping(String merchantId, String channelId, boolean isDefault) throws ServiceException,
			RuntimeServiceException {

		// TODO: validate existence of merchant and channel

		ChannelMappingEntity mapEntity = channelMappingMapper.selectByMerAndChannel(merchantId, channelId);
		// not set
		if (mapEntity == null) {
			// clear current default channel
			if (isDefault) {
				int affectedRow = channelMappingMapper.emptyDefaultChannel(merchantId);
				logger.debug("clear default channel: merId={}, channelId={}, affectedRow={}", merchantId, channelId,
						affectedRow);
			}
			// insert new channel mapping
			mapEntity = new ChannelMappingEntity();
			mapEntity.setID(SecurityUtils.UUID());
			mapEntity.setMerId(merchantId);
			mapEntity.setChannelId(channelId);
			mapEntity.setDefaultChannel(isDefault);
			int affectedRow = channelMappingMapper.insert(mapEntity);
			logger.debug("set channel mapping: merId={}, channelId={}, isDefault={}, affectedRow={}", merchantId,
					channelId, isDefault, affectedRow);
			return affectedRow == 1;
		}
		// already set as is
		else if (mapEntity.isDefaultChannel() == isDefault) {
			return false;
		}
		// set as desired
		else {
			// clear current default channel
			if (isDefault) {
				int affectedRow = channelMappingMapper.emptyDefaultChannel(merchantId);
				logger.debug("clear default channel: merId={}, channelId={}, affectedRow={}", merchantId, channelId,
						affectedRow);
			}
			// set value
			int affectedRow = channelMappingMapper.setChannelProperty(merchantId, channelId, isDefault);
			logger.debug("set channel mapping: merId={}, channelId={}, isDefault={}, affectedRow={}", merchantId,
					channelId, isDefault, affectedRow);
			return affectedRow == 1;
		}
	}

	@Override
	@Transactional
	public ChannelBean getChannel(String merchantId, Currency txnCurrency) throws ServiceException,
			RuntimeServiceException {
		ChannelEntity channelEntity = channelMapper.selectMerChannelWithParms(merchantId, txnCurrency);
		if (channelEntity == null) {
			channelEntity = channelMapper.selectMerDefaultChannelWithParams(merchantId);
		}
		if (channelEntity != null) {
			Converter converter = ConverterHelper.getConverter(ChannelBean.class);
			ChannelBean channelBean = (ChannelBean) converter.entity2Bean(ChannelBean.class, channelEntity);
			return channelBean;
		}
		return null;
	}

	@Override
	@Transactional
	public MerSettleConfigBean getSettleConfig(String merchantId) throws ServiceException, RuntimeServiceException {
		MerSettleConfigEntity merSettleConfigEntity = merSettleConfigMapper.selectOne(merchantId);
		if (merSettleConfigEntity != null) {
			Converter converter = ConverterHelper.getConverter(MerSettleConfigBean.class);
			MerSettleConfigBean configBean = (MerSettleConfigBean) converter.entity2Bean(MerSettleConfigBean.class,
					merSettleConfigEntity);
			return configBean;
		}
		return null;

	}
}
