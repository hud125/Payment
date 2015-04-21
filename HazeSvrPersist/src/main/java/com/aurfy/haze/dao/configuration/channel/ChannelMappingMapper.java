package com.aurfy.haze.dao.configuration.channel;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.dao.exceptions.MapperException;
import com.aurfy.haze.entity.configuration.channel.ChannelMappingEntity;

@Component(AOP_MAPPER.CHANNEL_MAPPING_MAPPER)
@MapperEntity(value = ChannelMappingEntity.class, CRUDBeanRequired = false)
public interface ChannelMappingMapper extends CRUDMapper {

	public ChannelMappingEntity selectByMerAndChannel(@Param("merId") String merId, @Param("channelId") String channelId)
			throws MapperException;

	public int emptyDefaultChannel(@Param("merId") String merId);

	public int setChannelProperty(@Param("merId") String merId, @Param("channelId") String channelId,
			@Param("defaultChannel") boolean defaultChannel);

	public int deleteByChannel(@Param("channelId") String channelId) throws MapperException;

}
