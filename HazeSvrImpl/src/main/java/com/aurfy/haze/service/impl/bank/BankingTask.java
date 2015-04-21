package com.aurfy.haze.service.impl.bank;

import com.aurfy.haze.service.impl.bank.handler.BankHandler;

/**
 * An element that refers to a banking request and related information.
 * 
 * @author hermano
 *
 */
public class BankingTask {

	private TxnReference txnReference;
	private BankHandler handler;

	public BankingTask() {
	}

	public TxnReference getTxnReference() {
		return txnReference;
	}

	public void setTxnReference(TxnReference txnReference) {
		this.txnReference = txnReference;
	}

	public BankHandler getHandler() {
		return handler;
	}

	public void setHandler(BankHandler handler) {
		this.handler = handler;
	}

}
