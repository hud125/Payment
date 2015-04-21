package com.aurfy.haze.entity.perm;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.perm.PermOperation;
import com.aurfy.haze.entity.Entity;

@Alias("PermOperationEntity")
public class PermOperationEntity extends PermOperation implements Entity {

}
