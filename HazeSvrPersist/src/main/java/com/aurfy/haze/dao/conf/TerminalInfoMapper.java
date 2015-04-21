package com.aurfy.haze.dao.conf;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.TerminalInfoEntity;

@Component(AOP_MAPPER.TERMINAL_INFO_MAPPER)
@MapperEntity(value = TerminalInfoEntity.class, CRUDBeanRequired = false)
public interface TerminalInfoMapper extends CRUDMapper {

}
