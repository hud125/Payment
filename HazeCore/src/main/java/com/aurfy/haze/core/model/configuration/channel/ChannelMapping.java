package com.aurfy.haze.core.model.configuration.channel;

import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.infra.mer.Merchant;

public class ChannelMapping extends TextualIDModel {

	private String merId;
	private String channelId;
	private boolean defaultChannel;

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public boolean isDefaultChannel() {
		return defaultChannel;
	}

	public void setDefaultChannel(boolean defaultChannel) {
		this.defaultChannel = defaultChannel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelMapping [merId=");
		builder.append(merId);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", defaultChannel=");
		builder.append(defaultChannel);
		builder.append(", getID()=");
		builder.append(getID());
		builder.append(", getCreateDate()=");
		builder.append(getCreateDate());
		builder.append(", getUpdateDate()=");
		builder.append(getUpdateDate());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
		result = prime * result + (defaultChannel ? 1231 : 1237);
		result = prime * result + ((merId == null) ? 0 : merId.hashCode());
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
		ChannelMapping other = (ChannelMapping) obj;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		if (defaultChannel != other.defaultChannel)
			return false;
		if (merId == null) {
			if (other.merId != null)
				return false;
		} else if (!merId.equals(other.merId))
			return false;
		return true;
	}

}
