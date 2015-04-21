package com.aurfy.haze.dao.infra.mer;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.mer.SaleEntity;

@Component(AOP_MAPPER.SALE_MAPPER)
@MapperEntity(value = SaleEntity.class, CRUDBeanRequired = true)
public interface SaleMapper extends CRUDMapper {

}
