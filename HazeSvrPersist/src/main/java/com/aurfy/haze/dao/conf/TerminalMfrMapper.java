package com.aurfy.haze.dao.conf;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.TerminalMfrEntity;

@Component(AOP_MAPPER.TERMINAL_MANUFACTURER_MAPPER)
@MapperEntity(value = TerminalMfrEntity.class, CRUDBeanRequired = true)
public interface TerminalMfrMapper extends CRUDMapper {

}
