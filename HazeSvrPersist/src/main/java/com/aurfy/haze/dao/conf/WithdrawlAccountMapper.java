package com.aurfy.haze.dao.conf;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.WithdrawlAccountEntity;

@Component(AOP_MAPPER.WITHDRAWL_ACCOUNT_MAPPER)
@MapperEntity(value = WithdrawlAccountEntity.class, CRUDBeanRequired = false)
public interface WithdrawlAccountMapper extends CRUDMapper {

}
