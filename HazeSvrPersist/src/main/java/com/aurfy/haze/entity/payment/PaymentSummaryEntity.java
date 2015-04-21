package com.aurfy.haze.entity.payment;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.payment.PaymentSummary;
import com.aurfy.haze.entity.Entity;

@Alias("PaymentSummaryEntity")
public class PaymentSummaryEntity extends PaymentSummary implements Entity {

	private String refFlowId;// 原始订单的支付信息，适用于退货等情况
	private String refSummaryId;
	private String deliveryApprovalId;// 妥投审核表

	public String getRefFlowId() {
		return refFlowId;
	}

	public void setRefFlowId(String refFlowId) {
		this.refFlowId = refFlowId;
	}

	/**
	 * 原始订单的支付信息，适用于退货等情况
	 * 
	 * @return
	 */
	public String getRefSummaryId() {
		return refSummaryId;
	}

	public void setRefSummaryId(String refSummaryId) {
		this.refSummaryId = refSummaryId;
	}

	/**
	 * 妥投审核表
	 * 
	 * @return
	 */
	public String getDeliveryApprovalId() {
		return deliveryApprovalId;
	}

	public void setDeliveryApprovalId(String deliveryApprovalId) {
		this.deliveryApprovalId = deliveryApprovalId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentSummaryEntity [refFlowId=");
		builder.append(refFlowId);
		builder.append(", refSummaryId=");
		builder.append(refSummaryId);
		builder.append(", deliveryApprovalId=");
		builder.append(deliveryApprovalId);
		builder.append(", getStatus()=");
		builder.append(getStatus());
		builder.append(", getBankCurrency()=");
		builder.append(getBankCurrency());
		builder.append(", getBankAmount()=");
		builder.append(getBankAmount());
		builder.append(", getPayCredential()=");
		builder.append(getPayCredential());
		builder.append(", getOpRateClassifier()=");
		builder.append(getOpRateClassifier());
		builder.append(", getOpAmount()=");
		builder.append(getOpAmount());
		builder.append(", getOpCurrency()=");
		builder.append(getOpCurrency());
		builder.append(", getIssueBank()=");
		builder.append(getIssueBank());
		builder.append(", getIs3d()=");
		builder.append(getIs3d());
		builder.append(", getIsDcc()=");
		builder.append(getIsDcc());
		builder.append(", getCompleteDate()=");
		builder.append(getCompleteDate());
		builder.append(", getRemainAmount()=");
		builder.append(getRemainAmount());
		builder.append(", getRemainCurrency()=");
		builder.append(getRemainCurrency());
		builder.append(", getDeliveryDate()=");
		builder.append(getDeliveryDate());
		builder.append(", getOwnerId()=");
		builder.append(getOwnerId());
		builder.append(", getExchangeRate()=");
		builder.append(getExchangeRate());
		builder.append(", getSrcPayOrder()=");
		builder.append(getSrcPayOrder());
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
		result = prime * result + ((deliveryApprovalId == null) ? 0 : deliveryApprovalId.hashCode());
		result = prime * result + ((refFlowId == null) ? 0 : refFlowId.hashCode());
		result = prime * result + ((refSummaryId == null) ? 0 : refSummaryId.hashCode());
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
		PaymentSummaryEntity other = (PaymentSummaryEntity) obj;
		if (deliveryApprovalId == null) {
			if (other.deliveryApprovalId != null)
				return false;
		} else if (!deliveryApprovalId.equals(other.deliveryApprovalId))
			return false;
		if (refFlowId == null) {
			if (other.refFlowId != null)
				return false;
		} else if (!refFlowId.equals(other.refFlowId))
			return false;
		if (refSummaryId == null) {
			if (other.refSummaryId != null)
				return false;
		} else if (!refSummaryId.equals(other.refSummaryId))
			return false;
		return true;
	}


}
