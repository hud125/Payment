package com.aurfy.haze.entity.configuration.channel;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.channel.ChannelProvider;
import com.aurfy.haze.entity.Entity;

@Alias("ChannelProviderEntity")
public class ChannelProviderEntity extends ChannelProvider implements Entity{

}
