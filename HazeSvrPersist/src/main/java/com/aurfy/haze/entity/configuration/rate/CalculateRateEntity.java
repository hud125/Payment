package com.aurfy.haze.entity.configuration.rate;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.rate.CalculateRate;
import com.aurfy.haze.entity.Entity;

@Alias("CalculateRateEntity")
public class CalculateRateEntity extends CalculateRate implements Entity {

}
