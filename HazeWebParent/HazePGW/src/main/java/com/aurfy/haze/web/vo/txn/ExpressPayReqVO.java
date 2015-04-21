package com.aurfy.haze.web.vo.txn;

import com.aurfy.haze.web.vo.RequestVO;

public class ExpressPayReqVO extends RequestVO {

	private String merCode;

	private String currency;
	private String amount;
	private String orderId;
	private String billDesc;// 账单描述，出现在页面及持卡人账单上的描述性文字
	private String orderDetail;// 订单包含的详细项目（商品信息等）
	private String orderDate;
	private String orderTimezone;// TODO: convert to TimeZone if necessary
	private String orderTimeout;// 订单超时时间（单位：秒）：主要用于防钓鱼，防止replay攻击
	private String customerIp;
	private String payTimeout;
	private String preferredBank;// 默认支付方式
	private String browserNotifyUrl;// 通知URL
	private String serverNotifyUrl;// 返回URL

	private String terminalId;
	private String virtualAccount;
	private String cardOrg;
	private String token;
	private String cardNo;
	private String encryptedCardNo;
	private String maskCardNo;
	private String expiryDate;
	private String cellphone;
	private String credentialType;
	private String credentialNo;
	private String cardHolderFullName;
	private String cardHolderFirstName;
	private String cardHolderMiddleName;
	private String cardHolderLastName;
	private String cardType;
	private String cvv;
	private String pin;

	public String getMerCode() {
		return merCode;
	}

	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBillDesc() {
		return billDesc;
	}

	public void setBillDesc(String billDesc) {
		this.billDesc = billDesc;
	}

	public String getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public String getOrderTimezone() {
		return orderTimezone;
	}

	public void setOrderTimezone(String orderTimezone) {
		this.orderTimezone = orderTimezone;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public String getPreferredBank() {
		return preferredBank;
	}

	public void setPreferredBank(String preferredBank) {
		this.preferredBank = preferredBank;
	}

	public String getBrowserNotifyUrl() {
		return browserNotifyUrl;
	}

	public void setBrowserNotifyUrl(String browserNotifyUrl) {
		this.browserNotifyUrl = browserNotifyUrl;
	}

	public String getServerNotifyUrl() {
		return serverNotifyUrl;
	}

	public void setServerNotifyUrl(String serverNotifyUrl) {
		this.serverNotifyUrl = serverNotifyUrl;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public void setOrderTimeout(String orderTimeout) {
		this.orderTimeout = orderTimeout;
	}

	public void setPayTimeout(String payTimeout) {
		this.payTimeout = payTimeout;
	}

	public String getCardHolderFullName() {
		return cardHolderFullName;
	}

	public void setCardHolderFullName(String cardHolderFullName) {
		this.cardHolderFullName = cardHolderFullName;
	}

	public String getCardHolderFirstName() {
		return cardHolderFirstName;
	}

	public void setCardHolderFirstName(String cardHolderFirstName) {
		this.cardHolderFirstName = cardHolderFirstName;
	}

	public String getCardHolderMiddleName() {
		return cardHolderMiddleName;
	}

	public void setCardHolderMiddleName(String cardHolderMiddleName) {
		this.cardHolderMiddleName = cardHolderMiddleName;
	}

	public String getCardHolderLastName() {
		return cardHolderLastName;
	}

	public void setCardHolderLastName(String cardHolderLastName) {
		this.cardHolderLastName = cardHolderLastName;
	}

	public String getCurrency() {
		return currency;
	}

	public String getAmount() {
		return amount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getOrderTimeout() {
		return orderTimeout;
	}

	public String getPayTimeout() {
		return payTimeout;
	}

	public String getVirtualAccount() {
		return virtualAccount;
	}

	public void setVirtualAccount(String virtualAccount) {
		this.virtualAccount = virtualAccount;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getEncryptedCardNo() {
		return encryptedCardNo;
	}

	public void setEncryptedCardNo(String encryptedCardNo) {
		this.encryptedCardNo = encryptedCardNo;
	}

	public String getMaskCardNo() {
		return maskCardNo;
	}

	public void setMaskCardNo(String maskCardNo) {
		this.maskCardNo = maskCardNo;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getCardOrg() {
		return cardOrg;
	}

	public void setCardOrg(String cardOrg) {
		this.cardOrg = cardOrg;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

}
