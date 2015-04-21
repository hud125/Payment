package com.aurfy.haze.core.model.settlement;

import java.math.BigDecimal;

import com.aurfy.haze.core.model.TextualIDModel;

public class MerSettleConfig extends TextualIDModel {

	private String merId;
	private String subMerID;
	private String settleCurrencies;
	private SettleConditionEnum settleCondition;
	private int settlePeriodDay;
	private int maxDeliveryDay;
	private ReconciliationDiffPolicyEnum reconciliationDiffPolicy;
	private BigDecimal depositRate;
	private FreezePolicyEnum freezePolicy;

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getSubMerID() {
		return subMerID;
	}

	public void setSubMerID(String subMerID) {
		this.subMerID = subMerID;
	}

	/**
	 * 商户清算币种，可考虑支持多币种
	 * 
	 * @return
	 */
	public String getSettleCurrencies() {
		return settleCurrencies;
	}

	public void setSettleCurrencies(String settleCurrencies) {
		this.settleCurrencies = settleCurrencies;
	}

	public SettleConditionEnum getSettleCondition() {
		return settleCondition;
	}

	public void setSettleCondition(SettleConditionEnum settleCondition) {
		this.settleCondition = settleCondition;
	}

	/**
	 * 周期结算的日期，举例：通常为T+7中的7
	 * 
	 */
	public int getSettlePeriodDay() {
		return settlePeriodDay;
	}

	public void setSettlePeriodDay(int settlePeriodDay) {
		this.settlePeriodDay = settlePeriodDay;
	}

	/**
	 * 妥投结算最长超时时间，以天计算
	 * 
	 * @return
	 */
	public int getMaxDeliveryDay() {
		return maxDeliveryDay;
	}

	public void setMaxDeliveryDay(int maxDeliveryDay) {
		this.maxDeliveryDay = maxDeliveryDay;
	}

	/**
	 * 勾兑时针对银行差错账作何处理：1、自动退货；2、自动补单（状态更新为当天成功）
	 * 
	 * @return
	 */
	public ReconciliationDiffPolicyEnum getReconciliationDiffPolicy() {
		return reconciliationDiffPolicy;
	}

	public void setReconciliationDiffPolicy(ReconciliationDiffPolicyEnum reconciliationDiffPolicy) {
		this.reconciliationDiffPolicy = reconciliationDiffPolicy;
	}

	/**
	 * 保证金比例。如需按卡种设定，则挪到Operation Rate表中。
	 * 
	 * @return
	 */
	public BigDecimal getDepositRate() {
		return depositRate;
	}

	public void setDepositRate(BigDecimal depositRate) {
		this.depositRate = depositRate;
	}

	/**
	 * 资金冻结策略（for 退货&拒付）：当日冻结（清算时自动操作）；不自动冻结
	 * 
	 * @return
	 */
	public FreezePolicyEnum getFreezePolicy() {
		return freezePolicy;
	}

	public void setFreezePolicy(FreezePolicyEnum freezePolicy) {
		this.freezePolicy = freezePolicy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MerSettleConfig [merId=");
		builder.append(merId);
		builder.append(", subMerID=");
		builder.append(subMerID);
		builder.append(", settleCurrencies=");
		builder.append(settleCurrencies);
		builder.append(", settleCondition=");
		builder.append(settleCondition);
		builder.append(", settlePeriodDay=");
		builder.append(settlePeriodDay);
		builder.append(", maxDeliveryDay=");
		builder.append(maxDeliveryDay);
		builder.append(", reconciliationDiffPolicy=");
		builder.append(reconciliationDiffPolicy);
		builder.append(", depositRate=");
		builder.append(depositRate);
		builder.append(", freezePolicy=");
		builder.append(freezePolicy);
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
		result = prime * result + ((depositRate == null) ? 0 : depositRate.hashCode());
		result = prime * result + ((freezePolicy == null) ? 0 : freezePolicy.hashCode());
		result = prime * result + maxDeliveryDay;
		result = prime * result + ((merId == null) ? 0 : merId.hashCode());
		result = prime * result + ((reconciliationDiffPolicy == null) ? 0 : reconciliationDiffPolicy.hashCode());
		result = prime * result + ((settleCondition == null) ? 0 : settleCondition.hashCode());
		result = prime * result + ((settleCurrencies == null) ? 0 : settleCurrencies.hashCode());
		result = prime * result + settlePeriodDay;
		result = prime * result + ((subMerID == null) ? 0 : subMerID.hashCode());
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
		MerSettleConfig other = (MerSettleConfig) obj;
		if (depositRate == null) {
			if (other.depositRate != null)
				return false;
		} else if (!depositRate.equals(other.depositRate))
			return false;
		if (freezePolicy != other.freezePolicy)
			return false;
		if (maxDeliveryDay != other.maxDeliveryDay)
			return false;
		if (merId == null) {
			if (other.merId != null)
				return false;
		} else if (!merId.equals(other.merId))
			return false;
		if (reconciliationDiffPolicy != other.reconciliationDiffPolicy)
			return false;
		if (settleCondition != other.settleCondition)
			return false;
		if (settleCurrencies == null) {
			if (other.settleCurrencies != null)
				return false;
		} else if (!settleCurrencies.equals(other.settleCurrencies))
			return false;
		if (settlePeriodDay != other.settlePeriodDay)
			return false;
		if (subMerID == null) {
			if (other.subMerID != null)
				return false;
		} else if (!subMerID.equals(other.subMerID))
			return false;
		return true;
	}

}
