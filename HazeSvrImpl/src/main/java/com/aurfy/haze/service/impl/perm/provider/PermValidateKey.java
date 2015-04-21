package com.aurfy.haze.service.impl.perm.provider;

public class PermValidateKey {
	
	private String userId;
	private String entryKey;
	
	public PermValidateKey(String userId, String entryKey){
		this.userId = userId;
		this.entryKey = entryKey;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEntryKey() {
		return entryKey;
	}
	public void setEntryKey(String entryKey) {
		this.entryKey = entryKey;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PermValidateKey [userId=");
		builder.append(userId);
		builder.append(", entryKey=");
		builder.append(entryKey);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entryKey == null) ? 0 : entryKey.hashCode());
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
		PermValidateKey other = (PermValidateKey) obj;
		if (entryKey == null) {
			if (other.entryKey != null)
				return false;
		} else if (!entryKey.equals(other.entryKey))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	

}
