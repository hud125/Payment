package com.aurfy.haze.entity.configuration.channel;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.channel.ChannelMapping;
import com.aurfy.haze.entity.Entity;

@Alias("ChannelMappingEntity")
public class ChannelMappingEntity extends ChannelMapping implements Entity {

}
