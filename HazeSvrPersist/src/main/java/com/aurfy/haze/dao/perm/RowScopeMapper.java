package com.aurfy.haze.dao.perm;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.perm.RowScopeEntity;

@Component(AOP_MAPPER.ROE_SCOPE_MAPPER)
@MapperEntity(value = RowScopeEntity.class, CRUDBeanRequired = false)
public interface RowScopeMapper extends CRUDMapper {

}
