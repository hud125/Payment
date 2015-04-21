package com.aurfy.haze.entity.payment;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.payment.PaymentFlow;
import com.aurfy.haze.entity.Entity;

@Alias("PaymentFlowEntity")
public class PaymentFlowEntity extends PaymentFlow implements Entity {

	private String paySummaryId;

	public String getPaySummaryId() {
		return paySummaryId;
	}

	public void setPaySummaryId(String paySummaryId) {
		this.paySummaryId = paySummaryId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentFlowEntity [paySummaryId=");
		builder.append(paySummaryId);
		builder.append(", getSrcPayOrder()=");
		builder.append(getSrcPayOrder());
		builder.append(", getChannelId()=");
		builder.append(getChannelId());
		builder.append(", getPayCredential()=");
		builder.append(getPayCredential());
		builder.append(", getBankOrder()=");
		builder.append(getBankOrder());
		builder.append(", getSendDate()=");
		builder.append(getSendDate());
		builder.append(", getReceiveDate()=");
		builder.append(getReceiveDate());
		builder.append(", getBankFlowId()=");
		builder.append(getBankFlowId());
		builder.append(", getRawRespCode()=");
		builder.append(getRawRespCode());
		builder.append(", getMappedRespCode()=");
		builder.append(getMappedRespCode());
		builder.append(", getStatus()=");
		builder.append(getStatus());
		builder.append(", getReconciliationStatus()=");
		builder.append(getReconciliationStatus());
		builder.append(", getRefFlowId()=");
		builder.append(getRefFlowId());
		builder.append(", getComments()=");
		builder.append(getComments());
		builder.append(", getBankAuthCode()=");
		builder.append(getBankAuthCode());
		builder.append(", getRawRespMsg()=");
		builder.append(getRawRespMsg());
		builder.append(", getSendUrl()=");
		builder.append(getSendUrl());
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
		result = prime * result + ((paySummaryId == null) ? 0 : paySummaryId.hashCode());
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
		PaymentFlowEntity other = (PaymentFlowEntity) obj;
		if (paySummaryId == null) {
			if (other.paySummaryId != null)
				return false;
		} else if (!paySummaryId.equals(other.paySummaryId))
			return false;
		return true;
	}

}
