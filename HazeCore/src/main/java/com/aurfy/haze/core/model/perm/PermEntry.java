package com.aurfy.haze.core.model.perm;

import com.aurfy.haze.core.model.TextualIDModel;

public class PermEntry extends TextualIDModel {

	private String entryKey;
	private String displayName;
	private PermObject object;
	private PermOperation operation;
	private ViewScope viewScope;
	private String comments;

	public String getEntryKey() {
		return entryKey;
	}

	public void setEntryKey(String entryKey) {
		this.entryKey = entryKey;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public PermObject getObject() {
		return object;
	}

	public void setObject(PermObject obj) {
		this.object = obj;
	}

	public PermOperation getOperation() {
		return operation;
	}

	public void setOperation(PermOperation oper) {
		this.operation = oper;
	}

	public ViewScope getViewScope() {
		return viewScope;
	}

	public void setViewScope(ViewScope viewScope) {
		this.viewScope = viewScope;
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
		builder.append("PermEntry [entryKey=");
		builder.append(entryKey);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", object=");
		builder.append(object);
		builder.append(", operation=");
		builder.append(operation);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", viewScope=");
		builder.append(viewScope);
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
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((entryKey == null) ? 0 : entryKey.hashCode());
		result = prime * result + ((object == null) ? 0 : object.hashCode());
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		result = prime * result + ((viewScope == null) ? 0 : viewScope.hashCode());
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
		PermEntry other = (PermEntry) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (entryKey == null) {
			if (other.entryKey != null)
				return false;
		} else if (!entryKey.equals(other.entryKey))
			return false;
		if (object == null) {
			if (other.object != null)
				return false;
		} else if (!object.equals(other.object))
			return false;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		if (viewScope == null) {
			if (other.viewScope != null)
				return false;
		} else if (!viewScope.equals(other.viewScope))
			return false;
		return true;
	}

}
