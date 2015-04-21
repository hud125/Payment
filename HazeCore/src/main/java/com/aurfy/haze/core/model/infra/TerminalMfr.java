package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.model.TextualIDModel;

public class TerminalMfr extends TextualIDModel {

	private String factoryName;
	private String contactor;
	private String telphone;
	private String cellphone;
	private String URL;

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	@Override
	public String toString() {
		return "TerminalMfrEntity [factoryName=" + factoryName + ", contactor=" + contactor + ", telphone=" + telphone
				+ ", cellphone=" + cellphone + ", URL=" + URL + ", getFactoryName()=" + getFactoryName()
				+ ", getContactor()=" + getContactor() + ", getTelphone()=" + getTelphone() + ", getCellphone()="
				+ getCellphone() + ", getURL()=" + getURL() + ", getID()=" + getID() + ", toString()="
				+ super.toString() + ", hashCode()=" + hashCode() + ", getCreateDate()=" + getCreateDate()
				+ ", getUpdateDate()=" + getUpdateDate() + ", getClass()=" + getClass() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((URL == null) ? 0 : URL.hashCode());
		result = prime * result + ((cellphone == null) ? 0 : cellphone.hashCode());
		result = prime * result + ((contactor == null) ? 0 : contactor.hashCode());
		result = prime * result + ((factoryName == null) ? 0 : factoryName.hashCode());
		result = prime * result + ((telphone == null) ? 0 : telphone.hashCode());
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
		TerminalMfr other = (TerminalMfr) obj;
		if (URL == null) {
			if (other.URL != null)
				return false;
		} else if (!URL.equals(other.URL))
			return false;
		if (cellphone == null) {
			if (other.cellphone != null)
				return false;
		} else if (!cellphone.equals(other.cellphone))
			return false;
		if (contactor == null) {
			if (other.contactor != null)
				return false;
		} else if (!contactor.equals(other.contactor))
			return false;
		if (factoryName == null) {
			if (other.factoryName != null)
				return false;
		} else if (!factoryName.equals(other.factoryName))
			return false;
		if (telphone == null) {
			if (other.telphone != null)
				return false;
		} else if (!telphone.equals(other.telphone))
			return false;
		return true;
	}

}
