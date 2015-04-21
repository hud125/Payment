package com.aurfy.haze.dao.infra;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.SettleSummaryEntity;

@Component(AOP_MAPPER.SETTLESUMMARY_MAPPER)
@MapperEntity(value = SettleSummaryEntity.class, CRUDBeanRequired = true)
public interface SettleSummaryMapper extends CRUDMapper {

}
