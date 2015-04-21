package com.aurfy.haze.entity.infra.mer;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.mer.Psp;
import com.aurfy.haze.entity.Entity;

@Alias("PspEntity")
public class PspEntity extends Psp implements Entity {

}
