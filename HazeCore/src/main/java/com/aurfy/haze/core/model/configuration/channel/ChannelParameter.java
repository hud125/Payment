package com.aurfy.haze.core.model.configuration.channel;

import com.aurfy.haze.core.model.TextualIDModel;

public class ChannelParameter extends TextualIDModel {

	private String channelId;
	private String configKey;
	private String configValue;

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
		result = prime * result + ((configKey == null) ? 0 : configKey.hashCode());
		result = prime * result + ((configValue == null) ? 0 : configValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChannelParameter other = (ChannelParameter) obj;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		if (configKey == null) {
			if (other.configKey != null)
				return false;
		} else if (!configKey.equals(other.configKey))
			return false;
		if (configValue == null) {
			if (other.configValue != null)
				return false;
		} else if (!configValue.equals(other.configValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChannelParameter [channelId=" + channelId + ", configKey=" + configKey + ", configValue=" + configValue
				+ ", getID()=" + getID() + "]";
	}
}
