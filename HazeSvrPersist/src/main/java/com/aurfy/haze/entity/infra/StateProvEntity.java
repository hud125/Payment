package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.StateProv;
import com.aurfy.haze.entity.Entity;

@Alias("StateProvEntity")
public class StateProvEntity extends StateProv implements Entity {

}
