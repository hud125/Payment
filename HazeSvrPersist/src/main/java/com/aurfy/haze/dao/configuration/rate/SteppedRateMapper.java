package com.aurfy.haze.dao.configuration.rate;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.configuration.rate.SteppedRateEntity;

@Component(AOP_MAPPER.STEPPEDRATE_MAPPER)
@MapperEntity(value = SteppedRateEntity.class, CRUDBeanRequired = false)
public interface SteppedRateMapper extends CRUDMapper {

}
