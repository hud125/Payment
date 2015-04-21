package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.system.ActionResultEnum;
import com.aurfy.haze.core.model.system.AuditActionEnum;
import com.aurfy.haze.core.model.system.SystemModuleEnum;

public class AuditLog extends TextualIDModel {

	private SystemModuleEnum module;
	private AuditActionEnum action;
	private ActionResultEnum result;
	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String ownerId;
	private String comments;

	public SystemModuleEnum getModule() {
		return module;
	}

	public void setModule(SystemModuleEnum module) {
		this.module = module;
	}

	public AuditActionEnum getAction() {
		return action;
	}

	public void setAction(AuditActionEnum action) {
		this.action = action;
	}

	public ActionResultEnum getResult() {
		return result;
	}

	public void setResult(ActionResultEnum result) {
		this.result = result;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public String getParam5() {
		return param5;
	}

	public void setParam5(String param5) {
		this.param5 = param5;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((module == null) ? 0 : module.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((param1 == null) ? 0 : param1.hashCode());
		result = prime * result + ((param2 == null) ? 0 : param2.hashCode());
		result = prime * result + ((param3 == null) ? 0 : param3.hashCode());
		result = prime * result + ((param4 == null) ? 0 : param4.hashCode());
		result = prime * result + ((param5 == null) ? 0 : param5.hashCode());
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
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
		AuditLog other = (AuditLog) obj;
		if (action != other.action)
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (module != other.module)
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (param1 == null) {
			if (other.param1 != null)
				return false;
		} else if (!param1.equals(other.param1))
			return false;
		if (param2 == null) {
			if (other.param2 != null)
				return false;
		} else if (!param2.equals(other.param2))
			return false;
		if (param3 == null) {
			if (other.param3 != null)
				return false;
		} else if (!param3.equals(other.param3))
			return false;
		if (param4 == null) {
			if (other.param4 != null)
				return false;
		} else if (!param4.equals(other.param4))
			return false;
		if (param5 == null) {
			if (other.param5 != null)
				return false;
		} else if (!param5.equals(other.param5))
			return false;
		if (result != other.result)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuditLog [module=");
		builder.append(module);
		builder.append(", action=");
		builder.append(action);
		builder.append(", result=");
		builder.append(result);
		builder.append(", param1=");
		builder.append(param1);
		builder.append(", param2=");
		builder.append(param2);
		builder.append(", param3=");
		builder.append(param3);
		builder.append(", param4=");
		builder.append(param4);
		builder.append(", param5=");
		builder.append(param5);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append(", comments=");
		builder.append(comments);
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
