package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.City;
import com.aurfy.haze.entity.Entity;

@Alias("CityEntity")
public class CityEntity extends City implements Entity {

}
