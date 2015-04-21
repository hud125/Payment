package com.aurfy.haze.entity.settlement;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.settlement.SettleFlow;
import com.aurfy.haze.entity.Entity;

@Alias("SettleFlowEntity")
public class SettleFlowEntity extends SettleFlow implements Entity {

}
