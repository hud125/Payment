package com.aurfy.haze.service.impl.bank;

/**
 * a reference class to the transaction.
 * 
 * @author hermano
 *
 */
public final class TxnReference {

	private String merTxnId;
	private String paySummaryId;
	private String payFlowId;

	public TxnReference() {
	}

	public String getMerTxnId() {
		return merTxnId;
	}

	public void setMerTxnId(String merTxnId) {
		this.merTxnId = merTxnId;
	}

	public String getPaySummaryId() {
		return paySummaryId;
	}

	public void setPaySummaryId(String paySummaryId) {
		this.paySummaryId = paySummaryId;
	}

	public String getPayFlowId() {
		return payFlowId;
	}

	public void setPayFlowId(String payFlowId) {
		this.payFlowId = payFlowId;
	}

}
