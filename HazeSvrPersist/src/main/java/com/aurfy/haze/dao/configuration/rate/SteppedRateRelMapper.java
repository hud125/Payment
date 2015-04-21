package com.aurfy.haze.dao.configuration.rate;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.configuration.rate.SteppedRateRelEntity;

@Component(AOP_MAPPER.STEPPEDRATEREL_MAPPER)
@MapperEntity(value = SteppedRateRelEntity.class, CRUDBeanRequired = false)
public interface SteppedRateRelMapper extends CRUDMapper {

	/**
	 * get a steppedRateRelation associated with the given rateConf id.
	 * 
	 * @param steppedRateRel
	 * @return
	 */
	SteppedRateRelEntity selectSteppedRateRelByRateConf(SteppedRateRelEntity steppedRateRel);

	/**
	 * get a steppedRateRelation associated with the given steppedRate id.
	 * 
	 * @param steppedRateRel
	 * @return
	 */
	SteppedRateRelEntity selectSteppedRateRelBySteppedRate(SteppedRateRelEntity steppedRateRel);

	/**
	 * update steppedRateRelation info by the given steppedRate id.
	 * 
	 * @param steppedRateRel
	 * @return
	 */
	int updateSteppedRateRelBySteppedRate(SteppedRateRelEntity steppedRateRel);

	/**
	 * delete steppedRateRelation associated with the given steppedRate id and rateConf id.
	 * 
	 * @param steppedRateRel
	 * @return
	 */
	int deleteSteppedRateRelByRateConfAndSteppedRate(SteppedRateRelEntity steppedRateRel);
}
