package com.aurfy.haze.dao.infra;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.CountryEntity;

@Component(AOP_MAPPER.COUNTRY_MAPPER)
@MapperEntity(value = CountryEntity.class, CRUDBeanRequired = true)
public interface CountryMapper extends CRUDMapper {

}
