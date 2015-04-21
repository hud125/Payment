package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.ExchangeRate;
import com.aurfy.haze.entity.Entity;

@Alias("ExchangeRateEntity")
public class ExchangeRateEntity extends ExchangeRate implements Entity {

}

