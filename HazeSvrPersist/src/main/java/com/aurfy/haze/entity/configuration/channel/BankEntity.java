package com.aurfy.haze.entity.configuration.channel;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.channel.Bank;
import com.aurfy.haze.entity.Entity;

@Alias("BankEntity")
public class BankEntity extends Bank implements Entity{

}
