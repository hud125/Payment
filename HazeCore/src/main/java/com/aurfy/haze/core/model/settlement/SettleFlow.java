package com.aurfy.haze.core.model.settlement;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.payment.OpRateClassifier;
import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.txn.BankOrder;
import com.aurfy.haze.core.model.txn.MerchantOrder;

public class SettleFlow extends TextualIDModel {

	private MerchantOrder merOrder;
	private String paymentSummaryId;
	private String payFlowId;
	private String channelId;
	private String acquirerId;
	private String bankId;
	private PayCredential payCredential;// only use virtualAccount,cardOrg,encryptedCardNo,maskCardNo
	private String txnId;
	private BankOrder bankOrder;
	private String bankFlowId;// 银行流水号
	private Date payDate;// 支付完成日期
	private BigDecimal clearingExchangeRate;// 适用于银行结算币种和商户结算币种不同的场景（我们做汇转结给商户）
	private BigInteger clearingAmount;
	private Currency clearingCurrency;
	private OpRateClassifier opRateClassifier;// 内扣外扣
	private BigDecimal opExchangeRate;// 清分时币种转换所用汇率，从订单币种直接到商户结算币种，主要计算手续费及显示给商户用。
	private BigInteger opAmount;
	private BigDecimal opCurrency;
	private BigInteger depositAmount;
	private String scheduleBatchId;// 清算批次号
	private Date clearingDate;
	private ClearingStatusEnum clearingStatus;
	private FreezePolicyEnum freezePolicy;// 该笔资金冻结策略，适用于退货&拒付等情况
	private Date settleDate;
	private SettleStatusEnum settleStatus;
	private SettleConditionEnum settleCondition;
	private Date deliveryDate;
	private String depositScheduleBatchId;// 该笔保证金的结算批次
	private Date depositSettleDate;// 该笔保证金的结算日期
	private DepositSettleStatusEnum depositSettleStatus;// 保证金结算状态
	private String comments;

	public MerchantOrder getMerOrder() {
		return merOrder;
	}

	public void setMerOrder(MerchantOrder merOrder) {
		this.merOrder = merOrder;
	}

	public String getPaymentSummaryId() {
		return paymentSummaryId;
	}

	public void setPaymentSummaryId(String paymentSummaryId) {
		this.paymentSummaryId = paymentSummaryId;
	}

	public String getPayFlowId() {
		return payFlowId;
	}

	public void setPayFlowId(String payFlowId) {
		this.payFlowId = payFlowId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getAcquirerId() {
		return acquirerId;
	}

	public void setAcquirerId(String acquirerId) {
		this.acquirerId = acquirerId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public PayCredential getPayCredential() {
		return payCredential;
	}

	public void setPayCredential(PayCredential payCredential) {
		this.payCredential = payCredential;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public BankOrder getBankOrder() {
		return bankOrder;
	}

	public void setBankOrder(BankOrder bankOrder) {
		this.bankOrder = bankOrder;
	}

	public String getBankFlowId() {
		return bankFlowId;
	}

	public void setBankFlowId(String bankFlowId) {
		this.bankFlowId = bankFlowId;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public BigDecimal getClearingExchangeRate() {
		return clearingExchangeRate;
	}

	public void setClearingExchangeRate(BigDecimal clearingExchangeRate) {
		this.clearingExchangeRate = clearingExchangeRate;
	}

	public BigInteger getClearingAmount() {
		return clearingAmount;
	}

	public void setClearingAmount(BigInteger clearingAmount) {
		this.clearingAmount = clearingAmount;
	}

	public Currency getClearingCurrency() {
		return clearingCurrency;
	}

	public void setClearingCurrency(Currency clearingCurrency) {
		this.clearingCurrency = clearingCurrency;
	}

	public OpRateClassifier getOpRateClassifier() {
		return opRateClassifier;
	}

	public void setOpRateClassifier(OpRateClassifier opRateClassifier) {
		this.opRateClassifier = opRateClassifier;
	}

	public BigDecimal getOpExchangeRate() {
		return opExchangeRate;
	}

	public void setOpExchangeRate(BigDecimal opExchangeRate) {
		this.opExchangeRate = opExchangeRate;
	}

	public BigInteger getOpAmount() {
		return opAmount;
	}

	public void setOpAmount(BigInteger opAmount) {
		this.opAmount = opAmount;
	}

	public BigInteger getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(BigInteger depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getScheduleBatchId() {
		return scheduleBatchId;
	}

	public void setScheduleBatchId(String scheduleBatchId) {
		this.scheduleBatchId = scheduleBatchId;
	}

	public Date getClearingDate() {
		return clearingDate;
	}

	public void setClearingDate(Date clearingDate) {
		this.clearingDate = clearingDate;
	}

	public ClearingStatusEnum getClearingStatus() {
		return clearingStatus;
	}

	public void setClearingStatus(ClearingStatusEnum clearingStatus) {
		this.clearingStatus = clearingStatus;
	}

	public FreezePolicyEnum getFreezePolicy() {
		return freezePolicy;
	}

	public void setFreezePolicy(FreezePolicyEnum freezePolicy) {
		this.freezePolicy = freezePolicy;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	public SettleStatusEnum getSettleStatus() {
		return settleStatus;
	}

	public void setSettleStatus(SettleStatusEnum settleStatus) {
		this.settleStatus = settleStatus;
	}

	public SettleConditionEnum getSettleCondition() {
		return settleCondition;
	}

	public void setSettleCondition(SettleConditionEnum settleCondition) {
		this.settleCondition = settleCondition;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDepositScheduleBatchId() {
		return depositScheduleBatchId;
	}

	public void setDepositScheduleBatchId(String depositScheduleBatchId) {
		this.depositScheduleBatchId = depositScheduleBatchId;
	}

	public Date getDepositSettleDate() {
		return depositSettleDate;
	}

	public void setDepositSettleDate(Date depositSettleDate) {
		this.depositSettleDate = depositSettleDate;
	}

	public DepositSettleStatusEnum getDepositSettleStatus() {
		return depositSettleStatus;
	}

	public void setDepositSettleStatus(DepositSettleStatusEnum depositSettleStatus) {
		this.depositSettleStatus = depositSettleStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getOpCurrency() {
		return opCurrency;
	}

	public void setOpCurrency(BigDecimal opCurrency) {
		this.opCurrency = opCurrency;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SettleFlow [merOrder=");
		builder.append(merOrder);
		builder.append(", paymentSummaryId=");
		builder.append(paymentSummaryId);
		builder.append(", payFlowId=");
		builder.append(payFlowId);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", acquirerId=");
		builder.append(acquirerId);
		builder.append(", bankId=");
		builder.append(bankId);
		builder.append(", payCredential=");
		builder.append(payCredential);
		builder.append(", txnId=");
		builder.append(txnId);
		builder.append(", bankOrder=");
		builder.append(bankOrder);
		builder.append(", bankFlowId=");
		builder.append(bankFlowId);
		builder.append(", payDate=");
		builder.append(payDate);
		builder.append(", clearingExchangeRate=");
		builder.append(clearingExchangeRate);
		builder.append(", clearingAmount=");
		builder.append(clearingAmount);
		builder.append(", clearingCurrency=");
		builder.append(clearingCurrency);
		builder.append(", opRateClassifier=");
		builder.append(opRateClassifier);
		builder.append(", opExchangeRate=");
		builder.append(opExchangeRate);
		builder.append(", opAmount=");
		builder.append(opAmount);
		builder.append(", opCurrency=");
		builder.append(opCurrency);
		builder.append(", depositAmount=");
		builder.append(depositAmount);
		builder.append(", scheduleBatchId=");
		builder.append(scheduleBatchId);
		builder.append(", clearingDate=");
		builder.append(clearingDate);
		builder.append(", clearingStatus=");
		builder.append(clearingStatus);
		builder.append(", freezePolicy=");
		builder.append(freezePolicy);
		builder.append(", settleDate=");
		builder.append(settleDate);
		builder.append(", settleStatus=");
		builder.append(settleStatus);
		builder.append(", settleCondition=");
		builder.append(settleCondition);
		builder.append(", deliveryDate=");
		builder.append(deliveryDate);
		builder.append(", depositScheduleBatchId=");
		builder.append(depositScheduleBatchId);
		builder.append(", depositSettleDate=");
		builder.append(depositSettleDate);
		builder.append(", depositSettleStatus=");
		builder.append(depositSettleStatus);
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
		result = prime * result + ((acquirerId == null) ? 0 : acquirerId.hashCode());
		result = prime * result + ((bankFlowId == null) ? 0 : bankFlowId.hashCode());
		result = prime * result + ((bankId == null) ? 0 : bankId.hashCode());
		result = prime * result + ((bankOrder == null) ? 0 : bankOrder.hashCode());
		result = prime * result + ((channelId == null) ? 0 : channelId.hashCode());
		result = prime * result + ((clearingAmount == null) ? 0 : clearingAmount.hashCode());
		result = prime * result + ((clearingCurrency == null) ? 0 : clearingCurrency.hashCode());
		result = prime * result + ((clearingDate == null) ? 0 : clearingDate.hashCode());
		result = prime * result + ((clearingExchangeRate == null) ? 0 : clearingExchangeRate.hashCode());
		result = prime * result + ((clearingStatus == null) ? 0 : clearingStatus.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((depositAmount == null) ? 0 : depositAmount.hashCode());
		result = prime * result + ((depositScheduleBatchId == null) ? 0 : depositScheduleBatchId.hashCode());
		result = prime * result + ((depositSettleDate == null) ? 0 : depositSettleDate.hashCode());
		result = prime * result + ((depositSettleStatus == null) ? 0 : depositSettleStatus.hashCode());
		result = prime * result + ((freezePolicy == null) ? 0 : freezePolicy.hashCode());
		result = prime * result + ((merOrder == null) ? 0 : merOrder.hashCode());
		result = prime * result + ((opAmount == null) ? 0 : opAmount.hashCode());
		result = prime * result + ((opCurrency == null) ? 0 : opCurrency.hashCode());
		result = prime * result + ((opExchangeRate == null) ? 0 : opExchangeRate.hashCode());
		result = prime * result + ((opRateClassifier == null) ? 0 : opRateClassifier.hashCode());
		result = prime * result + ((payCredential == null) ? 0 : payCredential.hashCode());
		result = prime * result + ((payDate == null) ? 0 : payDate.hashCode());
		result = prime * result + ((payFlowId == null) ? 0 : payFlowId.hashCode());
		result = prime * result + ((paymentSummaryId == null) ? 0 : paymentSummaryId.hashCode());
		result = prime * result + ((scheduleBatchId == null) ? 0 : scheduleBatchId.hashCode());
		result = prime * result + ((settleCondition == null) ? 0 : settleCondition.hashCode());
		result = prime * result + ((settleDate == null) ? 0 : settleDate.hashCode());
		result = prime * result + ((settleStatus == null) ? 0 : settleStatus.hashCode());
		result = prime * result + ((txnId == null) ? 0 : txnId.hashCode());
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
		SettleFlow other = (SettleFlow) obj;
		if (acquirerId == null) {
			if (other.acquirerId != null)
				return false;
		} else if (!acquirerId.equals(other.acquirerId))
			return false;
		if (bankFlowId == null) {
			if (other.bankFlowId != null)
				return false;
		} else if (!bankFlowId.equals(other.bankFlowId))
			return false;
		if (bankId == null) {
			if (other.bankId != null)
				return false;
		} else if (!bankId.equals(other.bankId))
			return false;
		if (bankOrder == null) {
			if (other.bankOrder != null)
				return false;
		} else if (!bankOrder.equals(other.bankOrder))
			return false;
		if (channelId == null) {
			if (other.channelId != null)
				return false;
		} else if (!channelId.equals(other.channelId))
			return false;
		if (clearingAmount == null) {
			if (other.clearingAmount != null)
				return false;
		} else if (!clearingAmount.equals(other.clearingAmount))
			return false;
		if (clearingCurrency == null) {
			if (other.clearingCurrency != null)
				return false;
		} else if (!clearingCurrency.equals(other.clearingCurrency))
			return false;
		if (clearingDate == null) {
			if (other.clearingDate != null)
				return false;
		} else if (!clearingDate.equals(other.clearingDate))
			return false;
		if (clearingExchangeRate == null) {
			if (other.clearingExchangeRate != null)
				return false;
		} else if (!clearingExchangeRate.equals(other.clearingExchangeRate))
			return false;
		if (clearingStatus != other.clearingStatus)
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (depositAmount == null) {
			if (other.depositAmount != null)
				return false;
		} else if (!depositAmount.equals(other.depositAmount))
			return false;
		if (depositScheduleBatchId == null) {
			if (other.depositScheduleBatchId != null)
				return false;
		} else if (!depositScheduleBatchId.equals(other.depositScheduleBatchId))
			return false;
		if (depositSettleDate == null) {
			if (other.depositSettleDate != null)
				return false;
		} else if (!depositSettleDate.equals(other.depositSettleDate))
			return false;
		if (depositSettleStatus != other.depositSettleStatus)
			return false;
		if (freezePolicy != other.freezePolicy)
			return false;
		if (merOrder == null) {
			if (other.merOrder != null)
				return false;
		} else if (!merOrder.equals(other.merOrder))
			return false;
		if (opAmount == null) {
			if (other.opAmount != null)
				return false;
		} else if (!opAmount.equals(other.opAmount))
			return false;
		if (opCurrency == null) {
			if (other.opCurrency != null)
				return false;
		} else if (!opCurrency.equals(other.opCurrency))
			return false;
		if (opExchangeRate == null) {
			if (other.opExchangeRate != null)
				return false;
		} else if (!opExchangeRate.equals(other.opExchangeRate))
			return false;
		if (opRateClassifier != other.opRateClassifier)
			return false;
		if (payCredential == null) {
			if (other.payCredential != null)
				return false;
		} else if (!payCredential.equals(other.payCredential))
			return false;
		if (payDate == null) {
			if (other.payDate != null)
				return false;
		} else if (!payDate.equals(other.payDate))
			return false;
		if (payFlowId == null) {
			if (other.payFlowId != null)
				return false;
		} else if (!payFlowId.equals(other.payFlowId))
			return false;
		if (paymentSummaryId == null) {
			if (other.paymentSummaryId != null)
				return false;
		} else if (!paymentSummaryId.equals(other.paymentSummaryId))
			return false;
		if (scheduleBatchId == null) {
			if (other.scheduleBatchId != null)
				return false;
		} else if (!scheduleBatchId.equals(other.scheduleBatchId))
			return false;
		if (settleCondition != other.settleCondition)
			return false;
		if (settleDate == null) {
			if (other.settleDate != null)
				return false;
		} else if (!settleDate.equals(other.settleDate))
			return false;
		if (settleStatus != other.settleStatus)
			return false;
		if (txnId == null) {
			if (other.txnId != null)
				return false;
		} else if (!txnId.equals(other.txnId))
			return false;
		return true;
	}

	

}
