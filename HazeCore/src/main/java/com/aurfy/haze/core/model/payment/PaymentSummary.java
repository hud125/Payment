package com.aurfy.haze.core.model.payment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.txn.BankOrder;
import com.aurfy.haze.core.model.txn.MerchantOrder;
import com.aurfy.haze.core.model.txn.SrcPayOrder;

/**
 * 支付信息摘要表：每笔订单仅对应一条记录。<br />
 * 
 * 注意：<br />
 * src_amount, src_currency和mer_amount, mer_currency在不同表中含义不同。<br />
 * src_currency是指商户发过来的币种，其实src就是mer，只是在这张表中需要换个表示。
 * 
 * @author rocket
 *
 */
public class PaymentSummary extends TextualIDModel {

	private SrcPayOrder srcPayOrder;
	private OpRateClassifier opRateClassifier;// 内扣外扣
	private BigInteger opAmount;
	private Currency opCurrency;
	private Currency bankCurrency;
	private BigInteger bankAmount;
	private String issueBank;
	private PayCredential payCredential;
	private boolean is3d;
	private boolean isDcc;
	private Date completeDate;// 支付完成时间
	private BigDecimal exchangeRate;
	private PayStatusEnum status;
	private BigInteger remainAmount;// 剩余可用金额：默认为原始订单金额，退货后需更新。币种建议跟原始币种保持一致。
	private Currency remainCurrency;// 可用余额的币种
	private Date deliveryDate;
	private String ownerId;

	public PayStatusEnum getStatus() {
		return status;
	}

	public void setStatus(PayStatusEnum status) {
		this.status = status;
	}

	public void setDcc(boolean isDcc) {
		this.isDcc = isDcc;
	}

	public Currency getBankCurrency() {
		return bankCurrency;
	}

	public void setBankCurrency(Currency bankCurrency) {
		this.bankCurrency = bankCurrency;
	}

	public BigInteger getBankAmount() {
		return bankAmount;
	}

	public void setBankAmount(BigInteger bankAmount) {
		this.bankAmount = bankAmount;
	}

	/**
	 * In this class, bankOrder only use the column of card_org,encrypted_card_no,mask_card_no
	 * 
	 * @return
	 */
	public PayCredential getPayCredential() {
		return payCredential;
	}

	public void setPayCredential(PayCredential payCredential) {
		this.payCredential = payCredential;
	}

	/**
	 * 内扣外扣
	 * 
	 * @return
	 */
	public OpRateClassifier getOpRateClassifier() {
		return opRateClassifier;
	}

	public void setOpRateClassifier(OpRateClassifier opRateClassifier) {
		this.opRateClassifier = opRateClassifier;
	}

	public BigInteger getOpAmount() {
		return opAmount;
	}

	public void setOpAmount(BigInteger opAmount) {
		this.opAmount = opAmount;
	}

	public Currency getOpCurrency() {
		return opCurrency;
	}

	public void setOpCurrency(Currency opCurrency) {
		this.opCurrency = opCurrency;
	}

	/**
	 * 发卡行
	 * 
	 * @return
	 */
	public String getIssueBank() {
		return issueBank;
	}

	public void setIssueBank(String issueBank) {
		this.issueBank = issueBank;
	}

	public boolean getIs3d() {
		return is3d;
	}

	public void setIs3d(boolean is3d) {
		this.is3d = is3d;
	}

	public boolean getIsDcc() {
		return isDcc;
	}

	public void setIsDcc(boolean isDcc) {
		this.isDcc = isDcc;
	}

	/**
	 * 支付完成时间
	 * 
	 * @return
	 */
	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	/**
	 * 剩余可用金额：默认为原始订单金额，退货后需更新。币种建议跟原始币种保持一致。
	 * 
	 * @return
	 */
	public BigInteger getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(BigInteger remainAmount) {
		this.remainAmount = remainAmount;
	}

	/**
	 * 可用余额的币种
	 * 
	 * @return
	 */
	public Currency getRemainCurrency() {
		return remainCurrency;
	}

	public void setRemainCurrency(Currency remainCurrency) {
		this.remainCurrency = remainCurrency;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public SrcPayOrder getSrcPayOrder() {
		return srcPayOrder;
	}

	public void setSrcPayOrder(SrcPayOrder srcPayOrder) {
		this.srcPayOrder = srcPayOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bankAmount == null) ? 0 : bankAmount.hashCode());
		result = prime * result + ((bankCurrency == null) ? 0 : bankCurrency.hashCode());
		result = prime * result + ((completeDate == null) ? 0 : completeDate.hashCode());
		result = prime * result + ((deliveryDate == null) ? 0 : deliveryDate.hashCode());
		result = prime * result + ((exchangeRate == null) ? 0 : exchangeRate.hashCode());
		result = prime * result + (is3d ? 1231 : 1237);
		result = prime * result + (isDcc ? 1231 : 1237);
		result = prime * result + ((issueBank == null) ? 0 : issueBank.hashCode());
		result = prime * result + ((opAmount == null) ? 0 : opAmount.hashCode());
		result = prime * result + ((opCurrency == null) ? 0 : opCurrency.hashCode());
		result = prime * result + ((opRateClassifier == null) ? 0 : opRateClassifier.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((payCredential == null) ? 0 : payCredential.hashCode());
		result = prime * result + ((remainAmount == null) ? 0 : remainAmount.hashCode());
		result = prime * result + ((remainCurrency == null) ? 0 : remainCurrency.hashCode());
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
		PaymentSummary other = (PaymentSummary) obj;
		if (bankAmount == null) {
			if (other.bankAmount != null)
				return false;
		} else if (!bankAmount.equals(other.bankAmount))
			return false;
		if (bankCurrency == null) {
			if (other.bankCurrency != null)
				return false;
		} else if (!bankCurrency.equals(other.bankCurrency))
			return false;
		if (completeDate == null) {
			if (other.completeDate != null)
				return false;
		} else if (!completeDate.equals(other.completeDate))
			return false;
		if (deliveryDate == null) {
			if (other.deliveryDate != null)
				return false;
		} else if (!deliveryDate.equals(other.deliveryDate))
			return false;
		if (exchangeRate == null) {
			if (other.exchangeRate != null)
				return false;
		} else if (!exchangeRate.equals(other.exchangeRate))
			return false;
		if (is3d != other.is3d)
			return false;
		if (isDcc != other.isDcc)
			return false;
		if (issueBank == null) {
			if (other.issueBank != null)
				return false;
		} else if (!issueBank.equals(other.issueBank))
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
		if (opRateClassifier != other.opRateClassifier)
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (payCredential == null) {
			if (other.payCredential != null)
				return false;
		} else if (!payCredential.equals(other.payCredential))
			return false;
		if (remainAmount == null) {
			if (other.remainAmount != null)
				return false;
		} else if (!remainAmount.equals(other.remainAmount))
			return false;
		if (remainCurrency == null) {
			if (other.remainCurrency != null)
				return false;
		} else if (!remainCurrency.equals(other.remainCurrency))
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentSummary [srcPayOrder=");
		builder.append(srcPayOrder);
		builder.append(", opRateClassifier=");
		builder.append(opRateClassifier);
		builder.append(", opAmount=");
		builder.append(opAmount);
		builder.append(", opCurrency=");
		builder.append(opCurrency);
		builder.append(", bankCurrency=");
		builder.append(bankCurrency);
		builder.append(", bankAmount=");
		builder.append(bankAmount);
		builder.append(", issueBank=");
		builder.append(issueBank);
		builder.append(", payCredential=");
		builder.append(payCredential);
		builder.append(", is3d=");
		builder.append(is3d);
		builder.append(", isDcc=");
		builder.append(isDcc);
		builder.append(", completeDate=");
		builder.append(completeDate);
		builder.append(", exchangeRate=");
		builder.append(exchangeRate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", remainAmount=");
		builder.append(remainAmount);
		builder.append(", remainCurrency=");
		builder.append(remainCurrency);
		builder.append(", deliveryDate=");
		builder.append(deliveryDate);
		builder.append(", ownerId=");
		builder.append(ownerId);
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
