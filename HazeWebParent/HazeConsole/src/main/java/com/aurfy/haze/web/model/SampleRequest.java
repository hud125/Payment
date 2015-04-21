package com.aurfy.haze.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Required;

import com.aurfy.haze.web.validator.ValidationField;
import com.aurfy.haze.web.validator.ValidationFieldType;

public class SampleRequest implements Serializable {

	private String keyword;
	private String fieldName;
	private BigDecimal minPrice;
	private BigDecimal maxPrice;

	private int start;
	private int rows;

	public SampleRequest() {
	}

	@NotBlank
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@ValidationField(ValidationFieldType.A)
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Min(0)
	@Max(Integer.MAX_VALUE)
	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	@Min(0)
	@Max(Integer.MAX_VALUE)
	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	@AssertTrue(message = "maxPrice must be greater than or equal to minPrice")
	private boolean isValidPriceRange() {
		if (this.maxPrice != null && this.minPrice != null) {
			return this.maxPrice.compareTo(this.minPrice) >= 0;
		} else {
			return true;
		}
	}

	@Required
	@Min(0)
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@Required
	@Range(min = 1, max = 999)
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SampleRequest [keyword=");
		builder.append(keyword);
		builder.append(", fieldName=");
		builder.append(fieldName);
		builder.append(", minPrice=");
		builder.append(minPrice);
		builder.append(", maxPrice=");
		builder.append(maxPrice);
		builder.append(", start=");
		builder.append(start);
		builder.append(", rows=");
		builder.append(rows);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + ((maxPrice == null) ? 0 : maxPrice.hashCode());
		result = prime * result + ((minPrice == null) ? 0 : minPrice.hashCode());
		result = prime * result + rows;
		result = prime * result + start;
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
		SampleRequest other = (SampleRequest) obj;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (maxPrice == null) {
			if (other.maxPrice != null)
				return false;
		} else if (!maxPrice.equals(other.maxPrice))
			return false;
		if (minPrice == null) {
			if (other.minPrice != null)
				return false;
		} else if (!minPrice.equals(other.minPrice))
			return false;
		if (rows != other.rows)
			return false;
		if (start != other.start)
			return false;
		return true;
	}
}
