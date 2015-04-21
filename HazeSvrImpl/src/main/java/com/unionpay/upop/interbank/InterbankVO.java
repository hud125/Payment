package com.unionpay.upop.interbank;

import java.util.Date;

public class InterbankVO {

	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 银行卡号
	 */
	private String cardNumber;
	/**
	 * 返回码 00 无卡 p1 小额临时
	 */
	private String respCode;
	/**
	 * 交易transNumber
	 */
	private String transNumber;
	/**
	 * 前台通知URL
	 */
	private String frontUrl;
	/**
	 * 后台通知URL
	 */
	private String backUrl;
	/**
	 * 支付方式
	 */
	private String payMethod;
	/**
	 * 发卡行证书id
	 */
	private String certNumber;
	
	private int userId = 0;
	
	private String qid;
	
	private Integer certNumerMappingIndex = null;
	
	private Date purchaseDate;
	
	public InterbankVO(String cardNumber){
		this.cardNumber = cardNumber;
	}
	
	public InterbankVO(){
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getTransNumber() {
		return transNumber;
	}

	public void setTransNumber(String transNumber) {
		this.transNumber = transNumber;
	}

	public String getFrontUrl() {
		return frontUrl;
	}

	public void setFrontUrl(String frontUrl) {
		this.frontUrl = frontUrl;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public String getCertNumber() {
		return certNumber;
	}

	public void setCertNumber(String certNumber) {
		this.certNumber = certNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}
	
	public Integer getCertNumerMappingIndex() {
		return certNumerMappingIndex;
	}

	public void setCertNumerMappingIndex(Integer certNumerMappingIndex) {
		this.certNumerMappingIndex = certNumerMappingIndex;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((backUrl == null) ? 0 : backUrl.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((certNumber == null) ? 0 : certNumber.hashCode());
		result = prime * result + ((certNumerMappingIndex == null) ? 0 : certNumerMappingIndex.hashCode());
		result = prime * result + ((frontUrl == null) ? 0 : frontUrl.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((payMethod == null) ? 0 : payMethod.hashCode());
		result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
		result = prime * result + ((qid == null) ? 0 : qid.hashCode());
		result = prime * result + ((respCode == null) ? 0 : respCode.hashCode());
		result = prime * result + ((transNumber == null) ? 0 : transNumber.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterbankVO other = (InterbankVO) obj;
		if (backUrl == null) {
			if (other.backUrl != null)
				return false;
		} else if (!backUrl.equals(other.backUrl))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (certNumber == null) {
			if (other.certNumber != null)
				return false;
		} else if (!certNumber.equals(other.certNumber))
			return false;
		if (certNumerMappingIndex == null) {
			if (other.certNumerMappingIndex != null)
				return false;
		} else if (!certNumerMappingIndex.equals(other.certNumerMappingIndex))
			return false;
		if (frontUrl == null) {
			if (other.frontUrl != null)
				return false;
		} else if (!frontUrl.equals(other.frontUrl))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (payMethod != other.payMethod)
			return false;
		if (purchaseDate == null) {
			if (other.purchaseDate != null)
				return false;
		} else if (!purchaseDate.equals(other.purchaseDate))
			return false;
		if (qid == null) {
			if (other.qid != null)
				return false;
		} else if (!qid.equals(other.qid))
			return false;
		if (respCode == null) {
			if (other.respCode != null)
				return false;
		} else if (!respCode.equals(other.respCode))
			return false;
		if (transNumber == null) {
			if (other.transNumber != null)
				return false;
		} else if (!transNumber.equals(other.transNumber))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InterbankVO [mobile=" + mobile + ", cardNumber=" + cardNumber + ", respCode=" + respCode
				+ ", transNumber=" + transNumber + ", frontUrl=" + frontUrl + ", backUrl=" + backUrl + ", payMethod="
				+ payMethod + ", certNumber=" + certNumber + ", userId=" + userId + ", qid=" + qid
				+ ", certNumerMappingIndex=" + certNumerMappingIndex + ", purchaseDate=" + purchaseDate + "]";
	}

	
}