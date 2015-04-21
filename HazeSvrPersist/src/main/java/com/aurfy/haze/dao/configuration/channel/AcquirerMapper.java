package com.aurfy.haze.dao.configuration.channel;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.configuration.channel.AcquirerEntity;

@Component(AOP_MAPPER.ACQUIRER_MAPPER)
@MapperEntity(value = AcquirerEntity.class, CRUDBeanRequired = true)
public interface AcquirerMapper extends CRUDMapper {

}
