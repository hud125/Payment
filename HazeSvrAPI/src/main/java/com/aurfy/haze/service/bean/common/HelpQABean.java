package com.aurfy.haze.service.bean.common;

import com.aurfy.haze.core.model.common.HelpQA;
import com.aurfy.haze.service.bean.CRUDBean;

public class HelpQABean extends HelpQA implements CRUDBean {

	public HelpQABean() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HelpQABean [getID()=");
		builder.append(getID());
		builder.append(", getKey()=");
		builder.append(getKey());
		builder.append(", getTopic()=");
		builder.append(getTopic());
		builder.append(", getAnswer()=");
		builder.append(getAnswer());
		builder.append(", getCreateDate()=");
		builder.append(getCreateDate());
		builder.append(", getUpdateDate()=");
		builder.append(getUpdateDate());
		builder.append("]");
		return builder.toString();
	}
}
