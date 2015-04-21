package com.aurfy.haze.dao.infra;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.PasswordRecoveryEntity;

@Component(AOP_MAPPER.PASSWORDRECOVERY_MAPPER)
@MapperEntity(value = PasswordRecoveryEntity.class, CRUDBeanRequired = true)
public interface PasswordRecoveryMapper extends CRUDMapper{

	public PasswordRecoveryEntity selectByAuthkey(@Param("authKey") String authKey);
}
