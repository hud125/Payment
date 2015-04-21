package com.aurfy.haze.core.model.risk;

import java.math.BigDecimal;

import com.aurfy.haze.core.model.Currency;

public class RiskRequest {

	private String merId;
	private BigDecimal amount;
	private Currency currency;
	private String cardNumber;
	private String binRange;
	private String ipAddress;

}
