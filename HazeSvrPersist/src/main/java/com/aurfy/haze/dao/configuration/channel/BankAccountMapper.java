package com.aurfy.haze.dao.configuration.channel;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.configuration.channel.BankAccountEntity;

@Component(AOP_MAPPER.BANK_ACCOUNT_MAPPER)
@MapperEntity(value = BankAccountEntity.class, CRUDBeanRequired = true)
public interface BankAccountMapper extends CRUDMapper {

}
