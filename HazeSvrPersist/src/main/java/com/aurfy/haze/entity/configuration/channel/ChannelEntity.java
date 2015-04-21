package com.aurfy.haze.entity.configuration.channel;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.channel.Channel;
import com.aurfy.haze.entity.Entity;

@Alias("ChannelEntity")
public class ChannelEntity extends Channel implements Entity {

	private List<ChannelParameterEntity> channelParams;

	
	public List<ChannelParameterEntity> getChannelParams() {
		return channelParams;
	}

	public void setChannelParams(List<ChannelParameterEntity> channelParams) {
		this.channelParams = channelParams;
	}

}
