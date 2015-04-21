package com.aurfy.haze.core.model.perm;

import java.util.List;

public class ViewScope {

	private RowScope rowScope;
	private List<ColumnScope> columnScopes;

	public ViewScope() {
	}

	public RowScope getRowScope() {
		return rowScope;
	}

	public void setRowScope(RowScope rowScope) {
		this.rowScope = rowScope;
	}

	public List<ColumnScope> getColumnScopes() {
		return columnScopes;
	}

	public void setColumnScopes(List<ColumnScope> columnScopes) {
		this.columnScopes = columnScopes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ViewScope [rowScope=");
		builder.append(rowScope);
		builder.append(", columnScopes=");
		builder.append(columnScopes);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((columnScopes == null) ? 0 : columnScopes.hashCode());
		result = prime * result + ((rowScope == null) ? 0 : rowScope.hashCode());
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
		ViewScope other = (ViewScope) obj;
		if (columnScopes == null) {
			if (other.columnScopes != null)
				return false;
		} else if (!columnScopes.equals(other.columnScopes))
			return false;
		if (rowScope == null) {
			if (other.rowScope != null)
				return false;
		} else if (!rowScope.equals(other.rowScope))
			return false;
		return true;
	}

}
