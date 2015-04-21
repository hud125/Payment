package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.VariableMap;
import com.aurfy.haze.entity.Entity;

@Alias("VariableMapEntity")
public class VariableMapEntity extends VariableMap implements Entity {

}
