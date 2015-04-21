package com.aurfy.haze.dao.infra;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.SettleAccountEntity;

@Component(AOP_MAPPER.SETTLEACCOUNT_MAPPER)
@MapperEntity(value = SettleAccountEntity.class, CRUDBeanRequired = true)
public interface SettleAccountMapper extends CRUDMapper {

}
