package com.aurfy.haze.service.bean.infra;

import com.aurfy.haze.core.model.infra.Logistics;
import com.aurfy.haze.service.bean.CRUDBean;

public class LogisticsBean extends Logistics implements CRUDBean{

	public LogisticsBean() {
		super();
	}

	@Override
	public String toString() {
		return "LogisticsBean [getName()=" + getName() + ", getAbbreviation()="
				+ getAbbreviation() + ", getUrl()=" + getUrl()
				+ ", getInquiryHandler()=" + getInquiryHandler()
				+ ", getComments()=" + getComments() + ", getID()=" + getID()
				+ ", toString()=" + super.toString() + ", hashCode()="
				+ hashCode() + ", getCreateDate()=" + getCreateDate()
				+ ", getUpdateDate()=" + getUpdateDate() + ", getClass()="
				+ getClass() + "]";
	}
}
