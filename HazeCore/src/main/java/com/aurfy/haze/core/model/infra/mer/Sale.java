package com.aurfy.haze.core.model.infra.mer;

import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.configuration.rate.RateConfig;

public class Sale extends TextualIDModel {

	private String userId;
	private String rateConfigId;
	private String comments;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRateConfigId() {
		return rateConfigId;
	}

	public void setRateConfigId(String rateConfigId) {
		this.rateConfigId = rateConfigId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sale [userId=");
		builder.append(userId);
		builder.append(", rateConfigId=");
		builder.append(rateConfigId);
		builder.append(", comments=");
		builder.append(comments);
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
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((rateConfigId == null) ? 0 : rateConfigId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Sale other = (Sale) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (rateConfigId == null) {
			if (other.rateConfigId != null)
				return false;
		} else if (!rateConfigId.equals(other.rateConfigId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
