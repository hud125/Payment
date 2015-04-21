package com.aurfy.haze.service.bean.bank;

import com.aurfy.haze.core.model.payment.PayCredential;
import com.aurfy.haze.core.model.txn.SrcPayOrder;

public class BaseBankRequest implements BankRequest {

	private String srcTxnId;
	private SrcPayOrder payOrder;
	private PayCredential payCredential;

	public BaseBankRequest() {
	}

	public String getSrcTxnId() {
		return srcTxnId;
	}

	public void setSrcTxnId(String srcTxnId) {
		this.srcTxnId = srcTxnId;
	}

	@Override
	public SrcPayOrder getPayOrder() {
		return payOrder;
	}

	public void setPayOrder(SrcPayOrder payOrder) {
		this.payOrder = payOrder;
	}

	@Override
	public PayCredential getPayCredential() {
		return payCredential;
	}

	public void setPayCredential(PayCredential payCredential) {
		this.payCredential = payCredential;
	}

}
