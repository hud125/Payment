package com.aurfy.haze.core.model.configuration.rate;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;

/**
 * 商户/销售/代理商/PSP PSP是指payment service provider，指可以提供支付网关。<br />
 * 
 * A payment service provider (PSP) offers shops online services for accepting electronic payments by a variety of
 * payment methods including credit card, bank-based payments such as direct debit, bank transfer, and real-time bank
 * transfer based on online banking.
 * 
 * @author rocket
 *
 */
@UseEnumTypeHandler
public enum RateRoleClassifierEnum {

	MERCHANT,

	SALE,

	AGENT,

	PSP

}
