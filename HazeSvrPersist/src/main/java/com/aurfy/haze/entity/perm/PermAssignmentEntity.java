package com.aurfy.haze.entity.perm;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.perm.PermEntry;
import com.aurfy.haze.core.model.perm.PermValueEnum;
import com.aurfy.haze.entity.Entity;

@Alias("PermAssignmentEntity")
public class PermAssignmentEntity extends TextualIDModel implements Entity {

	private String assigneeId;
	private AssigneeClassifierEnum assigneeClassifier;
	private PermEntry permEntry;
	private PermValueEnum permValue;
	private String comments;
	private String ownerId;

	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

	public AssigneeClassifierEnum getAssigneeClassifier() {
		return assigneeClassifier;
	}

	public void setAssigneeClassifier(AssigneeClassifierEnum assigneeClassifier) {
		this.assigneeClassifier = assigneeClassifier;
	}

	public PermValueEnum getPermValue() {
		return permValue;
	}

	public void setPermValue(PermValueEnum permValue) {
		this.permValue = permValue;
	}

	public PermEntry getPermEntry() {
		return permEntry;
	}

	public void setPermEntry(PermEntry permEntry) {
		this.permEntry = permEntry;
	}

	public String getComments() {
		return comments;
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PermAssignmentEntity [assigneeId=");
		builder.append(assigneeId);
		builder.append(", assigneeClassifier=");
		builder.append(assigneeClassifier);
		builder.append(", permEntry=");
		builder.append(permEntry);
		builder.append(", permValue=");
		builder.append(permValue);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", ownerId=");
		builder.append(ownerId);
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
		result = prime * result + ((assigneeClassifier == null) ? 0 : assigneeClassifier.hashCode());
		result = prime * result + ((assigneeId == null) ? 0 : assigneeId.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((permEntry == null) ? 0 : permEntry.hashCode());
		result = prime * result + ((permValue == null) ? 0 : permValue.hashCode());
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
		PermAssignmentEntity other = (PermAssignmentEntity) obj;
		if (assigneeClassifier != other.assigneeClassifier)
			return false;
		if (assigneeId == null) {
			if (other.assigneeId != null)
				return false;
		} else if (!assigneeId.equals(other.assigneeId))
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
		if (permEntry == null) {
			if (other.permEntry != null)
				return false;
		} else if (!permEntry.equals(other.permEntry))
			return false;
		if (permValue != other.permValue)
			return false;
		return true;
	}

}
