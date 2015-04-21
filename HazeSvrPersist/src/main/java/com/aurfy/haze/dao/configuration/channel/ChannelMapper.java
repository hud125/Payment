package com.aurfy.haze.dao.configuration.channel;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.dao.exceptions.MapperException;
import com.aurfy.haze.entity.configuration.channel.ChannelEntity;

@Component(AOP_MAPPER.CHANNEL_MAPPER)
@MapperEntity(value = ChannelEntity.class, CRUDBeanRequired = true)
public interface ChannelMapper extends CRUDMapper {

	public ChannelEntity selectOneWithParams(String id) throws MapperException;

	/**
	 * select channel By merchantId and Currency, channel parameters will be included.
	 * 
	 * @param merchantId
	 * @param txnCurrency
	 * @return
	 */
	public ChannelEntity selectMerChannelWithParms(@Param("merchantId") String merchantId,
			@Param("txnCurrency") Currency txnCurrency);

	/**
	 * select default channel By merchantId, channel parameters will be included.
	 * 
	 * @param merchantId
	 * @return
	 */
	public ChannelEntity selectMerDefaultChannelWithParams(@Param("merchantId") String merchantId);
}
