package com.aurfy.haze.dao.infra.mer;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.mer.PspEntity;

@Component(AOP_MAPPER.PSP_MAPPER)
@MapperEntity(value = PspEntity.class, CRUDBeanRequired = true)
public interface PspMapper extends CRUDMapper {

}
