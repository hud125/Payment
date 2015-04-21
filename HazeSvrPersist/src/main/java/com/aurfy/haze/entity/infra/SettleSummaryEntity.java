package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.SettleSummary;
import com.aurfy.haze.entity.Entity;

@Alias("SettleSummaryEntity")
public class SettleSummaryEntity extends SettleSummary implements Entity {
	
	
}
