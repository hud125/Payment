package com.aurfy.haze.core.model.configuration.rate;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

@UseEnumTypeHandler
public enum RateClassifierEnum {
	/**
	 * 费率类型，包括：消费、退款、拒付、调查单；购汇、结汇、提款、转账
	 * 
	 * 不知英语表达
	 * 
	 */
	
	CONSUME,
	
	REFUND,
	
	REFUSE,
	
	SURVEY,
	
	etc
	
}
