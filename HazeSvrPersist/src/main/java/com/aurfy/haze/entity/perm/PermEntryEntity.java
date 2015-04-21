package com.aurfy.haze.entity.perm;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.perm.PermEntry;
import com.aurfy.haze.entity.Entity;

@Alias("PermEntryEntity")
public class PermEntryEntity extends PermEntry implements Entity {

}
