package com.aurfy.haze.service.bean.perm;

import com.aurfy.haze.core.model.perm.PermValueEnum;

public class PermAssignmentBean {

	private String userId;
	private PermEntryBean permEntryBean;
	private PermValueEnum permValue;
	private String comments;

	public PermAssignmentBean() {
	}
	
	public PermAssignmentBean(String userId, PermEntryBean permEntry, PermValueEnum permValue,String comments) {
		this.userId = userId;
		this.permEntryBean = permEntry;
		this.permValue = permValue;
		this.comments = comments;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public PermEntryBean getPermEntryBean() {
		return permEntryBean;
	}

	public void setPermEntryBean(PermEntryBean permEntryBean) {
		this.permEntryBean = permEntryBean;
	}

	public PermValueEnum getPermValue() {
		return permValue;
	}

	public void setPermValue(PermValueEnum permValue) {
		this.permValue = permValue;
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
		builder.append("PermAssignmentBean [userId=");
		builder.append(userId);
		builder.append(", permEntry=");
		builder.append(permEntryBean);
		builder.append(", permValue=");
		builder.append(permValue);
		builder.append(", comments=");
		builder.append(comments);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((permEntryBean == null) ? 0 : permEntryBean.hashCode());
		result = prime * result + ((permValue == null) ? 0 : permValue.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PermAssignmentBean other = (PermAssignmentBean) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (permEntryBean == null) {
			if (other.permEntryBean != null)
				return false;
		} else if (!permEntryBean.equals(other.permEntryBean))
			return false;
		if (permValue != other.permValue)
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
