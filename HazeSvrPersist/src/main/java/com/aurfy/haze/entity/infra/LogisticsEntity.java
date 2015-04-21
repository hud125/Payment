package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.Logistics;
import com.aurfy.haze.entity.Entity;

@Alias("LogisticsEntity")
public class LogisticsEntity extends Logistics implements Entity{

	
}
