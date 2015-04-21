package com.aurfy.haze.core.model.infra;

import java.util.Date;

import com.aurfy.haze.core.model.TextualIDModel;

public class ClearingAccount extends TextualIDModel {

	private String scheduleBatchID;
	
	private Date clearingDate;
	
	private String merchantID;
	
	private String merSubID;
	
	private String clearingCurrency;
	
	private Long sumIncomeAmount;
	
	private Long sumOperationAmount;
	
	private Long sumDepositAmount;
	
	private Long sumReturnDepositAmount;
	
	private Long sumBalanceAmount;

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

	public Long getSumIncomeAmount() {
		return sumIncomeAmount;
	}

	public void setSumIncomeAmount(Long sumIncomeAmount) {
		this.sumIncomeAmount = sumIncomeAmount;
	}

	public Long getSumOperationAmount() {
		return sumOperationAmount;
	}

	public void setSumOperationAmount(Long sumOperationAmount) {
		this.sumOperationAmount = sumOperationAmount;
	}

	public Long getSumDepositAmount() {
		return sumDepositAmount;
	}

	public void setSumDepositAmount(Long sumDepositAmount) {
		this.sumDepositAmount = sumDepositAmount;
	}

	public Long getSumReturnDepositAmount() {
		return sumReturnDepositAmount;
	}

	public void setSumReturnDepositAmount(Long sumReturnDepositAmount) {
		this.sumReturnDepositAmount = sumReturnDepositAmount;
	}

	public Long getSumBalanceAmount() {
		return sumBalanceAmount;
	}

	public void setSumBalanceAmount(Long sumBalanceAmount) {
		this.sumBalanceAmount = sumBalanceAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((clearingCurrency == null) ? 0 : clearingCurrency.hashCode());
		result = prime * result + ((clearingDate == null) ? 0 : clearingDate.hashCode());
		result = prime * result + ((merSubID == null) ? 0 : merSubID.hashCode());
		result = prime * result + ((merchantID == null) ? 0 : merchantID.hashCode());
		result = prime * result + ((scheduleBatchID == null) ? 0 : scheduleBatchID.hashCode());
		result = prime * result + ((sumBalanceAmount == null) ? 0 : sumBalanceAmount.hashCode());
		result = prime * result + ((sumDepositAmount == null) ? 0 : sumDepositAmount.hashCode());
		result = prime * result + ((sumIncomeAmount == null) ? 0 : sumIncomeAmount.hashCode());
		result = prime * result + ((sumOperationAmount == null) ? 0 : sumOperationAmount.hashCode());
		result = prime * result + ((sumReturnDepositAmount == null) ? 0 : sumReturnDepositAmount.hashCode());
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
		ClearingAccount other = (ClearingAccount) obj;
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
		if (scheduleBatchID == null) {
			if (other.scheduleBatchID != null)
				return false;
		} else if (!scheduleBatchID.equals(other.scheduleBatchID))
			return false;
		if (sumBalanceAmount == null) {
			if (other.sumBalanceAmount != null)
				return false;
		} else if (!sumBalanceAmount.equals(other.sumBalanceAmount))
			return false;
		if (sumDepositAmount == null) {
			if (other.sumDepositAmount != null)
				return false;
		} else if (!sumDepositAmount.equals(other.sumDepositAmount))
			return false;
		if (sumIncomeAmount == null) {
			if (other.sumIncomeAmount != null)
				return false;
		} else if (!sumIncomeAmount.equals(other.sumIncomeAmount))
			return false;
		if (sumOperationAmount == null) {
			if (other.sumOperationAmount != null)
				return false;
		} else if (!sumOperationAmount.equals(other.sumOperationAmount))
			return false;
		if (sumReturnDepositAmount == null) {
			if (other.sumReturnDepositAmount != null)
				return false;
		} else if (!sumReturnDepositAmount.equals(other.sumReturnDepositAmount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClearingAcc [scheduleBatchID=");
		builder.append(scheduleBatchID);
		builder.append(", clearingDate=");
		builder.append(clearingDate);
		builder.append(", merchantID=");
		builder.append(merchantID);
		builder.append(", merSubID=");
		builder.append(merSubID);
		builder.append(", clearingCurrency=");
		builder.append(clearingCurrency);
		builder.append(", sumIncomeAmount=");
		builder.append(sumIncomeAmount);
		builder.append(", sumOperationAmount=");
		builder.append(sumOperationAmount);
		builder.append(", sumDepositAmount=");
		builder.append(sumDepositAmount);
		builder.append(", sumReturnDepositAmount=");
		builder.append(sumReturnDepositAmount);
		builder.append(", sumBalanceAmount=");
		builder.append(sumBalanceAmount);
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
