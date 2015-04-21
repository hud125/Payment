package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.WithdrawlAccount;
import com.aurfy.haze.entity.Entity;

@Alias("WithdrawlAccountEntity")
public class WithdrawlAccountEntity extends WithdrawlAccount implements Entity {

}