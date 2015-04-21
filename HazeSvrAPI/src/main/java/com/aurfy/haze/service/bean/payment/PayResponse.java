package com.aurfy.haze.service.bean.payment;

import java.math.BigInteger;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.payment.PayStatusEnum;
import com.aurfy.haze.core.model.payment.RespCode;

public class PayResponse {

	private String srcTxnId;
	private String paySummaryId;
	private RespCode respCode;
	private BigInteger payAmount;
	private Currency payCurrency;
	private PayStatusEnum status;// 当需要自动或人工处理某些流水时（如需退货等），将此标志位置为特殊条件，方便查询

	public PayResponse() {
		// TODO Auto-generated constructor stub
	}

	public String getSrcTxnId() {
		return srcTxnId;
	}

	public void setSrcTxnId(String srcTxnId) {
		this.srcTxnId = srcTxnId;
	}

	public String getPaySummaryId() {
		return paySummaryId;
	}

	public void setPaySummaryId(String paySummaryId) {
		this.paySummaryId = paySummaryId;
	}

	public RespCode getRespCode() {
		return respCode;
	}

	public void setRespCode(RespCode respCode) {
		this.respCode = respCode;
	}

	public BigInteger getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigInteger payAmount) {
		this.payAmount = payAmount;
	}

	public Currency getPayCurrency() {
		return payCurrency;
	}

	public void setPayCurrency(Currency payCurrency) {
		this.payCurrency = payCurrency;
	}

	public PayStatusEnum getStatus() {
		return status;
	}

	public void setStatus(PayStatusEnum status) {
		this.status = status;
	}

}
