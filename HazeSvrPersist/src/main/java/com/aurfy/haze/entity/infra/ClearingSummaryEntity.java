package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;
import com.aurfy.haze.core.model.infra.ClearingSummary;
import com.aurfy.haze.entity.Entity;

@Alias("ClearingSummaryEntity")
public class ClearingSummaryEntity extends ClearingSummary implements Entity {

}
