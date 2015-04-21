package com.aurfy.haze.entity.configuration.rate;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.configuration.rate.CalculateRate;
import com.aurfy.haze.core.model.configuration.rate.RateConfig;
import com.aurfy.haze.core.model.configuration.rate.SteppedRate;
import com.aurfy.haze.entity.Entity;

@Alias("RateConfigEntity")
public class RateConfigEntity extends RateConfig implements Entity {

	private List<CalculateRateEntity> calculateRates;
	private List<SteppedRateEntity> steppedRates;

	public List<CalculateRateEntity> getCalculateRates() {
		return calculateRates;
	}

	public void setCalculateRates(List<CalculateRateEntity> calculateRates) {
		this.calculateRates = calculateRates;
	}

	public List<SteppedRateEntity> getSteppedRates() {
		return steppedRates;
	}

	public void setSteppedRates(List<SteppedRateEntity> steppedRates) {
		this.steppedRates = steppedRates;
	}

}
