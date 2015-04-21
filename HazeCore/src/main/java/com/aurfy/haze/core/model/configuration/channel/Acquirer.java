package com.aurfy.haze.core.model.configuration.channel;

import com.aurfy.haze.core.model.TextualIDModel;

/**
 * 收单银行或机构列表
 * 
 * @author rocket
 *
 */
public class Acquirer extends TextualIDModel {

	private String acquirerName;
	private String comments;
	private String ownerId;

	/**
	 * 为收单银行时即银行名称，为收单机构时为机构名称
	 * 
	 * @return
	 */
	public String getComments() {
		return comments;
	}

	public String getAcquirerName() {
		return acquirerName;
	}

	public void setAcquirerName(String acquirerName) {
		this.acquirerName = acquirerName;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((acquirerName == null) ? 0 : acquirerName.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
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
		Acquirer other = (Acquirer) obj;
		if (acquirerName == null) {
			if (other.acquirerName != null)
				return false;
		} else if (!acquirerName.equals(other.acquirerName))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Acquirer [acquirerName=" + acquirerName + ", comments=" + comments + ", ownerId=" + ownerId
				+ ", getID()=" + getID() + ", getCreateDate()=" + getCreateDate() + ", getUpdateDate()="
				+ getUpdateDate() + "]";
	}

}
