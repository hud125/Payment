package com.aurfy.haze.core.model.txn;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import com.aurfy.haze.core.model.Currency;

public class BankOrder implements Serializable {

	public BankOrder() {
	}

	private String txtType;// 银行交易类型，有银行定，故当银行不同时，内容一般不同
	private String acqCode;// 收单号
	private String merId;
	private String subMerId;
	private String mcc;
	private String orderId;
	private String txtDate;// 提交给银行的订单时间
	private String terminalId;
	private String ip;
	private String url;
	private String port;
	private String addressId;
	private Currency currency;
	private BigInteger amount;
	private String msg;
	private BigDecimal exchangeRate;

	public String getTxtType() {
		return txtType;
	}

	public void setTxtType(String txtType) {
		this.txtType = txtType;
	}

	/**
	 * 收单号
	 * 
	 * @return
	 */
	public String getAcqCode() {
		return acqCode;
	}

	public void setAcqCode(String acqCode) {
		this.acqCode = acqCode;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getSubMerId() {
		return subMerId;
	}

	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTxtDate() {
		return txtDate;
	}

	public void setTxtDate(String txtDate) {
		this.txtDate = txtDate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acqCode == null) ? 0 : acqCode.hashCode());
		result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((exchangeRate == null) ? 0 : exchangeRate.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((mcc == null) ? 0 : mcc.hashCode());
		result = prime * result + ((merId == null) ? 0 : merId.hashCode());
		result = prime * result + ((msg == null) ? 0 : msg.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((port == null) ? 0 : port.hashCode());
		result = prime * result + ((subMerId == null) ? 0 : subMerId.hashCode());
		result = prime * result + ((terminalId == null) ? 0 : terminalId.hashCode());
		result = prime * result + ((txtDate == null) ? 0 : txtDate.hashCode());
		result = prime * result + ((txtType == null) ? 0 : txtType.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		BankOrder other = (BankOrder) obj;
		if (acqCode == null) {
			if (other.acqCode != null)
				return false;
		} else if (!acqCode.equals(other.acqCode))
			return false;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (exchangeRate == null) {
			if (other.exchangeRate != null)
				return false;
		} else if (!exchangeRate.equals(other.exchangeRate))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (mcc == null) {
			if (other.mcc != null)
				return false;
		} else if (!mcc.equals(other.mcc))
			return false;
		if (merId == null) {
			if (other.merId != null)
				return false;
		} else if (!merId.equals(other.merId))
			return false;
		if (msg == null) {
			if (other.msg != null)
				return false;
		} else if (!msg.equals(other.msg))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		if (subMerId == null) {
			if (other.subMerId != null)
				return false;
		} else if (!subMerId.equals(other.subMerId))
			return false;
		if (terminalId == null) {
			if (other.terminalId != null)
				return false;
		} else if (!terminalId.equals(other.terminalId))
			return false;
		if (txtDate == null) {
			if (other.txtDate != null)
				return false;
		} else if (!txtDate.equals(other.txtDate))
			return false;
		if (txtType == null) {
			if (other.txtType != null)
				return false;
		} else if (!txtType.equals(other.txtType))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankOrder [txtType=");
		builder.append(txtType);
		builder.append(", acqCode=");
		builder.append(acqCode);
		builder.append(", merId=");
		builder.append(merId);
		builder.append(", subMerId=");
		builder.append(subMerId);
		builder.append(", mcc=");
		builder.append(mcc);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", txtDate=");
		builder.append(txtDate);
		builder.append(", terminalId=");
		builder.append(terminalId);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", url=");
		builder.append(url);
		builder.append(", port=");
		builder.append(port);
		builder.append(", addressId=");
		builder.append(addressId);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", msg=");
		builder.append(msg);
		builder.append(", exchangeRate=");
		builder.append(exchangeRate);
		builder.append("]");
		return builder.toString();
	}

}
