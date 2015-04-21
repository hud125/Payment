package com.aurfy.haze.entity.perm;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.perm.PermRole;
import com.aurfy.haze.entity.Entity;

@Alias("PermRoleEntity")
public class PermRoleEntity extends PermRole implements Entity {

}
