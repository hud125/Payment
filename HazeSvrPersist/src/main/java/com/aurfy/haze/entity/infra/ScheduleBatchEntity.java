package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.ScheduleBatch;
import com.aurfy.haze.entity.Entity;

@Alias("ScheduleBatchEntity")
public class ScheduleBatchEntity extends ScheduleBatch implements Entity {

}
