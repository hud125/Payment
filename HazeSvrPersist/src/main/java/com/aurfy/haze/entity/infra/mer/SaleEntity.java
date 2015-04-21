package com.aurfy.haze.entity.infra.mer;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.mer.Sale;
import com.aurfy.haze.entity.Entity;

@Alias("SaleEntity")
public class SaleEntity extends Sale implements Entity {

}
