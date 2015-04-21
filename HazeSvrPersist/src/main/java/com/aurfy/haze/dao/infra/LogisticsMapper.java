package com.aurfy.haze.dao.infra;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.LogisticsEntity;

@Component(AOP_MAPPER.LOGISTICS_MAPPER)
@MapperEntity(value = LogisticsEntity.class, CRUDBeanRequired = true)
public interface LogisticsMapper extends CRUDMapper {

}
