package com.aurfy.haze.entity.settlement;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.settlement.MerSettleConfig;
import com.aurfy.haze.entity.Entity;

@Alias("MerSettleConfigEntity")
public class MerSettleConfigEntity extends MerSettleConfig implements Entity{

}
