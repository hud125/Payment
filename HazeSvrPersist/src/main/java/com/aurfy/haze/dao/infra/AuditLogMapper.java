package com.aurfy.haze.dao.infra;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.AuditLogEntity;

@Component(AOP_MAPPER.AUDIT_LOG_MAPPER)
@MapperEntity(value = AuditLogEntity.class, CRUDBeanRequired = true)
public interface AuditLogMapper extends CRUDMapper {

}
