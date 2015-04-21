package com.aurfy.haze.core.model.infra;

import java.util.Date;

import com.aurfy.haze.core.model.TextualIDModel;

public class ClearingSummary extends TextualIDModel{

	
	private String scheduleBatchID;
	
	private Date clearingDate;
	
	private String merchantID;
	
	private String merSubID;
	
	private String clearingCurrency;
	
	private Float exchangeRate;
	
	private Long incomeAmount;
	
	private Long opAmount;
	
	private Long depositAmount;
	
	private Long balanceAmount;
	
	private String comments;

	public String getScheduleBatchID() {
		return scheduleBatchID;
	}

	public void setScheduleBatchID(String scheduleBatchID) {
		this.scheduleBatchID = scheduleBatchID;
	}

	public Date getClearingDate() {
		return clearingDate;
	}

	public void setClearingDate(Date clearingDate) {
		this.clearingDate = clearingDate;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public String getMerSubID() {
		return merSubID;
	}

	public void setMerSubID(String merSubID) {
		this.merSubID = merSubID;
	}

	public String getClearingCurrency() {
		return clearingCurrency;
	}

	public void setClearingCurrency(String clearingCurrency) {
		this.clearingCurrency = clearingCurrency;
	}

	public Float getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Long getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(Long incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public Long getOpAmount() {
		return opAmount;
	}

	public void setOpAmount(Long opAmount) {
		this.opAmount = opAmount;
	}

	public Long getDepositAmount() {
		return depositAmount;
	}
	
	public void setDepositAmount(Long depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Long getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Long balanceAmount) {
		this.balanceAmount = balanceAmount;
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
		result = prime * result + ((balanceAmount == null) ? 0 : balanceAmount.hashCode());
		result = prime * result + ((clearingCurrency == null) ? 0 : clearingCurrency.hashCode());
		result = prime * result + ((clearingDate == null) ? 0 : clearingDate.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((depositAmount == null) ? 0 : depositAmount.hashCode());
		result = prime * result + ((exchangeRate == null) ? 0 : exchangeRate.hashCode());
		result = prime * result + ((incomeAmount == null) ? 0 : incomeAmount.hashCode());
		result = prime * result + ((merSubID == null) ? 0 : merSubID.hashCode());
		result = prime * result + ((merchantID == null) ? 0 : merchantID.hashCode());
		result = prime * result + ((opAmount == null) ? 0 : opAmount.hashCode());
		result = prime * result + ((scheduleBatchID == null) ? 0 : scheduleBatchID.hashCode());
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
		ClearingSummary other = (ClearingSummary) obj;
		if (balanceAmount == null) {
			if (other.balanceAmount != null)
				return false;
		} else if (!balanceAmount.equals(other.balanceAmount))
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
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (depositAmount == null) {
			if (other.depositAmount != null)
				return false;
		} else if (!depositAmount.equals(other.depositAmount))
			return false;
		if (exchangeRate == null) {
			if (other.exchangeRate != null)
				return false;
		} else if (!exchangeRate.equals(other.exchangeRate))
			return false;
		if (incomeAmount == null) {
			if (other.incomeAmount != null)
				return false;
		} else if (!incomeAmount.equals(other.incomeAmount))
			return false;
		if (merSubID == null) {
			if (other.merSubID != null)
				return false;
		} else if (!merSubID.equals(other.merSubID))
			return false;
		if (merchantID == null) {
			if (other.merchantID != null)
				return false;
		} else if (!merchantID.equals(other.merchantID))
			return false;
		if (opAmount == null) {
			if (other.opAmount != null)
				return false;
		} else if (!opAmount.equals(other.opAmount))
			return false;
		if (scheduleBatchID == null) {
			if (other.scheduleBatchID != null)
				return false;
		} else if (!scheduleBatchID.equals(other.scheduleBatchID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClearingSummary [scheduleBatchID=");
		builder.append(scheduleBatchID);
		builder.append(", clearingDate=");
		builder.append(clearingDate);
		builder.append(", merchantID=");
		builder.append(merchantID);
		builder.append(", merSubID=");
		builder.append(merSubID);
		builder.append(", clearingCurrency=");
		builder.append(clearingCurrency);
		builder.append(", exchangeRate=");
		builder.append(exchangeRate);
		builder.append(", incomeAmount=");
		builder.append(incomeAmount);
		builder.append(", opAmount=");
		builder.append(opAmount);
		builder.append(", depositAmount=");
		builder.append(depositAmount);
		builder.append(", balanceAmount=");
		builder.append(balanceAmount);
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
