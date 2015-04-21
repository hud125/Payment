package com.aurfy.haze.core.model.txn;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.infra.mer.MerchantReference;

/**
 * 
 * @author rocket
 *
 */
public class MerchantOrder implements Serializable {

	public MerchantOrder() {
	}

	private MerchantReference merRef;
	private TxnTypeEnum txnType;
	private Currency currency;
	private BigInteger amount;
	private String orderId;
	private String billDesc;//账单描述，出现在页面及持卡人账单上的描述性文字
	private String orderDetail;//订单包含的详细项目（商品信息等）
	private Date orderDate;
	private String orderTimezone;// TODO: convert to TimeZone if necessary
	private int orderTimeout;//订单超时时间（单位：秒）：主要用于防钓鱼，防止replay攻击
	private String customerIp;
	private int payTimeout;
	private String preferredBank;//默认支付方式
	private String browserNotifyUrl;//通知URL
	private String serverNotifyUrl;//返回URL

	public MerchantReference getMerRef() {
		return merRef;
	}

	public void setMerRef(MerchantReference merRef) {
		this.merRef = merRef;
	}

	public TxnTypeEnum getTxnType() {
		return txnType;
	}

	public void setTxnType(TxnTypeEnum txnType) {
		this.txnType = txnType;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigInteger getAmount() {
		return amount;
	}

	public void setAmount(BigInteger amount) {
		this.amount = amount;
	}

	/**
	 * 商户侧订单号，只允许字母数字，不允许特殊字符，及下划线、减号等
	 * 
	 * @return
	 */
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 账单描述，出现在页面及持卡人账单上的描述性文字
	 * 
	 * @return
	 */
	public String getBillDesc() {
		return billDesc;
	}

	public void setBillDesc(String billDesc) {
		this.billDesc = billDesc;
	}

	/**
	 * 订单包含的详细项目（商品信息等）
	 * 
	 * @return
	 */
	public String getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderTimezone() {
		return orderTimezone;
	}

	public void setOrderTimezone(String orderTimezone) {
		this.orderTimezone = orderTimezone;
	}

	/**
	 * 订单超时时间（单位：秒）：主要用于防钓鱼，防止replay攻击
	 * 
	 * @return
	 */
	public int getOrderTimeout() {
		return orderTimeout;
	}

	public void setOrderTimeout(int orderTimeout) {
		this.orderTimeout = orderTimeout;
	}

	/**
	 * 商户端上送的客户端IP，主要用于防钓鱼
	 * 
	 * @return
	 */
	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	/**
	 * 最后支付的超时时间：适用于抢票等场景
	 * 
	 * @return
	 */
	public int getPayTimeout() {
		return payTimeout;
	}

	public void setPayTimeout(int payTimeout) {
		this.payTimeout = payTimeout;
	}

	/**
	 * 商户首选银行编码：适用于网银跳转等情况。同“默认支付方式”
	 * 
	 * @return
	 */
	public String getPreferredBank() {
		return preferredBank;
	}

	public void setPreferredBank(String preferredBank) {
		this.preferredBank = preferredBank;
	}
	
	/**
	 * 通知URL
	 * @return
	 */
	public String getBrowserNotifyUrl() {
		return browserNotifyUrl;
	}

	public void setBrowserNotifyUrl(String browserNotifyUrl) {
		this.browserNotifyUrl = browserNotifyUrl;
	}

	/**
	 * 返回URL
	 * @return
	 */
	public String getServerNotifyUrl() {
		return serverNotifyUrl;
	}

	public void setServerNotifyUrl(String serverNotifyUrl) {
		this.serverNotifyUrl = serverNotifyUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MerchantOrder [merRef=");
		builder.append(merRef);
		builder.append(", txnType=");
		builder.append(txnType);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append(", billDesc=");
		builder.append(billDesc);
		builder.append(", orderDetail=");
		builder.append(orderDetail);
		builder.append(", orderDate=");
		builder.append(orderDate);
		builder.append(", orderTimezone=");
		builder.append(orderTimezone);
		builder.append(", orderTimeout=");
		builder.append(orderTimeout);
		builder.append(", customerIp=");
		builder.append(customerIp);
		builder.append(", payTimeout=");
		builder.append(payTimeout);
		builder.append(", preferredBank=");
		builder.append(preferredBank);
		builder.append(", browserNotifyUrl=");
		builder.append(browserNotifyUrl);
		builder.append(", serverNotifyUrl=");
		builder.append(serverNotifyUrl);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((billDesc == null) ? 0 : billDesc.hashCode());
		result = prime * result + ((browserNotifyUrl == null) ? 0 : browserNotifyUrl.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((customerIp == null) ? 0 : customerIp.hashCode());
		result = prime * result + ((merRef == null) ? 0 : merRef.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderDetail == null) ? 0 : orderDetail.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + orderTimeout;
		result = prime * result + ((orderTimezone == null) ? 0 : orderTimezone.hashCode());
		result = prime * result + payTimeout;
		result = prime * result + ((preferredBank == null) ? 0 : preferredBank.hashCode());
		result = prime * result + ((serverNotifyUrl == null) ? 0 : serverNotifyUrl.hashCode());
		result = prime * result + ((txnType == null) ? 0 : txnType.hashCode());
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
		MerchantOrder other = (MerchantOrder) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (billDesc == null) {
			if (other.billDesc != null)
				return false;
		} else if (!billDesc.equals(other.billDesc))
			return false;
		if (browserNotifyUrl == null) {
			if (other.browserNotifyUrl != null)
				return false;
		} else if (!browserNotifyUrl.equals(other.browserNotifyUrl))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (customerIp == null) {
			if (other.customerIp != null)
				return false;
		} else if (!customerIp.equals(other.customerIp))
			return false;
		if (merRef == null) {
			if (other.merRef != null)
				return false;
		} else if (!merRef.equals(other.merRef))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderDetail == null) {
			if (other.orderDetail != null)
				return false;
		} else if (!orderDetail.equals(other.orderDetail))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (orderTimeout != other.orderTimeout)
			return false;
		if (orderTimezone == null) {
			if (other.orderTimezone != null)
				return false;
		} else if (!orderTimezone.equals(other.orderTimezone))
			return false;
		if (payTimeout != other.payTimeout)
			return false;
		if (preferredBank == null) {
			if (other.preferredBank != null)
				return false;
		} else if (!preferredBank.equals(other.preferredBank))
			return false;
		if (serverNotifyUrl == null) {
			if (other.serverNotifyUrl != null)
				return false;
		} else if (!serverNotifyUrl.equals(other.serverNotifyUrl))
			return false;
		if (txnType != other.txnType)
			return false;
		return true;
	}

}
