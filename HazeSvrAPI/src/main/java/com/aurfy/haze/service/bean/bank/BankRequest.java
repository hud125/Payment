package com.aurfy.haze.service.bean.bank;

import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.txn.SrcPayOrder;

/**
 * all requests that send to the bank via channel.
 * 
 * @author hermano
 *
 */
public interface BankRequest {
	
	//no bank order yet

	public SrcPayOrder getPayOrder();

	public PayCredential getPayCredential();

}
