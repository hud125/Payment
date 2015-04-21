package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.TextualIDModel;

public class WithdrawlAccount extends TextualIDModel {

	private long sumWithdrawlAmount;
	private Currency withdrawlCurrency;

	public long getSumWithdrawlAmount() {
		return sumWithdrawlAmount;
	}

	public void setSumWithdrawlAmount(long sumWithdrawlAmount) {
		this.sumWithdrawlAmount = sumWithdrawlAmount;
	}

	public Currency getWithdrawlCurrency() {
		return withdrawlCurrency;
	}

	public void setWithdrawlCurrency(Currency withdrawlCurrency) {
		this.withdrawlCurrency = withdrawlCurrency;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("withdrawlAccount [sumWithdrawlAmount=");
		builder.append(sumWithdrawlAmount);
		builder.append(", withdrawlCurrency=");
		builder.append(withdrawlCurrency);
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
		result = prime * result + (int) (sumWithdrawlAmount ^ (sumWithdrawlAmount >>> 32));
		result = prime * result + ((withdrawlCurrency == null) ? 0 : withdrawlCurrency.hashCode());
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
		WithdrawlAccount other = (WithdrawlAccount) obj;
		if (sumWithdrawlAmount != other.sumWithdrawlAmount)
			return false;
		if (withdrawlCurrency == null) {
			if (other.withdrawlCurrency != null)
				return false;
		} else if (!withdrawlCurrency.equals(other.withdrawlCurrency))
			return false;
		return true;
	}

	
}
