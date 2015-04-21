package com.aurfy.haze.entity.perm;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.User;
import com.aurfy.haze.core.model.perm.PermRole;
import com.aurfy.haze.entity.Entity;

@Alias("PermRelationEntity")
public class PermRelationEntity implements Entity {

	private User user;
	private PermRole role;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PermRole getRole() {
		return role;
	}

	public void setRole(PermRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PermRelationEntity [user=");
		builder.append(user);
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		PermRelationEntity other = (PermRelationEntity) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
