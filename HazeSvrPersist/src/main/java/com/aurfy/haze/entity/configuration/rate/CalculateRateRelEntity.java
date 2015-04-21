package com.aurfy.haze.entity.configuration.rate;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.rate.CalculateRate;
import com.aurfy.haze.core.model.configuration.rate.RateConfig;
import com.aurfy.haze.entity.Entity;

@Alias("CalculateRateRelEntity")
public class CalculateRateRelEntity implements Entity {
	private RateConfig rateConfig;
	private CalculateRate calculateRate;

	public RateConfig getRateConfig() {
		return rateConfig;
	}

	public void setRateConfig(RateConfig rateConfig) {
		this.rateConfig = rateConfig;
	}

	public CalculateRate getCalculateRate() {
		return calculateRate;
	}

	public void setCalculateRate(CalculateRate calculateRate) {
		this.calculateRate = calculateRate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CalRateRelEntity [rateConfig=");
		builder.append(rateConfig);
		builder.append(", calculateRate=");
		builder.append(calculateRate);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calculateRate == null) ? 0 : calculateRate.hashCode());
		result = prime * result + ((rateConfig == null) ? 0 : rateConfig.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalculateRateRelEntity other = (CalculateRateRelEntity) obj;
		if (calculateRate == null) {
			if (other.calculateRate != null)
				return false;
		} else if (!calculateRate.equals(other.calculateRate))
			return false;
		if (rateConfig == null) {
			if (other.rateConfig != null)
				return false;
		} else if (!rateConfig.equals(other.rateConfig))
			return false;
		return true;
	}

	
}
