package com.aurfy.haze.dao.perm;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.perm.PermOperationEntity;

@Component(AOP_MAPPER.PERM_OPERATION_MAPPER)
@MapperEntity(value = PermOperationEntity.class, CRUDBeanRequired = false)
public interface PermOperationMapper extends CRUDMapper {

}
