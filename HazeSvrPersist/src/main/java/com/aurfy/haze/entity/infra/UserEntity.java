package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.User;
import com.aurfy.haze.entity.Entity;

@Alias("UserEntity")
public class UserEntity extends User implements Entity {

}
