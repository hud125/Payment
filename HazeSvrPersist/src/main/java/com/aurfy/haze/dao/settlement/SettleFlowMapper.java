package com.aurfy.haze.dao.settlement;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.settlement.SettleFlowEntity;

@Component(AOP_MAPPER.SETTLEFLOWMAPPER)
@MapperEntity(value = SettleFlowEntity.class, CRUDBeanRequired = false)
public interface SettleFlowMapper extends CRUDMapper {

}
