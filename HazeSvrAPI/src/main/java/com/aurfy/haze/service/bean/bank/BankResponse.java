package com.aurfy.haze.service.bean.bank;

import com.aurfy.haze.core.model.payment.PayStatusEnum;
import com.aurfy.haze.core.model.payment.RespCode;
import com.aurfy.haze.core.model.txn.BankOrder;

public interface BankResponse {

	public PayStatusEnum getStatus();
	
	public BankOrder getBankOrder();

	public RespCode getRespCode();

	public BankRespData getBankRespData();

}
