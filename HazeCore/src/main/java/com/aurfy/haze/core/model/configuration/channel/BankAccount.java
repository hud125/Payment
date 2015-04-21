package com.aurfy.haze.core.model.configuration.channel;

import java.util.Date;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.infra.AddressBook;

/**
 * 资金落地行
 * 
 * @author zhangcheng
 *
 */
public class BankAccount extends TextualIDModel {
	private String bankId;
	private String bankCode;
	private String bankAbbreviation;
	private String bankName;
	private String bankBranch;
	private String bankURL;
	private String addressId;
	private String accountHolder;// 开户人姓名
	private String accountNumber;// 卡号
	private Currency accountCurrency;// Currency
	private String routingTransitNumber;// http://en.wikipedia.org/wiki/Routing_transit_number
	private String swiftCode;// http://www.theswiftcodes.com/
	private Date accountOpenDate;// 开卡日期
	private String comments;
	private String ownerId;
	private AddressBook addressBook;

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

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

	public AddressBook getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(AddressBook addressBook) {
		this.addressBook = addressBook;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getRoutingTransitNumber() {
		return routingTransitNumber;
	}

	public void setRoutingTransitNumber(String routingTransitNumber) {
		this.routingTransitNumber = routingTransitNumber;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public Date getAccountOpenDate() {
		return accountOpenDate;
	}

	public void setAccountOpenDate(Date accountOpenDate) {
		this.accountOpenDate = accountOpenDate;
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

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public Currency getAccountCurrency() {
		return accountCurrency;
	}

	public void setAccountCurrency(Currency accountCurrency) {
		this.accountCurrency = accountCurrency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((accountCurrency == null) ? 0 : accountCurrency.hashCode());
		result = prime * result + ((accountHolder == null) ? 0 : accountHolder.hashCode());
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((accountOpenDate == null) ? 0 : accountOpenDate.hashCode());
		result = prime * result + ((addressBook == null) ? 0 : addressBook.hashCode());
		result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
		result = prime * result + ((bankAbbreviation == null) ? 0 : bankAbbreviation.hashCode());
		result = prime * result + ((bankBranch == null) ? 0 : bankBranch.hashCode());
		result = prime * result + ((bankCode == null) ? 0 : bankCode.hashCode());
		result = prime * result + ((bankId == null) ? 0 : bankId.hashCode());
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((bankURL == null) ? 0 : bankURL.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((routingTransitNumber == null) ? 0 : routingTransitNumber.hashCode());
		result = prime * result + ((swiftCode == null) ? 0 : swiftCode.hashCode());
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
		BankAccount other = (BankAccount) obj;
		if (accountCurrency == null) {
			if (other.accountCurrency != null)
				return false;
		} else if (!accountCurrency.equals(other.accountCurrency))
			return false;
		if (accountHolder == null) {
			if (other.accountHolder != null)
				return false;
		} else if (!accountHolder.equals(other.accountHolder))
			return false;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (accountOpenDate == null) {
			if (other.accountOpenDate != null)
				return false;
		} else if (!accountOpenDate.equals(other.accountOpenDate))
			return false;
		if (addressBook == null) {
			if (other.addressBook != null)
				return false;
		} else if (!addressBook.equals(other.addressBook))
			return false;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
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
		if (bankId == null) {
			if (other.bankId != null)
				return false;
		} else if (!bankId.equals(other.bankId))
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
		if (routingTransitNumber == null) {
			if (other.routingTransitNumber != null)
				return false;
		} else if (!routingTransitNumber.equals(other.routingTransitNumber))
			return false;
		if (swiftCode == null) {
			if (other.swiftCode != null)
				return false;
		} else if (!swiftCode.equals(other.swiftCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankAccount [bankId=");
		builder.append(bankId);
		builder.append(", bankCode=");
		builder.append(bankCode);
		builder.append(", bankAbbreviation=");
		builder.append(bankAbbreviation);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", bankBranch=");
		builder.append(bankBranch);
		builder.append(", bankURL=");
		builder.append(bankURL);
		builder.append(", addressId=");
		builder.append(addressId);
		builder.append(", accountHolder=");
		builder.append(accountHolder);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", accountCurrency=");
		builder.append(accountCurrency);
		builder.append(", routingTransitNumber=");
		builder.append(routingTransitNumber);
		builder.append(", swiftCode=");
		builder.append(swiftCode);
		builder.append(", accountOpenDate=");
		builder.append(accountOpenDate);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append(", addressBook=");
		builder.append(addressBook);
		builder.append(", getID()=");
		builder.append(getID());
		builder.append(", getCreateDate()=");
		builder.append(getCreateDate());
		builder.append(", getUpdateDate()=");
		builder.append(getUpdateDate());
		builder.append("]");
		return builder.toString();
	}

}
