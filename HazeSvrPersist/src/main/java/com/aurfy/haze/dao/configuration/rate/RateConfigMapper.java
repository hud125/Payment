package com.aurfy.haze.dao.configuration.rate;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.configuration.rate.RateConfigEntity;

@Component(AOP_MAPPER.RATE_CONFIG_MAPPER)
@MapperEntity(value = RateConfigEntity.class, CRUDBeanRequired = false)
public interface RateConfigMapper extends CRUDMapper {

	public RateConfigEntity selectOneWithParams(RateConfigEntity rateConfig);

	public List<RateConfigEntity> selectByWithParams(RateConfigEntity rateConfig);
	
}
