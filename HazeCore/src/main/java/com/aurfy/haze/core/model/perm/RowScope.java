package com.aurfy.haze.core.model.perm;

import com.aurfy.haze.core.model.TextualIDModel;

public class RowScope extends TextualIDModel {

	private String entityName;
	private String tableClause;
	private String whereClause;
	private String comments;

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getTableClause() {
		return tableClause;
	}

	public void setTableClause(String tableClause) {
		this.tableClause = tableClause;
	}

	public String getWhereClause() {
		return whereClause;
	}

	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
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
		builder.append("RowScope [entityName=");
		builder.append(entityName);
		builder.append(", tableClause=");
		builder.append(tableClause);
		builder.append(", whereClause=");
		builder.append(whereClause);
		builder.append(", comments=");
		builder.append(comments);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((entityName == null) ? 0 : entityName.hashCode());
		result = prime * result + ((tableClause == null) ? 0 : tableClause.hashCode());
		result = prime * result + ((whereClause == null) ? 0 : whereClause.hashCode());
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
		RowScope other = (RowScope) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (entityName == null) {
			if (other.entityName != null)
				return false;
		} else if (!entityName.equals(other.entityName))
			return false;
		if (tableClause == null) {
			if (other.tableClause != null)
				return false;
		} else if (!tableClause.equals(other.tableClause))
			return false;
		if (whereClause == null) {
			if (other.whereClause != null)
				return false;
		} else if (!whereClause.equals(other.whereClause))
			return false;
		return true;
	}

}
