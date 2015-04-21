package com.aurfy.haze.dao.perm;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.perm.ColumnScopeEntity;

@Component(AOP_MAPPER.COLUMN_SCOPE_MAPPER)
@MapperEntity(value = ColumnScopeEntity.class, CRUDBeanRequired = false)
public interface ColumnScopeMapper extends CRUDMapper {

}
