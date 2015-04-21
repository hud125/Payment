package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.TextualIDModel;

import java.math.BigDecimal;

public class ExchangeRate extends TextualIDModel{
	
	private String exchangeName;
	private Currency fromCurrency;
	private Currency toCurrency;
	private BigDecimal buyRate;
	private BigDecimal cashBuyRate;
	private BigDecimal sellRate;
	private BigDecimal cashSellRate;
	private boolean active;
	private String comments;
	private String ownerId;
	

	
	public String getExchangeName() {
		return exchangeName;
	}
	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	public Currency getFromCurrency() {
		return fromCurrency;
	}
	public void setFromCurrency(Currency fromCurrency) {
		this.fromCurrency = fromCurrency;
	}
	public Currency getToCurrency() {
		return toCurrency;
	}
	public void setToCurrency(Currency toCurrency) {
		this.toCurrency = toCurrency;
	}
	public BigDecimal getBuyRate() {
		return buyRate;
	}
	public void setBuyRate(BigDecimal buyRate) {
		this.buyRate = buyRate;
	}
	public BigDecimal getCashBuyRate() {
		return cashBuyRate;
	}
	public void setCashBuyRate(BigDecimal cashBuyRate) {
		this.cashBuyRate = cashBuyRate;
	}
	public BigDecimal getSellRate() {
		return sellRate;
	}
	public void setSellRate(BigDecimal sellRate) {
		this.sellRate = sellRate;
	}
	public BigDecimal getCashSellRate() {
		return cashSellRate;
	}
	public void setCashSellRate(BigDecimal cashSellRate) {
		this.cashSellRate = cashSellRate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
		builder.append(getClass().getSimpleName());
		builder.append(" [exchangeName=");
		builder.append(exchangeName);
		builder.append(", fromCurrency=");
		builder.append(fromCurrency);
		builder.append(", name=");
		builder.append(fromCurrency);
		builder.append(", toCurrency=");
		builder.append(toCurrency);
		builder.append(", buyRate=");
		builder.append(buyRate);
		builder.append(", cashBuyRate=");
		builder.append(cashBuyRate);
		builder.append(", sellRate=");
		builder.append(sellRate);
		builder.append(", cashSellRate=");
		builder.append(cashSellRate);
		builder.append(", active=");
		builder.append(active);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", owner=");
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
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((buyRate == null) ? 0 : buyRate.hashCode());
		result = prime * result
				+ ((cashBuyRate == null) ? 0 : cashBuyRate.hashCode());
		result = prime * result
				+ ((cashSellRate == null) ? 0 : cashSellRate.hashCode());
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result
				+ ((exchangeName == null) ? 0 : exchangeName.hashCode());
		result = prime * result
				+ ((fromCurrency == null) ? 0 : fromCurrency.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result
				+ ((sellRate == null) ? 0 : sellRate.hashCode());
		result = prime * result
				+ ((toCurrency == null) ? 0 : toCurrency.hashCode());
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
		ExchangeRate other = (ExchangeRate) obj;
		if (active != other.active)
			return false;
		if (buyRate == null) {
			if (other.buyRate != null)
				return false;
		} else if (!buyRate.equals(other.buyRate))
			return false;
		if (cashBuyRate == null) {
			if (other.cashBuyRate != null)
				return false;
		} else if (!cashBuyRate.equals(other.cashBuyRate))
			return false;
		if (cashSellRate == null) {
			if (other.cashSellRate != null)
				return false;
		} else if (!cashSellRate.equals(other.cashSellRate))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (exchangeName == null) {
			if (other.exchangeName != null)
				return false;
		} else if (!exchangeName.equals(other.exchangeName))
			return false;
		if (fromCurrency == null) {
			if (other.fromCurrency != null)
				return false;
		} else if (!fromCurrency.equals(other.fromCurrency))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (sellRate == null) {
			if (other.sellRate != null)
				return false;
		} else if (!sellRate.equals(other.sellRate))
			return false;
		if (toCurrency == null) {
			if (other.toCurrency != null)
				return false;
		} else if (!toCurrency.equals(other.toCurrency))
			return false;
		return true;
	}
	
}
	

