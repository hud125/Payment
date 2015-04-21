package com.aurfy.haze.entity.perm;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.perm.ColumnScope;
import com.aurfy.haze.entity.Entity;

@Alias("ColumnScopeEntity")
public class ColumnScopeEntity extends ColumnScope implements Entity {

	private String permEntryId;
	private String permEntryKey;

	public String getPermEntryId() {
		return permEntryId;
	}

	public void setPermEntryId(String permEntryId) {
		this.permEntryId = permEntryId;
	}

	public String getPermEntryKey() {
		return permEntryKey;
	}

	public void setPermEntryKey(String permEntryKey) {
		this.permEntryKey = permEntryKey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ColumnScopeEntity [permEntryId=");
		builder.append(permEntryId);
		builder.append(", permEntryKey=");
		builder.append(permEntryKey);
		builder.append(", getColumnName()=");
		builder.append(getColumnName());
		builder.append(", getPropertyName()=");
		builder.append(getPropertyName());
		builder.append(", getComments()=");
		builder.append(getComments());
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
		result = prime * result + ((permEntryId == null) ? 0 : permEntryId.hashCode());
		result = prime * result + ((permEntryKey == null) ? 0 : permEntryKey.hashCode());
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
		ColumnScopeEntity other = (ColumnScopeEntity) obj;
		if (permEntryId == null) {
			if (other.permEntryId != null)
				return false;
		} else if (!permEntryId.equals(other.permEntryId))
			return false;
		if (permEntryKey == null) {
			if (other.permEntryKey != null)
				return false;
		} else if (!permEntryKey.equals(other.permEntryKey))
			return false;
		return true;
	}

}
