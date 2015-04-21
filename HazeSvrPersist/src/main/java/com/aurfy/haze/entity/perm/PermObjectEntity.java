package com.aurfy.haze.entity.perm;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.perm.PermObject;
import com.aurfy.haze.entity.Entity;

@Alias("PermObjectEntity")
public class PermObjectEntity extends PermObject implements Entity {

}
