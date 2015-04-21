package com.aurfy.haze.dao.infra;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.ClearingSummaryEntity;

@Component(AOP_MAPPER.CLEARINGSUMMARY_MAPPER)
@MapperEntity(value = ClearingSummaryEntity.class, CRUDBeanRequired = true)
public interface ClearingSummaryMapper extends CRUDMapper {

}
