package com.aurfy.haze.dao.configuration.channel;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.dao.exceptions.MapperException;
import com.aurfy.haze.entity.configuration.channel.ChannelParameterEntity;

@Component(AOP_MAPPER.CHANNEL_PARAMETER_MAPPER)
@MapperEntity(value = ChannelParameterEntity.class, CRUDBeanRequired = false)
public interface ChannelParameterMapper extends CRUDMapper {
	/**
	 * 根据channelId删除ChannelParameter
	 * 
	 * @param channelId
	 * @return
	 * @throws MapperException
	 */
	public int deleteByChannelId(@Param("channelId") String channelId) throws MapperException;

	/**
	 * 根据configKey和channelId获得唯一特定的对象
	 * 
	 * @param configKey
	 * @param channelId
	 * @return
	 * @throws MapperException
	 */
	public ChannelParameterEntity selectByKeyAndChannelId(@Param("configKey") String configKey,
			@Param("channelId") String channelId) throws MapperException;
}
