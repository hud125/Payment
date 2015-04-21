package com.aurfy.haze.dao.infra;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.ClearingAccountEntity;

@Component(AOP_MAPPER.CLEARINGACCOUNT_MAPPER)
@MapperEntity(value = ClearingAccountEntity.class, CRUDBeanRequired = true)
public interface ClearingAccountMapper extends CRUDMapper {

}
