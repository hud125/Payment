package com.aurfy.haze.dao.settlement;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.settlement.MerSettleConfigEntity;

@Component(AOP_MAPPER.MERSETTLE_CONFIG_MAPPER)
@MapperEntity(value = MerSettleConfigEntity.class, CRUDBeanRequired = false)
public interface MerSettleConfigMapper extends CRUDMapper {

}
