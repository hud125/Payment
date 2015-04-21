package com.aurfy.haze.web.vo.payment;

import com.aurfy.haze.web.vo.RequestVO;

public class PayQueryReqVO extends RequestVO {

	private String merCode;
	private String orderId;
	private String orderDate;// 订单时间
	private String extended;
	

	public String getMerCode() {
		return merCode;
	}

	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getExtended() {
		return extended;
	}

	public void setExtended(String extended) {
		this.extended = extended;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

}
