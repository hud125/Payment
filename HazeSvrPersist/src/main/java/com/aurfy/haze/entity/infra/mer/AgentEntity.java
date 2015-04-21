package com.aurfy.haze.entity.infra.mer;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.mer.Agent;
import com.aurfy.haze.entity.Entity;

@Alias("AgentEntity")
public class AgentEntity extends Agent implements Entity {

}
