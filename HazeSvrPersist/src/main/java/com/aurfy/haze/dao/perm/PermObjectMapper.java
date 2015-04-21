package com.aurfy.haze.dao.perm;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.perm.PermObjectEntity;

@Component(AOP_MAPPER.PERM_OBJECT_MAPPER)
@MapperEntity(value = PermObjectEntity.class, CRUDBeanRequired = false)
public interface PermObjectMapper extends CRUDMapper {

}
