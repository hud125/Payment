package com.aurfy.haze.dao.conf;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.ScheduleBatchEntity;

@Component(AOP_MAPPER.SCHEDULE_BATCH_MAPPER)
@MapperEntity(value = ScheduleBatchEntity.class, CRUDBeanRequired = false)
public interface ScheduleBatchMapper extends CRUDMapper{

}
