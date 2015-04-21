package com.aurfy.haze.core.model.payment;

import com.aurfy.haze.core.internal.UseEnumTypeHandler;
import com.aurfy.haze.core.utils.ValidateUtils;

@UseEnumTypeHandler("com.aurfy.haze.dao.handler.ExpiryDateTypeHandler")
public class ExpiryDate {

	private String month;
	private String year;

	/**
	 * month first, then year
	 * 
	 * @param month
	 * @param year
	 */
	public ExpiryDate(String month, String year) {
		super();
		setMonth(month);
		setYear(year);
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		if (!ValidateUtils.isValidMonth(month)) {
			throw new RuntimeException("invalid month");
		}
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		if (!ValidateUtils.isValidYearShort(year)) {
			throw new RuntimeException("invalid year");
		}
		this.year = year;
	}

	public static ExpiryDate parseByDateStr(String dateStr) {
		if (dateStr == null) {
			return null;
		} else if (dateStr.length() != 4) {
			throw new RuntimeException("invalid expiry date");
		} else {
			return new ExpiryDate(dateStr.substring(0, 2), dateStr.substring(2));
		}
	}

	/**
	 * 做交易时，上送的就是“0115”，所以在toString中转换就行了
	 */
	@Override
	public String toString() {
		return getMonth() + getYear();
	}

	public String toYearMonth() {
		return getYear() + getMonth();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		ExpiryDate other = (ExpiryDate) obj;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

}
