package com.aurfy.haze.entity.configuration.rate;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.rate.RateConfig;
import com.aurfy.haze.core.model.configuration.rate.SteppedRate;
import com.aurfy.haze.entity.Entity;

@Alias("SteppedRateRelEntity")
public class SteppedRateRelEntity implements Entity {
	private RateConfig rateConfig;
	private SteppedRate steppedRate;

	public RateConfig getRateConfig() {
		return rateConfig;
	}

	public void setRateConfig(RateConfig rateConfig) {
		this.rateConfig = rateConfig;
	}

	public SteppedRate getSteppedRate() {
		return steppedRate;
	}

	public void setSteppedRate(SteppedRate steppedRate) {
		this.steppedRate = steppedRate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SteppedRateRelEntity [rateConfig=");
		builder.append(rateConfig);
		builder.append(", steppedRate=");
		builder.append(steppedRate);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rateConfig == null) ? 0 : rateConfig.hashCode());
		result = prime * result + ((steppedRate == null) ? 0 : steppedRate.hashCode());
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
		SteppedRateRelEntity other = (SteppedRateRelEntity) obj;
		if (rateConfig == null) {
			if (other.rateConfig != null)
				return false;
		} else if (!rateConfig.equals(other.rateConfig))
			return false;
		if (steppedRate == null) {
			if (other.steppedRate != null)
				return false;
		} else if (!steppedRate.equals(other.steppedRate))
			return false;
		return true;
	}

}
