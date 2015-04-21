package com.aurfy.haze.core.model.infra;

import java.util.Date;

import com.aurfy.haze.core.model.TextualIDModel;

public class PasswordRecovery extends TextualIDModel {
private String authKey;
	
	private Date expiryDate;

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((authKey == null) ? 0 : authKey.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
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
		PasswordRecovery other = (PasswordRecovery) obj;
		if (authKey == null) {
			if (other.authKey != null)
				return false;
		} else if (!authKey.equals(other.authKey))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PasswordRecoveryEntity [authKey=");
		builder.append(authKey);
		builder.append(", expiryDate=");
		builder.append(expiryDate);
		builder.append(", getID()=");
		builder.append(getID());
		builder.append(", getCreateDate()=");
		builder.append(getCreateDate());
		builder.append(", getUpdateDate()=");
		builder.append(getUpdateDate());
		builder.append("]");
		return builder.toString();
	}
	
}
