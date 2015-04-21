package com.aurfy.haze.service.impl.rate;

import java.math.BigDecimal;

import com.aurfy.haze.core.model.rate.RateAlgorithm;
import com.aurfy.haze.core.model.rate.RateBatchSize;

public class AnnuallyRateAlgorithm extends BaseRateAlgorithm implements RateAlgorithm {

	public AnnuallyRateAlgorithm() {
		super(RateBatchSize.MULTIPLE);
	}

	@Override
	protected BigDecimal calculateInternal(BigDecimal input) {
		// TODO Auto-generated method stub
		return null;
	}
}
