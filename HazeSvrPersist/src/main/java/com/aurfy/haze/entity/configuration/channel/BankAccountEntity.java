package com.aurfy.haze.entity.configuration.channel;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.channel.BankAccount;
import com.aurfy.haze.entity.Entity;

@Alias("BankAccountEntity")
public class BankAccountEntity extends BankAccount implements Entity {

}
