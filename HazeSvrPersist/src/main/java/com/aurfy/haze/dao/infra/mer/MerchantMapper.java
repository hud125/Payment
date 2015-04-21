package com.aurfy.haze.dao.infra.mer;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.mer.MerchantEntity;

@Component(AOP_MAPPER.MERCHANT_MAPPER)
@MapperEntity(value = MerchantEntity.class, CRUDBeanRequired = true)
public interface MerchantMapper extends CRUDMapper {
	
}
