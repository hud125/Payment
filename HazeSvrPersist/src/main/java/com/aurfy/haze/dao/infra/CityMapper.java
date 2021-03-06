package com.aurfy.haze.dao.infra;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.CityEntity;

@Component(AOP_MAPPER.CITY_MAPPER)
@MapperEntity(value = CityEntity.class, CRUDBeanRequired = true)
public interface CityMapper extends CRUDMapper {

}
