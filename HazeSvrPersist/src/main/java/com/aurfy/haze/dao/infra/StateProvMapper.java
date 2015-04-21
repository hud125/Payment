package com.aurfy.haze.dao.infra;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.StateProvEntity;

@Component(AOP_MAPPER.STATE_PROV_MAPPER)
@MapperEntity(value = StateProvEntity.class, CRUDBeanRequired = true)
public interface StateProvMapper extends CRUDMapper {

	/**
	 * get state list by the given country
	 * 
	 * @param country_id
	 * @return
	 */
	List<StateProvEntity> listStateProvsByCountry(@Param("country_id") String country_id);

}
