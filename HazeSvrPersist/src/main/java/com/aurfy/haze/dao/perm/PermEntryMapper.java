package com.aurfy.haze.dao.perm;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.perm.PermEntryEntity;

@Component(AOP_MAPPER.PERM_ENTRY_MAPPER)
@MapperEntity(value = PermEntryEntity.class, CRUDBeanRequired = false)
public interface PermEntryMapper extends CRUDMapper {

	/**
	 * get permEntrys associated with the given permEntry name.
	 * 
	 * @param name
	 * @return
	 */
	List<PermEntryEntity> selectPermEntryByName(@Param("name") String name);

}
