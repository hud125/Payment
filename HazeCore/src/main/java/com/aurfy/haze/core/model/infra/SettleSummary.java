package com.aurfy.haze.core.model.infra;

import java.util.Date;

import com.aurfy.haze.core.model.TextualIDModel;

public class SettleSummary extends TextualIDModel {

	private String scheduleBatchID;
	
	private Date settleDate;
	
	private String merchantID;
	
	private String merSubID;
	
	private String settleCurrency;
	
	private Long incomeAmount;
	
	private Long operationAmount;
	
	private Long depositAmount;
	
	private Long returnDepositAmount;
	
	private Long freezeAmount;
	
	private Long unfreezeAmount;
	
	private Long balanceAmount;
	
	private String comments;

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

	public Long getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(Long incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public Long getOperationAmount() {
		return operationAmount;
	}

	public void setOperationAmount(Long operationAmount) {
		this.operationAmount = operationAmount;
	}

	public Long getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Long depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Long getReturnDepositAmount() {
		return returnDepositAmount;
	}

	public void setReturnDepositAmount(Long returnDepositAmount) {
		this.returnDepositAmount = returnDepositAmount;
	}

	public Long getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(Long freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public Long getUnfreezeAmount() {
		return unfreezeAmount;
	}

	public void setUnfreezeAmount(Long unfreezeAmount) {
		this.unfreezeAmount = unfreezeAmount;
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
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((depositAmount == null) ? 0 : depositAmount.hashCode());
		result = prime * result + ((freezeAmount == null) ? 0 : freezeAmount.hashCode());
		result = prime * result + ((incomeAmount == null) ? 0 : incomeAmount.hashCode());
		result = prime * result + ((merSubID == null) ? 0 : merSubID.hashCode());
		result = prime * result + ((merchantID == null) ? 0 : merchantID.hashCode());
		result = prime * result + ((operationAmount == null) ? 0 : operationAmount.hashCode());
		result = prime * result + ((returnDepositAmount == null) ? 0 : returnDepositAmount.hashCode());
		result = prime * result + ((scheduleBatchID == null) ? 0 : scheduleBatchID.hashCode());
		result = prime * result + ((settleCurrency == null) ? 0 : settleCurrency.hashCode());
		result = prime * result + ((settleDate == null) ? 0 : settleDate.hashCode());
		result = prime * result + ((unfreezeAmount == null) ? 0 : unfreezeAmount.hashCode());
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
		SettleSummary other = (SettleSummary) obj;
		if (balanceAmount == null) {
			if (other.balanceAmount != null)
				return false;
		} else if (!balanceAmount.equals(other.balanceAmount))
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
		if (freezeAmount == null) {
			if (other.freezeAmount != null)
				return false;
		} else if (!freezeAmount.equals(other.freezeAmount))
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
		if (operationAmount == null) {
			if (other.operationAmount != null)
				return false;
		} else if (!operationAmount.equals(other.operationAmount))
			return false;
		if (returnDepositAmount == null) {
			if (other.returnDepositAmount != null)
				return false;
		} else if (!returnDepositAmount.equals(other.returnDepositAmount))
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
		if (unfreezeAmount == null) {
			if (other.unfreezeAmount != null)
				return false;
		} else if (!unfreezeAmount.equals(other.unfreezeAmount))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SettleSummary [scheduleBatchID=");
		builder.append(scheduleBatchID);
		builder.append(", settleDate=");
		builder.append(settleDate);
		builder.append(", merchantID=");
		builder.append(merchantID);
		builder.append(", merSubID=");
		builder.append(merSubID);
		builder.append(", settleCurrency=");
		builder.append(settleCurrency);
		builder.append(", incomeAmount=");
		builder.append(incomeAmount);
		builder.append(", operationAmount=");
		builder.append(operationAmount);
		builder.append(", depositAmount=");
		builder.append(depositAmount);
		builder.append(", returnDepositAmount=");
		builder.append(returnDepositAmount);
		builder.append(", freezeAmount=");
		builder.append(freezeAmount);
		builder.append(", unfreezeAmount=");
		builder.append(unfreezeAmount);
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
