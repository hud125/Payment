package com.aurfy.haze.dao.configuration.rate;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.configuration.rate.CalculateRateEntity;

@Component(AOP_MAPPER.CALCULATERATE_MAPPER)
@MapperEntity(value = CalculateRateEntity.class, CRUDBeanRequired = false)
public interface CalculateRateMapper extends CRUDMapper {

}
