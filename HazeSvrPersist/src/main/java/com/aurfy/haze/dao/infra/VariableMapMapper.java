package com.aurfy.haze.dao.infra;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.VariableMapEntity;

@Component(AOP_MAPPER.VARIABLE_MAP_MAPPER)
@MapperEntity(value = VariableMapEntity.class, CRUDBeanRequired = false)
public interface VariableMapMapper extends CRUDMapper {

	/**
	 * get a variableMap associated with the given keyName.
	 * 
	 * @param keyName
	 * @return
	 */
	List<VariableMapEntity> selectVariableMapByKeyName(@Param("keyName") String keyName);

}
