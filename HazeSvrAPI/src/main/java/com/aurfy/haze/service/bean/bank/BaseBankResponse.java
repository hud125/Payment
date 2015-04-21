package com.aurfy.haze.service.bean.bank;

import com.aurfy.haze.core.model.payment.PayStatusEnum;
import com.aurfy.haze.core.model.payment.RespCode;
import com.aurfy.haze.core.model.txn.BankOrder;

public abstract class BaseBankResponse implements BankResponse {

	private BankOrder bankOrder;
	private RespCode respCode;
	private BankRespData bankRespData;
	private PayStatusEnum status;

	public BaseBankResponse() {
	}

	@Override
	public PayStatusEnum getStatus() {
		return status;
	}

	public void setStatus(PayStatusEnum status) {
		this.status = status;
	}

	@Override
	public BankOrder getBankOrder() {
		return bankOrder;
	}

	public void setBankOrder(BankOrder bankOrder) {
		this.bankOrder = bankOrder;
	}

	@Override
	public RespCode getRespCode() {
		return respCode;
	}

	public void setRespCode(RespCode respCode) {
		this.respCode = respCode;
	}

	@Override
	public BankRespData getBankRespData() {
		return bankRespData;
	}

	public void setBankRespData(BankRespData bankRespData) {
		this.bankRespData = bankRespData;
	}

}
