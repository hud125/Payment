package com.aurfy.haze.entity.configuration.channel;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.channel.ChannelParameter;
import com.aurfy.haze.entity.Entity;

@Alias("ChannelParameterEntity")
public class ChannelParameterEntity extends ChannelParameter implements Entity{

}
