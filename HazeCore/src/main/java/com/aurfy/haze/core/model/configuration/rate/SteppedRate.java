package com.aurfy.haze.core.model.configuration.rate;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.TextualIDModel;

/**
 * 阶梯式扣率：采取全量模式，以币种为单位<br />
 * 可能是for Merchant/Agent/PSP
 * 
 * @author rocket
 *
 */
public class SteppedRate extends TextualIDModel {

	private RateRoleClassifierEnum rateRoleClassifier;// Merchant/Sales/Agent/PSP
	private String targetId;// Merchant/Agent/PSP其中之一的ID
	private Currency currency;// 费率对应币种
	private BigInteger minBound;
	private BigDecimal percentage;// 百分比 比率
	private BigDecimal fixedAmount;// 固定费率部分
	private BigDecimal minAmount;// 最低手续费
	private BigDecimal maxAmount;
	private String comments;
	private String ownerId;

	/**
	 * Merchant/Sales/Agent/PSP
	 * 
	 * @return
	 */
	public RateRoleClassifierEnum getRateRoleClassifier() {
		return rateRoleClassifier;
	}

	public void setRateRoleClassifier(RateRoleClassifierEnum rateRoleClassifier) {
		this.rateRoleClassifier = rateRoleClassifier;
	}

	/**
	 * Merchant/Agent/PSP其中之一的ID
	 * 
	 * @return
	 */
	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	/**
	 * 费率对应币种
	 * 
	 * @return
	 */
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * 最小金额（总数）：当到达此金额时（不超过下一阶段的最小金额），按总交易额*原扣率- 优惠手续费数额，给PSP或Agent、返佣 优惠手续费数额： 1、按百分比算：总交易额*优惠扣率 2、按固定金额：=固定金额
	 * 同时考虑最大最小值
	 * 
	 * @return
	 */
	public BigInteger getMinBound() {
		return minBound;
	}

	public void setMinBound(BigInteger minBound) {
		this.minBound = minBound;
	}

	/**
	 * 百分比 比率
	 * 
	 * @return
	 */
	public BigDecimal getPercentage() {
		return percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	/**
	 * 固定费率部分
	 * 
	 * @return
	 */
	public BigDecimal getFixedAmount() {
		return fixedAmount;
	}

	public void setFixedAmount(BigDecimal fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	/**
	 * 最低手续费
	 * 
	 * @return
	 */
	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	/**
	 * 最高手续费
	 * 
	 * @return
	 */
	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SteppedRate [rateRoleClassifier=");
		builder.append(rateRoleClassifier);
		builder.append(", targetId=");
		builder.append(targetId);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", minBound=");
		builder.append(minBound);
		builder.append(", percentage=");
		builder.append(percentage);
		builder.append(", fixedAmount=");
		builder.append(fixedAmount);
		builder.append(", minAmount=");
		builder.append(minAmount);
		builder.append(", maxAmount=");
		builder.append(maxAmount);
		builder.append(", comments=");
		builder.append(comments);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((fixedAmount == null) ? 0 : fixedAmount.hashCode());
		result = prime * result + ((maxAmount == null) ? 0 : maxAmount.hashCode());
		result = prime * result + ((minAmount == null) ? 0 : minAmount.hashCode());
		result = prime * result + ((minBound == null) ? 0 : minBound.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((percentage == null) ? 0 : percentage.hashCode());
		result = prime * result + ((rateRoleClassifier == null) ? 0 : rateRoleClassifier.hashCode());
		result = prime * result + ((targetId == null) ? 0 : targetId.hashCode());
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
		SteppedRate other = (SteppedRate) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (fixedAmount == null) {
			if (other.fixedAmount != null)
				return false;
		} else if (!fixedAmount.equals(other.fixedAmount))
			return false;
		if (maxAmount == null) {
			if (other.maxAmount != null)
				return false;
		} else if (!maxAmount.equals(other.maxAmount))
			return false;
		if (minAmount == null) {
			if (other.minAmount != null)
				return false;
		} else if (!minAmount.equals(other.minAmount))
			return false;
		if (minBound == null) {
			if (other.minBound != null)
				return false;
		} else if (!minBound.equals(other.minBound))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (percentage == null) {
			if (other.percentage != null)
				return false;
		} else if (!percentage.equals(other.percentage))
			return false;
		if (rateRoleClassifier != other.rateRoleClassifier)
			return false;
		if (targetId == null) {
			if (other.targetId != null)
				return false;
		} else if (!targetId.equals(other.targetId))
			return false;
		return true;
	}

}
