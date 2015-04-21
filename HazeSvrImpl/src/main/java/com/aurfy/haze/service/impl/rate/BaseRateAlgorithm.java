package com.aurfy.haze.service.impl.rate;

import java.math.BigDecimal;

import com.aurfy.haze.core.model.rate.RateAlgorithm;
import com.aurfy.haze.core.model.rate.RateBatchSize;

public abstract class BaseRateAlgorithm implements RateAlgorithm {

	private RateBatchSize batchSize;
	private BigDecimal minimum;
	private BigDecimal maximum;

	public BaseRateAlgorithm(RateBatchSize batchSize) {
		this.batchSize = batchSize;
	}

	@Override
	public RateBatchSize getBatchSize() {
		return this.batchSize;
	}

	protected BigDecimal getMinAmount() {
		return minimum;
	}

	@Override
	public void setMinAmount(BigDecimal minimum) {
		this.minimum = minimum;
	}

	protected BigDecimal getMaxAmount() {
		return maximum;
	}

	@Override
	public void setMaxAmount(BigDecimal maximum) {
		this.maximum = maximum;
	}

	@Override
	public BigDecimal calculate(BigDecimal input) {
		if (input == null) {
			throw new IllegalArgumentException("input parameter can not be null");
		}
		BigDecimal price = calculateInternal(input);
		if (getMinAmount() != null) {
			price = price.max(getMinAmount());
		}
		if (getMaxAmount() != null) {
			price = price.min(getMinAmount());
		}
		return price;
	}

	/**
	 * calculate by the internal algorithm, does not need to care about min/max value.
	 * 
	 * @return
	 */
	protected abstract BigDecimal calculateInternal(BigDecimal input);

}
