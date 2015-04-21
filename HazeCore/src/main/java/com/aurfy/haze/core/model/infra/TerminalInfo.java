package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.model.TextualIDModel;

public class TerminalInfo extends TextualIDModel {

	private TerminalMfr terminalMfr;
	private String merID;

	public TerminalMfr getTerminalMfr() {
		return terminalMfr;
	}

	public void setTerminalMfr(TerminalMfr terminalMfr) {
		this.terminalMfr = terminalMfr;
	}

	public String getMerID() {
		return merID;
	}

	public void setMerID(String merID) {
		this.merID = merID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TerminalInfo [TerminalMfr=");
		builder.append(terminalMfr);
		builder.append(", MerchantID=");
		builder.append(merID);
		builder.append(", getID()=");
		builder.append(getID());
		builder.append(", getCreateDate()=");
		builder.append(getCreateDate());
		builder.append(", getUpdateDate()=");
		builder.append(getUpdateDate());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((merID == null) ? 0 : merID.hashCode());
		result = prime * result + ((terminalMfr == null) ? 0 : terminalMfr.hashCode());
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
		TerminalInfo other = (TerminalInfo) obj;
		if (merID == null) {
			if (other.merID != null)
				return false;
		} else if (!merID.equals(other.merID))
			return false;
		if (terminalMfr == null) {
			if (other.terminalMfr != null)
				return false;
		} else if (!terminalMfr.equals(other.terminalMfr))
			return false;
		return true;
	}
}
