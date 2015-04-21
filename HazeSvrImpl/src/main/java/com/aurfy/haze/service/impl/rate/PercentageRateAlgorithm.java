package com.aurfy.haze.service.impl.rate;

import java.math.BigDecimal;

import com.aurfy.haze.core.model.rate.RateAlgorithm;
import com.aurfy.haze.core.model.rate.RateBatchSize;

public class PercentageRateAlgorithm extends BaseRateAlgorithm implements RateAlgorithm {

	public PercentageRateAlgorithm() {
		super(RateBatchSize.SINGLE);
	}

	@Override
	protected BigDecimal calculateInternal(BigDecimal input) {
		// TODO Auto-generated method stub
		return null;
	}

}
