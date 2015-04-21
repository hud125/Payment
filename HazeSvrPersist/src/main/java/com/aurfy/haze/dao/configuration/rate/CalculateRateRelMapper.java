package com.aurfy.haze.dao.configuration.rate;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.configuration.rate.CalculateRateRelEntity;

@Component(AOP_MAPPER.CALCULATERATEREL_MAPPER)
@MapperEntity(value = CalculateRateRelEntity.class, CRUDBeanRequired = false)
public interface CalculateRateRelMapper extends CRUDMapper {


	/**
	 * get a calculateRateRelation associated with the given rateConf id.
	 * 
	 * @param calculateRateRel
	 * @return
	 */
	CalculateRateRelEntity selectCalculateRateRelByRateConf(CalculateRateRelEntity calculateRateRel);

	/**
	 * get a calculateRateRelation associated with the given calculateRate id.
	 * 
	 * @param calculateRateRel
	 * @return
	 */
	CalculateRateRelEntity selectCalculateRateRelByCalculateRate(CalculateRateRelEntity calculateRateRel);

	/**
	 * update calculateRateRelation info by the given calculateRate id.
	 * 
	 * @param calculateRateRel
	 * @return
	 */
	int updateCalculateRateRelByCalculateRate(CalculateRateRelEntity calculateRateRel);

	/**
	 * delete calculateRateRelation associated with the given calculateRate id and rateConf id.
	 * 
	 * @param calculateRateRel
	 * @return
	 */
	int deleteCalculateRateRelByRateConfAndCalculateRate(CalculateRateRelEntity calculateRateRel);

}
