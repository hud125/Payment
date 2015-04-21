package com.aurfy.haze.core.model.infra.mer;

/**
 * Basic merchant information reference.
 * 
 * @author hermano
 *
 */
public class MerchantReference {

	public MerchantReference() {
	}

	private String merId;
	private String subMerId;
	private String merCode;

	private String salesId;
	private String agentId;
	private String pspId;

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getSubMerId() {
		return subMerId;
	}

	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getPspId() {
		return pspId;
	}

	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

	public String getMerCode() {
		return merCode;
	}

	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MerchantReference [merId=");
		builder.append(merId);
		builder.append(", subMerId=");
		builder.append(subMerId);
		builder.append(", merCode=");
		builder.append(merCode);
		builder.append(", salesId=");
		builder.append(salesId);
		builder.append(", agentId=");
		builder.append(agentId);
		builder.append(", pspId=");
		builder.append(pspId);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentId == null) ? 0 : agentId.hashCode());
		result = prime * result + ((merCode == null) ? 0 : merCode.hashCode());
		result = prime * result + ((merId == null) ? 0 : merId.hashCode());
		result = prime * result + ((pspId == null) ? 0 : pspId.hashCode());
		result = prime * result + ((salesId == null) ? 0 : salesId.hashCode());
		result = prime * result + ((subMerId == null) ? 0 : subMerId.hashCode());
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
		MerchantReference other = (MerchantReference) obj;
		if (agentId == null) {
			if (other.agentId != null)
				return false;
		} else if (!agentId.equals(other.agentId))
			return false;
		if (merCode == null) {
			if (other.merCode != null)
				return false;
		} else if (!merCode.equals(other.merCode))
			return false;
		if (merId == null) {
			if (other.merId != null)
				return false;
		} else if (!merId.equals(other.merId))
			return false;
		if (pspId == null) {
			if (other.pspId != null)
				return false;
		} else if (!pspId.equals(other.pspId))
			return false;
		if (salesId == null) {
			if (other.salesId != null)
				return false;
		} else if (!salesId.equals(other.salesId))
			return false;
		if (subMerId == null) {
			if (other.subMerId != null)
				return false;
		} else if (!subMerId.equals(other.subMerId))
			return false;
		return true;
	}

}
