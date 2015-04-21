package com.aurfy.haze.core.model.rate;

import java.math.BigDecimal;

/**
 * 
 * @author hermano
 *
 */
public interface RateAlgorithm {

	/**
	 * <p>
	 * indicate the batch size for this rate algorithm<br />
	 * if it's for single, then processor should parse single record. otherwise, parse whole batch instead.
	 * </p>
	 * 
	 * @return
	 */
	public RateBatchSize getBatchSize();

	/**
	 * Optionally set the minimum value.
	 * 
	 * @param minimum
	 */
	public void setMinAmount(BigDecimal minimum);

	/**
	 * Optionally set the maximum value.
	 * 
	 * @param maximum
	 */
	public void setMaxAmount(BigDecimal maximum);

	/**
	 * calculate the amount using the given input.
	 * 
	 * @param input
	 * @return
	 */
	public BigDecimal calculate(BigDecimal input);
}
