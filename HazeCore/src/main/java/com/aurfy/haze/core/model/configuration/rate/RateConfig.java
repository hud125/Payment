package com.aurfy.haze.core.model.configuration.rate;

import com.aurfy.haze.core.model.TextualIDModel;

/**
 * 扣率配置表：含常规及阶梯扣率
 * 
 * @author rocket
 *
 */
public class RateConfig extends TextualIDModel {

	private String targetId;
	private RateRoleClassifierEnum rateRoleClassifier;
	private String comments;

	/**
	 * 商户/销售/代理商/PSP的ID
	 * @return
	 */
	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public RateRoleClassifierEnum getRateRoleClassifier() {
		return rateRoleClassifier;
	}

	public void setRateRoleClassifier(RateRoleClassifierEnum rateRoleClassifier) {
		this.rateRoleClassifier = rateRoleClassifier;
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
		builder.append("RateConfiguration [targetId=");
		builder.append(targetId);
		builder.append(", rateRoleClassifier=");
		builder.append(rateRoleClassifier);
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
		result = prime * result + ((rateRoleClassifier == null) ? 0 : rateRoleClassifier.hashCode());
		result = prime * result + ((targetId == null) ? 0 : targetId.hashCode());
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
		RateConfig other = (RateConfig) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (rateRoleClassifier != other.rateRoleClassifier)
			return false;
		if (targetId == null) {
			if (other.targetId != null)
				return false;
		} else if (!targetId.equals(other.targetId))
			return false;
		return true;
	}

}
