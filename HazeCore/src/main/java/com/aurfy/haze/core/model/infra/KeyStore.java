package com.aurfy.haze.core.model.infra;

import java.util.Date;

import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.infra.mer.Merchant;
import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;

public class KeyStore extends TextualIDModel {

	private Merchant merchant;
	private CipherAlgorithmEnum cipherAlgorithm;
	private String encryptedKey;
	private String salt;
	private Date expiryDate;
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public CipherAlgorithmEnum getCipherAlgorithm() {
		return cipherAlgorithm;
	}
	public void setCipherAlgorithm(CipherAlgorithmEnum cipherAlgorithm) {
		this.cipherAlgorithm = cipherAlgorithm;
	}
	public String getEncryptedKey() {
		return encryptedKey;
	}
	public void setEncryptedKey(String encryptedKey) {
		this.encryptedKey = encryptedKey;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("KeyStore [merchant=");
		builder.append(merchant);
		builder.append(", cipherAlgorithm=");
		builder.append(cipherAlgorithm);
		builder.append(", encryptedKey=");
		builder.append(encryptedKey);
		builder.append(", salt=");
		builder.append(salt);
		builder.append(", expiryDate=");
		builder.append(expiryDate);
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
		result = prime * result + ((cipherAlgorithm == null) ? 0 : cipherAlgorithm.hashCode());
		result = prime * result + ((encryptedKey == null) ? 0 : encryptedKey.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + ((merchant == null) ? 0 : merchant.hashCode());
		result = prime * result + ((salt == null) ? 0 : salt.hashCode());
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
		KeyStore other = (KeyStore) obj;
		if (cipherAlgorithm != other.cipherAlgorithm)
			return false;
		if (encryptedKey == null) {
			if (other.encryptedKey != null)
				return false;
		} else if (!encryptedKey.equals(other.encryptedKey))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (merchant == null) {
			if (other.merchant != null)
				return false;
		} else if (!merchant.equals(other.merchant))
			return false;
		if (salt == null) {
			if (other.salt != null)
				return false;
		} else if (!salt.equals(other.salt))
			return false;
		return true;
	}
	
}
