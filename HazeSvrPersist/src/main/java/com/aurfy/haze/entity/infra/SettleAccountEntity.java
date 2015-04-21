package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.SettleAccount;
import com.aurfy.haze.entity.Entity;

@Alias("SettleAccountEntity")
public class SettleAccountEntity extends SettleAccount implements Entity {

}
