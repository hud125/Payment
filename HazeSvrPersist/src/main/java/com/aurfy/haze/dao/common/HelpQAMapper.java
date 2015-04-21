package com.aurfy.haze.dao.common;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.common.HelpQAEntity;

@Component(AOP_MAPPER.HELPQA_MAPPER)
@MapperEntity(value = HelpQAEntity.class, CRUDBeanRequired = true)
public interface HelpQAMapper extends CRUDMapper {

}
