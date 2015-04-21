package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.Country;
import com.aurfy.haze.entity.Entity;

@Alias("CountryEntity")
public class CountryEntity extends Country implements Entity {

}
