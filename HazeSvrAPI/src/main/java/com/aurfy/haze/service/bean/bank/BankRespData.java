package com.aurfy.haze.service.bean.bank;

public class BankRespData {
	/**
	 * 银行流水号
	 */
	private String qid;
	private String respMsg;

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

}
