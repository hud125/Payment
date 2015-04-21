package com.aurfy.haze.core.model.payment;

import java.io.Serializable;

import com.aurfy.haze.core.model.configuration.CardOrgEnum;

/**
 * all confidential information for payment, i.e: card info, card holder, etc.
 * 
 * @author hermano
 *
 */
public class PayCredential implements Serializable {

	public PayCredential() {
	}

	private String virtualAccount;
	private CardOrgEnum cardOrg;
	private String token;

	private String cardNo;

	private String encryptedCardNo;
	private String maskCardNo;
	private ExpiryDate expiryDate;
	private String cellphone;

	private String cardHolderFullName;
	private String cardHolderFirstName;
	private String cardHolderMiddleName;
	private String cardHolderLastName;

	private CredentialTypeEnum credentialType;
	private String credentialNo;

	private CardType cardType;
	private String cvv;
	private String pin;

	public String getVirtualAccount() {
		return virtualAccount;
	}

	public void setVirtualAccount(String virtualAccount) {
		this.virtualAccount = virtualAccount;
	}

	public CardOrgEnum getCardOrg() {
		return cardOrg;
	}

	public void setCardOrg(CardOrgEnum cardOrg) {
		this.cardOrg = cardOrg;
	}

	/**
	 * 支付用token，也可能是URL，适用于3D支付、农行等需要预先获取token的场景
	 * 
	 * @return
	 */
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getEncryptedCardNo() {
		return encryptedCardNo;
	}

	public void setEncryptedCardNo(String encryptedCardNo) {
		this.encryptedCardNo = encryptedCardNo;
	}

	public String getMaskCardNo() {
		return maskCardNo;
	}

	public void setMaskCardNo(String maskCardNo) {
		this.maskCardNo = maskCardNo;
	}

	public ExpiryDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(ExpiryDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getCardHolderFullName() {
		return cardHolderFullName;
	}

	public void setCardHolderFullName(String cardHolderFullName) {
		this.cardHolderFullName = cardHolderFullName;
	}

	public String getCardHolderFirstName() {
		return cardHolderFirstName;
	}

	public void setCardHolderFirstName(String cardHolderFirstName) {
		this.cardHolderFirstName = cardHolderFirstName;
	}

	public String getCardHolderMiddleName() {
		return cardHolderMiddleName;
	}

	public void setCardHolderMiddleName(String cardHolderMiddleName) {
		this.cardHolderMiddleName = cardHolderMiddleName;
	}

	public String getCardHolderLastName() {
		return cardHolderLastName;
	}

	public void setCardHolderLastName(String cardHolderLastName) {
		this.cardHolderLastName = cardHolderLastName;
	}

	public CredentialTypeEnum getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(CredentialTypeEnum credentialType) {
		this.credentialType = credentialType;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PayCredential [virtualAccount=");
		builder.append(virtualAccount);
		builder.append(", cardOrg=");
		builder.append(cardOrg);
		builder.append(", token=");
		builder.append(token);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", encryptedCardNo=");
		builder.append(encryptedCardNo);
		builder.append(", maskCardNo=");
		builder.append(maskCardNo);
		builder.append(", expiryDate=");
		builder.append(expiryDate);
		builder.append(", cellphone=");
		builder.append(cellphone);
		builder.append(", cardHolderFullName=");
		builder.append(cardHolderFullName);
		builder.append(", cardHolderFirstName=");
		builder.append(cardHolderFirstName);
		builder.append(", cardHolderMiddleName=");
		builder.append(cardHolderMiddleName);
		builder.append(", cardHolderLastName=");
		builder.append(cardHolderLastName);
		builder.append(", credentialType=");
		builder.append(credentialType);
		builder.append(", credentialNo=");
		builder.append(credentialNo);
		builder.append(", cardType=");
		builder.append(cardType);
		builder.append(", cvv=");
		builder.append(cvv);
		builder.append(", pin=");
		builder.append(pin);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardHolderFirstName == null) ? 0 : cardHolderFirstName.hashCode());
		result = prime * result + ((cardHolderFullName == null) ? 0 : cardHolderFullName.hashCode());
		result = prime * result + ((cardHolderLastName == null) ? 0 : cardHolderLastName.hashCode());
		result = prime * result + ((cardHolderMiddleName == null) ? 0 : cardHolderMiddleName.hashCode());
		result = prime * result + ((cardNo == null) ? 0 : cardNo.hashCode());
		result = prime * result + ((cardOrg == null) ? 0 : cardOrg.hashCode());
		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + ((cellphone == null) ? 0 : cellphone.hashCode());
		result = prime * result + ((credentialNo == null) ? 0 : credentialNo.hashCode());
		result = prime * result + ((credentialType == null) ? 0 : credentialType.hashCode());
		result = prime * result + ((cvv == null) ? 0 : cvv.hashCode());
		result = prime * result + ((encryptedCardNo == null) ? 0 : encryptedCardNo.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + ((maskCardNo == null) ? 0 : maskCardNo.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((virtualAccount == null) ? 0 : virtualAccount.hashCode());
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
		PayCredential other = (PayCredential) obj;
		if (cardHolderFirstName == null) {
			if (other.cardHolderFirstName != null)
				return false;
		} else if (!cardHolderFirstName.equals(other.cardHolderFirstName))
			return false;
		if (cardHolderFullName == null) {
			if (other.cardHolderFullName != null)
				return false;
		} else if (!cardHolderFullName.equals(other.cardHolderFullName))
			return false;
		if (cardHolderLastName == null) {
			if (other.cardHolderLastName != null)
				return false;
		} else if (!cardHolderLastName.equals(other.cardHolderLastName))
			return false;
		if (cardHolderMiddleName == null) {
			if (other.cardHolderMiddleName != null)
				return false;
		} else if (!cardHolderMiddleName.equals(other.cardHolderMiddleName))
			return false;
		if (cardNo == null) {
			if (other.cardNo != null)
				return false;
		} else if (!cardNo.equals(other.cardNo))
			return false;
		if (cardOrg != other.cardOrg)
			return false;
		if (cardType == null) {
			if (other.cardType != null)
				return false;
		} else if (!cardType.equals(other.cardType))
			return false;
		if (cellphone == null) {
			if (other.cellphone != null)
				return false;
		} else if (!cellphone.equals(other.cellphone))
			return false;
		if (credentialNo == null) {
			if (other.credentialNo != null)
				return false;
		} else if (!credentialNo.equals(other.credentialNo))
			return false;
		if (credentialType != other.credentialType)
			return false;
		if (cvv == null) {
			if (other.cvv != null)
				return false;
		} else if (!cvv.equals(other.cvv))
			return false;
		if (encryptedCardNo == null) {
			if (other.encryptedCardNo != null)
				return false;
		} else if (!encryptedCardNo.equals(other.encryptedCardNo))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (maskCardNo == null) {
			if (other.maskCardNo != null)
				return false;
		} else if (!maskCardNo.equals(other.maskCardNo))
			return false;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (virtualAccount == null) {
			if (other.virtualAccount != null)
				return false;
		} else if (!virtualAccount.equals(other.virtualAccount))
			return false;
		return true;
	}

}
