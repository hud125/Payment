package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.model.TextualIDModel;

public class Logistics extends TextualIDModel {
	
	private String name;
	
	private String abbreviation;
	
	private String url;
	
	private String inquiryHandler;
	
	private String comments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInquiryHandler() {
		return inquiryHandler;
	}

	public void setInquiryHandler(String inquiryHandler) {
		this.inquiryHandler = inquiryHandler;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Logistics [name=" + name + ", abbreviation=" + abbreviation
				+ ", url=" + url + ", inquiryHandler=" + inquiryHandler
				+ ", comments=" + comments + ", getID()=" + getID()
				+ ", getCreateDate()=" + getCreateDate() + ", getUpdateDate()="
				+ getUpdateDate() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((abbreviation == null) ? 0 : abbreviation.hashCode());
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result
				+ ((inquiryHandler == null) ? 0 : inquiryHandler.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Logistics other = (Logistics) obj;
		if (abbreviation == null) {
			if (other.abbreviation != null)
				return false;
		} else if (!abbreviation.equals(other.abbreviation))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (inquiryHandler == null) {
			if (other.inquiryHandler != null)
				return false;
		} else if (!inquiryHandler.equals(other.inquiryHandler))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
}
