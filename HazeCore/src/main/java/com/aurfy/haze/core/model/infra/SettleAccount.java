package com.aurfy.haze.core.model.infra;

import java.util.Date;

import com.aurfy.haze.core.model.TextualIDModel;

public class SettleAccount extends TextualIDModel {
	
	private String scheduleBatchID;
	
	private Date settleDate;
	
	private String merchantID;
	
	private String merSubID;

	private String settleCurrency;
	
	private Long sumIncomeAmount;
	
	private Long sumOperationAmount;
	
	private Long sumDepositAmount;
	
	private Long sumReturnDepositAmount;
	
	private Long sumFreezeAmount;
	
	private Long sumUnfreezeAmount;
	
	private Long sumBalanceAmount;
	
	private Long onwayDeposit;

	public String getScheduleBatchID() {
		return scheduleBatchID;
	}

	public void setScheduleBatchID(String scheduleBatchID) {
		this.scheduleBatchID = scheduleBatchID;
	}

	public Date getSettleDate() {
		return settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
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

	public String getSettleCurrency() {
		return settleCurrency;
	}

	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
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

	public Long getSumFreezeAmount() {
		return sumFreezeAmount;
	}

	public void setSumFreezeAmount(Long sumFreezeAmount) {
		this.sumFreezeAmount = sumFreezeAmount;
	}

	public Long getSumUnfreezeAmount() {
		return sumUnfreezeAmount;
	}

	public void setSumUnfreezeAmount(Long sumUnfreezeAmount) {
		this.sumUnfreezeAmount = sumUnfreezeAmount;
	}

	public Long getSumBalanceAmount() {
		return sumBalanceAmount;
	}

	public void setSumBalanceAmount(Long sumBalanceAmount) {
		this.sumBalanceAmount = sumBalanceAmount;
	}

	public Long getOnwayDeposit() {
		return onwayDeposit;
	}

	public void setOnwayDeposit(Long onwayDeposit) {
		this.onwayDeposit = onwayDeposit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((merSubID == null) ? 0 : merSubID.hashCode());
		result = prime * result + ((merchantID == null) ? 0 : merchantID.hashCode());
		result = prime * result + ((onwayDeposit == null) ? 0 : onwayDeposit.hashCode());
		result = prime * result + ((scheduleBatchID == null) ? 0 : scheduleBatchID.hashCode());
		result = prime * result + ((settleCurrency == null) ? 0 : settleCurrency.hashCode());
		result = prime * result + ((settleDate == null) ? 0 : settleDate.hashCode());
		result = prime * result + ((sumBalanceAmount == null) ? 0 : sumBalanceAmount.hashCode());
		result = prime * result + ((sumDepositAmount == null) ? 0 : sumDepositAmount.hashCode());
		result = prime * result + ((sumFreezeAmount == null) ? 0 : sumFreezeAmount.hashCode());
		result = prime * result + ((sumIncomeAmount == null) ? 0 : sumIncomeAmount.hashCode());
		result = prime * result + ((sumOperationAmount == null) ? 0 : sumOperationAmount.hashCode());
		result = prime * result + ((sumReturnDepositAmount == null) ? 0 : sumReturnDepositAmount.hashCode());
		result = prime * result + ((sumUnfreezeAmount == null) ? 0 : sumUnfreezeAmount.hashCode());
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
		SettleAccount other = (SettleAccount) obj;
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
		if (onwayDeposit == null) {
			if (other.onwayDeposit != null)
				return false;
		} else if (!onwayDeposit.equals(other.onwayDeposit))
			return false;
		if (scheduleBatchID == null) {
			if (other.scheduleBatchID != null)
				return false;
		} else if (!scheduleBatchID.equals(other.scheduleBatchID))
			return false;
		if (settleCurrency == null) {
			if (other.settleCurrency != null)
				return false;
		} else if (!settleCurrency.equals(other.settleCurrency))
			return false;
		if (settleDate == null) {
			if (other.settleDate != null)
				return false;
		} else if (!settleDate.equals(other.settleDate))
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
		if (sumFreezeAmount == null) {
			if (other.sumFreezeAmount != null)
				return false;
		} else if (!sumFreezeAmount.equals(other.sumFreezeAmount))
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
		if (sumUnfreezeAmount == null) {
			if (other.sumUnfreezeAmount != null)
				return false;
		} else if (!sumUnfreezeAmount.equals(other.sumUnfreezeAmount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SettleAccount [scheduleBatchID=");
		builder.append(scheduleBatchID);
		builder.append(", settleDate=");
		builder.append(settleDate);
		builder.append(", merchantID=");
		builder.append(merchantID);
		builder.append(", merSubID=");
		builder.append(merSubID);
		builder.append(", settleCurrency=");
		builder.append(settleCurrency);
		builder.append(", sumIncomeAmount=");
		builder.append(sumIncomeAmount);
		builder.append(", sumOperationAmount=");
		builder.append(sumOperationAmount);
		builder.append(", sumDepositAmount=");
		builder.append(sumDepositAmount);
		builder.append(", sumReturnDepositAmount=");
		builder.append(sumReturnDepositAmount);
		builder.append(", sumFreezeAmount=");
		builder.append(sumFreezeAmount);
		builder.append(", sumUnfreezeAmount=");
		builder.append(sumUnfreezeAmount);
		builder.append(", sumBalanceAmount=");
		builder.append(sumBalanceAmount);
		builder.append(", onwayDeposit=");
		builder.append(onwayDeposit);
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
