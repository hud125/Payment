package com.aurfy.haze.core.model.configuration.rate;

import java.math.BigDecimal;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.rate.RateAlgorithm;

/**
 * 费率算法，可用于商户交易、购结汇、转账提款等。<br />
 * 主要维度：卡种，币种，费率类型，终端类型（电脑，移动端等）
 * 
 * @author rocket
 *
 */
public class CalculateRate extends TextualIDModel {

	private CardOrgEnum cardOrg;// 卡组织，可以为空，代表*
	private Currency currency;// 费率对应币种
	private RateClassifierEnum rateClassifier;// 费率类型，包括：消费、退款、拒付、调查单；购汇、结汇、提款、转账
	private RateAlgorithmEnum rateAlgorithm;// 计费算法：按百分比，按固定金额，按百分比+固定金额（退货时不退固定金额部分），按阶梯等
	private BigDecimal percentage;// 百分比 比率
	private BigDecimal fixedAmount;// 固定费率部分
	private BigDecimal minAmount;// 最低手续费
	private BigDecimal maxAmount;
	private String extend1;// 预留字段
	private String extend2;
	private String extend3;
	private String comments;
	private String ownerId;

	/**
	 * 卡组织，可以为空，代表*
	 * 
	 * @return
	 */
	public CardOrgEnum getCardOrg() {
		return cardOrg;
	}

	public void setCardOrg(CardOrgEnum cardOrg) {
		this.cardOrg = cardOrg;
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
	 * 费率类型，包括：消费、退款、拒付、调查单；购汇、结汇、提款、转账
	 * 
	 * @return
	 */
	public RateClassifierEnum getRateClassifier() {
		return rateClassifier;
	}

	public void setRateClassifier(RateClassifierEnum rateClassifier) {
		this.rateClassifier = rateClassifier;
	}

	/**
	 * 计费算法：按百分比，按固定金额，按百分比+固定金额（退货时不退固定金额部分），按阶梯等
	 * 
	 * @return
	 */
	public RateAlgorithmEnum getRateAlgorithm() {
		return rateAlgorithm;
	}

	public void setRateAlgorithm(RateAlgorithmEnum rateAlgorithm) {
		this.rateAlgorithm = rateAlgorithm;
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

	public BigDecimal getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	/**
	 * 预留字段
	 * 
	 * @return
	 */
	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	/**
	 * 预留字段
	 * 
	 * @return
	 */
	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

	/**
	 * 预留字段
	 * 
	 * @return
	 */
	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3;
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
		builder.append("CalculateRate [cardOrg=");
		builder.append(cardOrg);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", rateClassifier=");
		builder.append(rateClassifier);
		builder.append(", rateAlgorithm=");
		builder.append(rateAlgorithm);
		builder.append(", percentage=");
		builder.append(percentage);
		builder.append(", fixedAmount=");
		builder.append(fixedAmount);
		builder.append(", minAmount=");
		builder.append(minAmount);
		builder.append(", maxAmount=");
		builder.append(maxAmount);
		builder.append(", extend1=");
		builder.append(extend1);
		builder.append(", extend2=");
		builder.append(extend2);
		builder.append(", extend3=");
		builder.append(extend3);
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
		result = prime * result + ((cardOrg == null) ? 0 : cardOrg.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((extend1 == null) ? 0 : extend1.hashCode());
		result = prime * result + ((extend2 == null) ? 0 : extend2.hashCode());
		result = prime * result + ((extend3 == null) ? 0 : extend3.hashCode());
		result = prime * result + ((fixedAmount == null) ? 0 : fixedAmount.hashCode());
		result = prime * result + ((maxAmount == null) ? 0 : maxAmount.hashCode());
		result = prime * result + ((minAmount == null) ? 0 : minAmount.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((percentage == null) ? 0 : percentage.hashCode());
		result = prime * result + ((rateAlgorithm == null) ? 0 : rateAlgorithm.hashCode());
		result = prime * result + ((rateClassifier == null) ? 0 : rateClassifier.hashCode());
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
		CalculateRate other = (CalculateRate) obj;
		if (cardOrg != other.cardOrg)
			return false;
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
		if (extend1 == null) {
			if (other.extend1 != null)
				return false;
		} else if (!extend1.equals(other.extend1))
			return false;
		if (extend2 == null) {
			if (other.extend2 != null)
				return false;
		} else if (!extend2.equals(other.extend2))
			return false;
		if (extend3 == null) {
			if (other.extend3 != null)
				return false;
		} else if (!extend3.equals(other.extend3))
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
		if (rateAlgorithm != other.rateAlgorithm)
			return false;
		if (rateClassifier != other.rateClassifier)
			return false;
		return true;
	}

}
