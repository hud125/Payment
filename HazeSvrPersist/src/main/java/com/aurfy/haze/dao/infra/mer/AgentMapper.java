package com.aurfy.haze.dao.infra.mer;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.mer.AgentEntity;

@Component(AOP_MAPPER.AGENT_MAPPER)
@MapperEntity(value = AgentEntity.class, CRUDBeanRequired = true)
public interface AgentMapper extends CRUDMapper {

}
