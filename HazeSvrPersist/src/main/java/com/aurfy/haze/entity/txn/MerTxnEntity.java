package com.aurfy.haze.entity.txn;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.txn.MerTxn;
import com.aurfy.haze.entity.Entity;

@Alias("MerTxnEntity")
public class MerTxnEntity extends MerTxn implements Entity {

	private String settleRefId;
	private String payRefId;

	public String getSettleRefId() {
		return settleRefId;
	}

	public void setSettleRefId(String settleRefId) {
		this.settleRefId = settleRefId;
	}

	public String getPayRefId() {
		return payRefId;
	}

	public void setPayRefId(String payRefId) {
		this.payRefId = payRefId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MerTxnEntity [settleRefId=");
		builder.append(settleRefId);
		builder.append(", payRefId=");
		builder.append(payRefId);
		builder.append(", getMerOrder()=");
		builder.append(getMerOrder());
		builder.append(", getTxnStatus()=");
		builder.append(getTxnStatus());
		builder.append(", getOwnerId()=");
		builder.append(getOwnerId());
		builder.append(", getProductClassifier()=");
		builder.append(getProductClassifier());
		builder.append(", getTerminalId()=");
		builder.append(getTerminalId());
		builder.append(", getProtocolVer()=");
		builder.append(getProtocolVer());
		builder.append(", getCharsetEncoding()=");
		builder.append(getCharsetEncoding());
		builder.append(", getSignMethod()=");
		builder.append(getSignMethod());
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
		result = prime * result + ((payRefId == null) ? 0 : payRefId.hashCode());
		result = prime * result + ((settleRefId == null) ? 0 : settleRefId.hashCode());
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
		MerTxnEntity other = (MerTxnEntity) obj;
		if (payRefId == null) {
			if (other.payRefId != null)
				return false;
		} else if (!payRefId.equals(other.payRefId))
			return false;
		if (settleRefId == null) {
			if (other.settleRefId != null)
				return false;
		} else if (!settleRefId.equals(other.settleRefId))
			return false;
		return true;
	}

}
