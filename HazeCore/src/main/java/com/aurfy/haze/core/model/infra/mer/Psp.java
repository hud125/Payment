package com.aurfy.haze.core.model.infra.mer;

import com.aurfy.haze.core.model.TextualIDModel;

public class Psp extends TextualIDModel {
	private String userId;
	private String rateConfigId;
	private String salesId;
	private String agentId;
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

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Psp [userId=");
		builder.append(userId);
		builder.append(", rateConfigId=");
		builder.append(rateConfigId);
		builder.append(", salesId=");
		builder.append(salesId);
		builder.append(", agentId=");
		builder.append(agentId);
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
		result = prime * result + ((agentId == null) ? 0 : agentId.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((rateConfigId == null) ? 0 : rateConfigId.hashCode());
		result = prime * result + ((salesId == null) ? 0 : salesId.hashCode());
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
		Psp other = (Psp) obj;
		if (agentId == null) {
			if (other.agentId != null)
				return false;
		} else if (!agentId.equals(other.agentId))
			return false;
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
		if (salesId == null) {
			if (other.salesId != null)
				return false;
		} else if (!salesId.equals(other.salesId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
