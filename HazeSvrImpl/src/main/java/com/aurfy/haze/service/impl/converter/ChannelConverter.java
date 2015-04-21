package com.aurfy.haze.service.impl.converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.entity.Entity;
import com.aurfy.haze.entity.configuration.channel.ChannelEntity;
import com.aurfy.haze.entity.configuration.channel.ChannelParameterEntity;
import com.aurfy.haze.service.bean.Bean;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.bean.configuration.channel.ChannelParameterBean;

class ChannelConverter extends DefaultConverter {

	private static final Logger logger = LoggerFactory.getLogger(ChannelConverter.class);

	@Override
	public ChannelBean entity2Bean(Class<? extends Bean> beanClass, Entity... entities) {
		if (entities.length == 0) {
			return null;
		}
		ChannelEntity channelEntity = (ChannelEntity) entities[0];
		ChannelBean channelBean = (ChannelBean) super.entity2Bean(beanClass, channelEntity);

		List<ChannelParameterEntity> entityParams = channelEntity.getChannelParams();

		List<ChannelParameterBean> beanParams = null;
		if (CollectionUtils.isNotEmpty(entityParams)) {
			beanParams = new ArrayList<ChannelParameterBean>(entityParams.size());
			for (ChannelParameterEntity param : entityParams) {
				beanParams.add((ChannelParameterBean) super.entity2Bean(ChannelParameterBean.class, param));
			}
		}
		channelBean.setChannelParams(beanParams);
		return channelBean;
	}

	@Override
	public ChannelEntity bean2Entity(Class<? extends Entity> entityClass, Bean... beans) {
		if (beans.length == 0) {
			return null;
		}
		ChannelBean channelBean = (ChannelBean) beans[0];
		ChannelEntity channelEntity = (ChannelEntity) super.bean2Entity(entityClass, channelBean);

		List<ChannelParameterBean> beanParams = channelBean.getChannelParams();

		List<ChannelParameterEntity> entityParams = null;
		if (CollectionUtils.isNotEmpty(beanParams)) {
			entityParams = new ArrayList<ChannelParameterEntity>(beanParams.size());
			for (ChannelParameterBean param : beanParams) {
				entityParams.add((ChannelParameterEntity) super.bean2Entity(ChannelParameterEntity.class, param));
			}
		}
		channelEntity.setChannelParams(entityParams);
		return channelEntity;
	}

}
