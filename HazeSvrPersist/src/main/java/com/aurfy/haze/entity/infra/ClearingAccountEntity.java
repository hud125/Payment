package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.ClearingAccount;
import com.aurfy.haze.entity.Entity;

@Alias("ClearingAccountEntity")
public class ClearingAccountEntity extends ClearingAccount implements Entity {

}
