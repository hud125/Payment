package com.aurfy.haze.entity.configuration.channel;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.channel.Acquirer;
import com.aurfy.haze.entity.Entity;

@Alias("AcquirerEntity")
public class AcquirerEntity extends Acquirer implements Entity {

}
