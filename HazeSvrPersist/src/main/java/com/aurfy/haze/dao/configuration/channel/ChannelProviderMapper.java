package com.aurfy.haze.dao.configuration.channel;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.configuration.channel.ChannelProviderEntity;

@Component(AOP_MAPPER.CHANNEL_PROVIDER_MAPPER)
@MapperEntity(value = ChannelProviderEntity.class, CRUDBeanRequired = true)
public interface ChannelProviderMapper extends CRUDMapper {

}
