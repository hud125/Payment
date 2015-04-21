package com.aurfy.haze.core.model.txn;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.utils.ValidateUtils;

/**
 * 
 * @author rocket
 *
 */
public class SrcPayOrder implements Serializable {

	/**
	 * 原始交易所来自的应用：PGW；充值；分账等
	 */
	private SrcAppTypeEnum srcAppType;

	/**
	 * 原应用的账户Id,可能是手机或者email,mid
	 */
	private String srcRefId;
	/**
	 * 原应用的交易流水号
	 */
	private String srcTxnId;
	/**
	 * 交易类型
	 */
	private TxnTypeEnum srcTxnType;
	/**
	 * 原应用商户的订单号
	 */
	private String srcOrderId;
	/**
	 * 
	 */
	private Currency srcCurrency;
	private BigInteger srcAmount;
	/**
	 * srcTxnDate是指商户的订单时间，即MerchantOrder中的orderDate
	 */
	private Date srcTxnDate;// 
	/**
	 * 交易日期，只到年月日，不含时分秒。重写equals方法，!DateUtils.isInSameDay(srcTxnDay, other.srcTxnDay)
	 */
	private Date srcTxnDay;

	public SrcAppTypeEnum getSrcAppType() {
		return srcAppType;
	}

	public void setSrcAppType(SrcAppTypeEnum srcAppType) {
		this.srcAppType = srcAppType;
	}

	public String getSrcRefId() {
		return srcRefId;
	}

	public void setSrcRefId(String srcRefId) {
		this.srcRefId = srcRefId;
	}

	public String getSrcTxnId() {
		return srcTxnId;
	}

	public void setSrcTxnId(String srcTxnId) {
		this.srcTxnId = srcTxnId;
	}

	public TxnTypeEnum getSrcTxnType() {
		return srcTxnType;
	}

	public void setSrcTxnType(TxnTypeEnum srcTxnType) {
		this.srcTxnType = srcTxnType;
	}

	public String getSrcOrderId() {
		return srcOrderId;
	}

	public void setSrcOrderId(String srcOrderId) {
		this.srcOrderId = srcOrderId;
	}

	public Currency getSrcCurrency() {
		return srcCurrency;
	}

	public void setSrcCurrency(Currency srcCurrency) {
		this.srcCurrency = srcCurrency;
	}

	public BigInteger getSrcAmount() {
		return srcAmount;
	}

	public void setSrcAmount(BigInteger srcAmount) {
		this.srcAmount = srcAmount;
	}

	/**
	 * 指商户的订单时间，即MerchantOrder中的orderDate
	 * 
	 * @return
	 */
	public Date getSrcTxnDate() {
		return srcTxnDate;
	}

	public void setSrcTxnDate(Date srcTxnDate) {
		this.srcTxnDate = srcTxnDate;
	}

	/**
	 * 交易日期，只到年月日，不含时分秒。重写equals方法，!ValidateUtils.isInSameDay(srcTxnDay, other.srcTxnDay)
	 * 
	 * @return
	 */
	public Date getSrcTxnDay() {
		return srcTxnDay;
	}

	public void setSrcTxnDay(Date srcTxnDay) {
		this.srcTxnDay = srcTxnDay;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SrcPayOrder [srcAppType=");
		builder.append(srcAppType);
		builder.append(", srcRefId=");
		builder.append(srcRefId);
		builder.append(", srcTxnId=");
		builder.append(srcTxnId);
		builder.append(", srcTxnType=");
		builder.append(srcTxnType);
		builder.append(", srcOrderId=");
		builder.append(srcOrderId);
		builder.append(", srcCurrency=");
		builder.append(srcCurrency);
		builder.append(", srcAmount=");
		builder.append(srcAmount);
		builder.append(", srcTxnDate=");
		builder.append(srcTxnDate);
		builder.append(", srcTxnDay=");
		builder.append(srcTxnDay);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((srcAmount == null) ? 0 : srcAmount.hashCode());
		result = prime * result + ((srcAppType == null) ? 0 : srcAppType.hashCode());
		result = prime * result + ((srcCurrency == null) ? 0 : srcCurrency.hashCode());
		result = prime * result + ((srcOrderId == null) ? 0 : srcOrderId.hashCode());
		result = prime * result + ((srcRefId == null) ? 0 : srcRefId.hashCode());
		result = prime * result + ((srcTxnDate == null) ? 0 : srcTxnDate.hashCode());
		result = prime * result + ((srcTxnDay == null) ? 0 : srcTxnDay.hashCode());
		result = prime * result + ((srcTxnId == null) ? 0 : srcTxnId.hashCode());
		result = prime * result + ((srcTxnType == null) ? 0 : srcTxnType.hashCode());
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
		SrcPayOrder other = (SrcPayOrder) obj;
		if (srcAmount == null) {
			if (other.srcAmount != null)
				return false;
		} else if (!srcAmount.equals(other.srcAmount))
			return false;
		if (srcAppType != other.srcAppType)
			return false;
		if (srcCurrency == null) {
			if (other.srcCurrency != null)
				return false;
		} else if (!srcCurrency.equals(other.srcCurrency))
			return false;
		if (srcOrderId == null) {
			if (other.srcOrderId != null)
				return false;
		} else if (!srcOrderId.equals(other.srcOrderId))
			return false;
		if (srcRefId == null) {
			if (other.srcRefId != null)
				return false;
		} else if (!srcRefId.equals(other.srcRefId))
			return false;
		if (srcTxnDate == null) {
			if (other.srcTxnDate != null)
				return false;
		} else if (!srcTxnDate.equals(other.srcTxnDate))
			return false;
		if (srcTxnDay == null) {
			if (other.srcTxnDay != null)
				return false;
		} else if (!ValidateUtils.isInSameDay(srcTxnDay, other.srcTxnDay))
			return false;
		if (srcTxnId == null) {
			if (other.srcTxnId != null)
				return false;
		} else if (!srcTxnId.equals(other.srcTxnId))
			return false;
		if (srcTxnType != other.srcTxnType)
			return false;
		return true;
	}

}
