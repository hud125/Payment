package com.aurfy.haze.core.model.configuration.channel;

import com.aurfy.haze.core.model.TextualIDModel;

/**
 * 银行列表，作为基础数据给其他业务使用
 * 
 * @author zhangcheng
 *
 */
public class Bank extends TextualIDModel {
	
	private static final long serialVersionUID = 1762763023679010260L;
	private String bankCode;//银行编号：如ICBC
	private String bankAbbreviation;//银行缩写：如工行
	private String bankName;//中国工商银行
	private String bankBranch;
	private String bankURL;//http://www.icbc.com.cn/icbc/
	private String comments;
	private String ownerId;
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankAbbreviation() {
		return bankAbbreviation;
	}
	public void setBankAbbreviation(String bankAbbreviation) {
		this.bankAbbreviation = bankAbbreviation;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankBranch() {
		return bankBranch;
	}
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	public String getBankURL() {
		return bankURL;
	}
	public void setBankURL(String bankURL) {
		this.bankURL = bankURL;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bank [bankCode=");
		builder.append(bankCode);
		builder.append(", bankAbbreviation=");
		builder.append(bankAbbreviation);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", bankBranch=");
		builder.append(bankBranch);
		builder.append(", bankURL=");
		builder.append(bankURL);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", ownerId=");
		builder.append(ownerId);
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
		result = prime
				* result
				+ ((bankAbbreviation == null) ? 0 : bankAbbreviation.hashCode());
		result = prime * result
				+ ((bankBranch == null) ? 0 : bankBranch.hashCode());
		result = prime * result
				+ ((bankCode == null) ? 0 : bankCode.hashCode());
		result = prime * result
				+ ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((bankURL == null) ? 0 : bankURL.hashCode());
		result = prime * result
				+ ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
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
		Bank other = (Bank) obj;
		if (bankAbbreviation == null) {
			if (other.bankAbbreviation != null)
				return false;
		} else if (!bankAbbreviation.equals(other.bankAbbreviation))
			return false;
		if (bankBranch == null) {
			if (other.bankBranch != null)
				return false;
		} else if (!bankBranch.equals(other.bankBranch))
			return false;
		if (bankCode == null) {
			if (other.bankCode != null)
				return false;
		} else if (!bankCode.equals(other.bankCode))
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (bankURL == null) {
			if (other.bankURL != null)
				return false;
		} else if (!bankURL.equals(other.bankURL))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		return true;
	}
	
}
