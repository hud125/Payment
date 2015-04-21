package com.aurfy.haze.dao.perm;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.perm.PermRelationEntity;

@Component(AOP_MAPPER.PERM_RELATION_MAPPER)
@MapperEntity(value = PermRelationEntity.class, CRUDBeanRequired = false)
public interface PermRelationMapper extends CRUDMapper {

	/**
	 * get a permRelation associated with the given user id.
	 * 
	 * @param permRelation
	 * @return
	 */
	PermRelationEntity selectPermRelationByUser(PermRelationEntity permRelation);

	/**
	 * get a permRelation associated with the given role id.
	 * 
	 * @param permRelation
	 * @return
	 */
	PermRelationEntity selectPermRelationByRole(PermRelationEntity permRelation);

	/**
	 * update permRelation info by the given role id.
	 * 
	 * @param permRelation
	 * @return
	 */
	int updatePermRelationByUser(PermRelationEntity permRelation);

	/**
	 * delete permRelation associated with the given user id and role id.
	 * 
	 * @param permRelation
	 * @return
	 */
	int deletePermRelationByUserAndRole(PermRelationEntity permRelation);

}
