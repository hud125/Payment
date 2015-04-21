package com.aurfy.haze.entity.configuration.rate;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.rate.SteppedRate;
import com.aurfy.haze.entity.Entity;

@Alias("SteppedRateEntity")
public class SteppedRateEntity extends SteppedRate implements Entity {

}
