package com.aurfy.haze.core.model.payment;

import java.util.Date;

import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.txn.BankOrder;
import com.aurfy.haze.core.model.txn.MerchantOrder;
import com.aurfy.haze.core.model.txn.SrcPayOrder;

/**
 * 字段说明：<br />
 * 1.bankOrder中txtDate为创建flow的时间，一般和createDate一致。<br />
 * 2.sendDate为发送flow给银行的时间，一般会比bankOrder中txtDate稍晚一些。<br />
 * 
 * @author rocket
 *
 */
public class PaymentFlow extends TextualIDModel {

	private SrcPayOrder srcPayOrder;
	private String channelId;
	private String providerId;
	private PayCredential payCredential;
	private BankOrder bankOrder;
	private String sendUrl;
	private Date sendDate;
	private Date receiveDate;
	private String bankFlowId;
	private Date bankRespDate;
	private String bankAuthCode;
	private String rawRespCode;
	private String rawRespMsg;
	private String mappedRespCode;
	private PayStatusEnum status;// 当需要自动或人工处理某些流水时（如需退货等），将此标志位置为特殊条件，方便查询
	private Date reconciliationDate;// 勾兑时间
	private boolean reconciliationStatus;// 勾兑状态：已勾兑，未勾兑
	private String refFlowId;// 需做反向交易时，原始流水号
	private String comments;

	public SrcPayOrder getSrcPayOrder() {
		return srcPayOrder;
	}

	public void setSrcPayOrder(SrcPayOrder srcPayOrder) {
		this.srcPayOrder = srcPayOrder;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public PayCredential getPayCredential() {
		return payCredential;
	}

	public void setPayCredential(PayCredential payCredential) {
		this.payCredential = payCredential;
	}

	public BankOrder getBankOrder() {
		return bankOrder;
	}

	public void setBankOrder(BankOrder bankOrder) {
		this.bankOrder = bankOrder;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	/**
	 * 银行返回的流水号
	 * 
	 * @return
	 */
	public String getBankFlowId() {
		return bankFlowId;
	}

	public void setBankFlowId(String bankFlowId) {
		this.bankFlowId = bankFlowId;
	}

	public String getRawRespCode() {
		return rawRespCode;
	}

	public void setRawRespCode(String rawRespCode) {
		this.rawRespCode = rawRespCode;
	}

	public String getMappedRespCode() {
		return mappedRespCode;
	}

	public void setMappedRespCode(String mappedRespCode) {
		this.mappedRespCode = mappedRespCode;
	}

	public PayStatusEnum getStatus() {
		return status;
	}

	public void setStatus(PayStatusEnum status) {
		this.status = status;
	}

	public boolean getReconciliationStatus() {
		return reconciliationStatus;
	}

	public void setReconciliationStatus(boolean reconciliationStatus) {
		this.reconciliationStatus = reconciliationStatus;
	}

	public String getRefFlowId() {
		return refFlowId;
	}

	public void setRefFlowId(String refFlowId) {
		this.refFlowId = refFlowId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getBankAuthCode() {
		return bankAuthCode;
	}

	public void setBankAuthCode(String bankAuthCode) {
		this.bankAuthCode = bankAuthCode;
	}

	public String getRawRespMsg() {
		return rawRespMsg;
	}

	public void setRawRespMsg(String rawRespMsg) {
		this.rawRespMsg = rawRespMsg;
	}

	public String getSendUrl() {
		return sendUrl;
	}

	public void setSendUrl(String sendUrl) {
		this.sendUrl = sendUrl;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public Date getBankRespDate() {
		return bankRespDate;
	}

	public void setBankRespDate(Date bankRespDate) {
		this.bankRespDate = bankRespDate;
	}

	public Date getReconciliationDate() {
		return reconciliationDate;
	}

	public void setReconciliationDate(Date reconciliationDate) {
		this.reconciliationDate = reconciliationDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentFlow [srcPayOrder=");
		builder.append(srcPayOrder);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", providerId=");
		builder.append(providerId);
		builder.append(", payCredential=");
		builder.append(payCredential);
		builder.append(", bankOrder=");
		builder.append(bankOrder);
		builder.append(", sendUrl=");
		builder.append(sendUrl);
		builder.append(", sendDate=");
		builder.append(sendDate);
		builder.append(", receiveDate=");
		builder.append(receiveDate);
		builder.append(", bankFlowId=");
		builder.append(bankFlowId);
		builder.append(", bankRespDate=");
		builder.append(bankRespDate);
		builder.append(", bankAuthCode=");
		builder.append(bankAuthCode);
		builder.append(", rawRespCode=");
		builder.append(rawRespCode);
		builder.append(", rawRespMsg=");
		builder.append(rawRespMsg);
		builder.append(", mappedRespCode=");
		builder.append(mappedRespCode);
		builder.append(", status=");
		builder.append(status);
		builder.append(", reconciliationDate=");
		builder.append(reconciliationDate);
		builder.append(", reconciliationStatus=");
		builder.append(reconciliationStatus);
		builder.append(", refFlowId=");
		builder.append(refFlowId);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bankAuthCode == null) ? 0 : bankAuthCode.hashCode());
		result = prime * result + ((bankFlowId == null) ? 0 : bankFlowId.hashCode());
		result = prime * result + ((bankOrder == null) ? 0 : bankOrder.hashCode());
		result = prime * result + ((bankRespDate == null) ? 0 : bankRespDate.hashCode());
		result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((mappedRespCode == null) ? 0 : mappedRespCode.hashCode());
		result = prime * result + ((payCredential == null) ? 0 : payCredential.hashCode());
		result = prime * result + ((providerId == null) ? 0 : providerId.hashCode());
		result = prime * result + ((rawRespCode == null) ? 0 : rawRespCode.hashCode());
		result = prime * result + ((rawRespMsg == null) ? 0 : rawRespMsg.hashCode());
		result = prime * result + ((receiveDate == null) ? 0 : receiveDate.hashCode());
		result = prime * result + ((reconciliationDate == null) ? 0 : reconciliationDate.hashCode());
		result = prime * result + (reconciliationStatus ? 1231 : 1237);
		result = prime * result + ((refFlowId == null) ? 0 : refFlowId.hashCode());
		result = prime * result + ((sendDate == null) ? 0 : sendDate.hashCode());
		result = prime * result + ((sendUrl == null) ? 0 : sendUrl.hashCode());
		result = prime * result + ((srcPayOrder == null) ? 0 : srcPayOrder.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		PaymentFlow other = (PaymentFlow) obj;
		if (bankAuthCode == null) {
			if (other.bankAuthCode != null)
				return false;
		} else if (!bankAuthCode.equals(other.bankAuthCode))
			return false;
		if (bankFlowId == null) {
			if (other.bankFlowId != null)
				return false;
		} else if (!bankFlowId.equals(other.bankFlowId))
			return false;
		if (bankOrder == null) {
			if (other.bankOrder != null)
				return false;
		} else if (!bankOrder.equals(other.bankOrder))
			return false;
		if (bankRespDate == null) {
			if (other.bankRespDate != null)
				return false;
		} else if (!bankRespDate.equals(other.bankRespDate))
			return false;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (mappedRespCode == null) {
			if (other.mappedRespCode != null)
				return false;
		} else if (!mappedRespCode.equals(other.mappedRespCode))
			return false;
		if (payCredential == null) {
			if (other.payCredential != null)
				return false;
		} else if (!payCredential.equals(other.payCredential))
			return false;
		if (providerId == null) {
			if (other.providerId != null)
				return false;
		} else if (!providerId.equals(other.providerId))
			return false;
		if (rawRespCode == null) {
			if (other.rawRespCode != null)
				return false;
		} else if (!rawRespCode.equals(other.rawRespCode))
			return false;
		if (rawRespMsg == null) {
			if (other.rawRespMsg != null)
				return false;
		} else if (!rawRespMsg.equals(other.rawRespMsg))
			return false;
		if (receiveDate == null) {
			if (other.receiveDate != null)
				return false;
		} else if (!receiveDate.equals(other.receiveDate))
			return false;
		if (reconciliationDate == null) {
			if (other.reconciliationDate != null)
				return false;
		} else if (!reconciliationDate.equals(other.reconciliationDate))
			return false;
		if (reconciliationStatus != other.reconciliationStatus)
			return false;
		if (refFlowId == null) {
			if (other.refFlowId != null)
				return false;
		} else if (!refFlowId.equals(other.refFlowId))
			return false;
		if (sendDate == null) {
			if (other.sendDate != null)
				return false;
		} else if (!sendDate.equals(other.sendDate))
			return false;
		if (sendUrl == null) {
			if (other.sendUrl != null)
				return false;
		} else if (!sendUrl.equals(other.sendUrl))
			return false;
		if (srcPayOrder == null) {
			if (other.srcPayOrder != null)
				return false;
		} else if (!srcPayOrder.equals(other.srcPayOrder))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

}
