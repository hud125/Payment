package com.aurfy.haze.dao.infra;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.UserEntity;

@Component(AOP_MAPPER.USER_MAPPER)
@MapperEntity(value = UserEntity.class, CRUDBeanRequired = true)
public interface UserMapper extends CRUDMapper {

	/**
	 * get a user associated with the given user name.
	 * 
	 * @param userName
	 * @return
	 */
	UserEntity selectByName(@Param("userName") String userName);

	/**
	 * select the user with login name and encrypted password.<br/>
	 * it is outer service's responsibility to encrypt the password first (with salt or any other algorithm).
	 * 
	 * @param loginName
	 * @param passwdDigest
	 * @return
	 */
	UserEntity selectUserByNameAndPasswd(@Param("loginName") String loginName,
			@Param("passwdDigest") String passwdDigest);

}
