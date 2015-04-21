package com.aurfy.haze.dao.perm;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.perm.PermRoleEntity;

@Component(AOP_MAPPER.PERM_ROLE_MAPPER)
@MapperEntity(value = PermRoleEntity.class, CRUDBeanRequired = false)
public interface PermRoleMapper extends CRUDMapper {

}
