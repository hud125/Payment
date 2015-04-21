package com.unionpay.upop.sdk;

import static com.unionpay.upop.sdk.util.PayUtils.getCurrentTime;
import static com.unionpay.upop.sdk.util.PayUtils.isBlank;

import java.io.Serializable;

public class PayRequest implements Serializable {

	private static final long serialVersionUID = -3798344254481833636L;

	private String version; // 配置文件
	private String charset; // 配置文件

	/**
	 * 商户的代码
	 */
	private String merId; // 配置文件
	/**
	 * 商户的名称
	 */
	private String merAbbr; // 配置文件
	/**
	 * 商户的类型mcc
	 */
	private String merCode; // 配置文件
	/**
	 * 收单机构代码
	 * 
	 */
	private String acqCode; // 配置文件
	/**
	 * 收单机构网站的前台URL 当完成买家账户向收单机构账户的支付时,通过收单机构通知URL进行页面跳转
	 */
	private String frontEndUrl;// 配置文件
	/**
	 * 收单机构网站的后台URL 当完成买家账户向收单机构账户的支付时,要求收单机构返回URL收到通知后进行响应。
	 */
	private String backEndUrl;// 配置文件

	/**
	 * 默认支付方式 LitePay（认证支付）,ProPay（快捷支付）...
	 */
	private String defaultPayType; // 配置文件
	/**
	 * 默认银行编码
	 * 
	 */
	private String defaultBankNumber; // 配置文件
	/**
	 * 交易超时时间 单位为毫秒。
	 */
	private String transTimeout;

	/**
	 * 交易币种 3位定长数字
	 * 
	 */
	private String orderCurrency; // 枚举?

	private String signMethod;// 配置文件
	/**
	 * 原始交易流水号 　 上一笔关联交易的流水号，以便于银联互联网系统可以准确定位原始交易。 交易类型为“撤销”、“完成”或者“退货”时必填。
	 * 
	 */
	private String origQid;
	/**
	 * 交易类型
	 */
	private String transType; // 枚举？
	/**
	 * 商品URL
	 */
	private String commodityUrl;
	/**
	 * 商品名称
	 */
	private String commodityName;
	/**
	 * 商品单价 单件商品的价格。本域中不带小数点，小数位根据6.24　交易币种来决定。
	 */
	private String commodityUnitPrice;
	/**
	 * 商品数量
	 */
	private String commodityQuantity;
	/**
	 * 优惠信息
	 */
	private String commodityDiscount;
	/**
	 * 运输费用
	 */
	private String transferFee;
	/**
	 * 持卡人IP 持卡人访问收单机构网站时的IP地址
	 */
	private String customerIp;
	/**
	 * 持卡人姓名
	 */
	private String customerName; // pay credential
	/**
	 * 商户保留域
	 */
	private String merReserved;

	private String orderNumber;
	/**
	 * 交易开始时间 交易开始日期和时间均为北京时间。格式：YYYYMMDDHHMMSS
	 */
	private String orderTime;
	/**
	 * 交易金额 本域值是交易的总金额，包括商品的价格和运费。
	 */
	private String orderAmount;
	/**
	 * 签名秘钥
	 */
	private String securityKey;

	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	public String getDefaultPayType() {
		return defaultPayType;
	}

	public void setDefaultPayType(String defaultPayType) {
		this.defaultPayType = defaultPayType;
	}

	public String getDefaultBankNumber() {
		return defaultBankNumber;
	}

	public void setDefaultBankNumber(String defaultBankNumber) {
		this.defaultBankNumber = defaultBankNumber;
	}

	public String getOrderNumber() {
		return isBlank(orderNumber) ? getCurrentTime() : orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderTime() {
		return isBlank(orderTime) ? getCurrentTime() : orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getCommodityUrl() {
		return commodityUrl;
	}

	public void setCommodityUrl(String commodityUrl) {
		this.commodityUrl = commodityUrl;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityUnitPrice() {
		return commodityUnitPrice;
	}

	public void setCommodityUnitPrice(String commodityUnitPrice) {
		this.commodityUnitPrice = commodityUnitPrice;
	}

	public String getCommodityQuantity() {
		return commodityQuantity;
	}

	public void setCommodityQuantity(String commodityQuantity) {
		this.commodityQuantity = commodityQuantity;
	}

	public String getCommodityDiscount() {
		return commodityDiscount;
	}

	public void setCommodityDiscount(String commodityDiscount) {
		this.commodityDiscount = commodityDiscount;
	}

	public String getTransferFee() {
		return transferFee;
	}

	public void setTransferFee(String transferFee) {
		this.transferFee = transferFee;
	}

	public String getMerCode() {
		return merCode;
	}

	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getOrigQid() {
		return origQid;
	}

	public void setOrigQid(String origQid) {
		this.origQid = origQid;
	}

	public String getAcqCode() {
		return acqCode;
	}

	public void setAcqCode(String acqCode) {
		this.acqCode = acqCode;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTransTimeout() {
		return transTimeout;
	}

	public void setTransTimeout(String transTimeout) {
		this.transTimeout = transTimeout;
	}

	public String getFrontEndUrl() {
		return frontEndUrl;
	}

	public void setFrontEndUrl(String frontEndUrl) {
		this.frontEndUrl = frontEndUrl;
	}

	public String getBackEndUrl() {
		return backEndUrl;
	}

	public void setBackEndUrl(String backEndUrl) {
		this.backEndUrl = backEndUrl;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getMerReserved() {
		return merReserved;
	}

	public void setMerReserved(String merReserved) {
		this.merReserved = merReserved;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerAbbr() {
		return merAbbr;
	}

	public void setMerAbbr(String merAbbr) {
		this.merAbbr = merAbbr;
	}
}
