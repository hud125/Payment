package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.tools.VariableMapClassifierEnum;

public class VariableMap extends TextualIDModel {
	private VariableMapClassifierEnum classifer;
	private String keyName;
	private String mapValue;
	
	public VariableMapClassifierEnum getClassifer() {
		return classifer;
	}
	public void setClassifer(VariableMapClassifierEnum classifer) {
		this.classifer = classifer;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public String getMapValue() {
		return mapValue;
	}
	public void setMapValue(String mapValue) {
		this.mapValue = mapValue;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("VariableMap [classifer=");
		builder.append(classifer);
		builder.append(", keyName=");
		builder.append(keyName);
		builder.append(", mapValue=");
		builder.append(mapValue);
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
		result = prime * result
				+ ((classifer == null) ? 0 : classifer.hashCode());
		result = prime * result + ((keyName == null) ? 0 : keyName.hashCode());
		result = prime * result
				+ ((mapValue == null) ? 0 : mapValue.hashCode());
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
		VariableMap other = (VariableMap) obj;
		if (classifer != other.classifer)
			return false;
		if (keyName == null) {
			if (other.keyName != null)
				return false;
		} else if (!keyName.equals(other.keyName))
			return false;
		if (mapValue == null) {
			if (other.mapValue != null)
				return false;
		} else if (!mapValue.equals(other.mapValue))
			return false;
		return true;
	}
	
}
