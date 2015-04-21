package com.aurfy.haze.core.model.infra.mer;

import java.util.Date;

import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.infra.AddressBook;

public class Merchant extends TextualIDModel {

	private String name;
	private String merchantCode;
	private String parentID;
	private String abbreviation;
	private String merchantType;
	private String federalID;
	private String taxID;
	private String transactionURLs;
	private String industry;
	private String mcc;
	private String contactPerson;
	private String contactPhone;
	private String contactEmail;
	private AddressBook addressBook;
	private Date effectiveDate;
	private Date terminateDate;
	private Float timeZone;
	private String timeZoneName;
	private String comments;
	private MerchantStatusEnum status;
	private String ownerId;

	private String salesId;
	private String agentId;
	private String pspId;
	private String rateConfigId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getFederalID() {
		return federalID;
	}

	public void setFederalID(String federalID) {
		this.federalID = federalID;
	}

	public String getTaxID() {
		return taxID;
	}

	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}

	public String getTransactionURLs() {
		return transactionURLs;
	}

	public void setTransactionURLs(String transactionURLs) {
		this.transactionURLs = transactionURLs;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * merchant category code
	 * 
	 * @return
	 */
	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public AddressBook getAddressBook() {
		return addressBook;
	}

	public void setAddressBook(AddressBook addressBook) {
		this.addressBook = addressBook;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getTerminateDate() {
		return terminateDate;
	}

	public void setTerminateDate(Date terminateDate) {
		this.terminateDate = terminateDate;
	}

	public Float getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Float timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZoneName() {
		return timeZoneName;
	}

	public void setTimeZoneName(String timeZoneName) {
		this.timeZoneName = timeZoneName;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public MerchantStatusEnum getStatus() {
		return status;
	}

	public void setStatus(MerchantStatusEnum status) {
		this.status = status;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getPspId() {
		return pspId;
	}

	public void setPspId(String pspId) {
		this.pspId = pspId;
	}

	public String getRateConfigId() {
		return rateConfigId;
	}

	public void setRateConfigId(String rateConfigId) {
		this.rateConfigId = rateConfigId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Merchant [name=");
		builder.append(name);
		builder.append(", code=");
		builder.append(merchantCode);
		builder.append(", parentID=");
		builder.append(parentID);
		builder.append(", abbreviation=");
		builder.append(abbreviation);
		builder.append(", merchantType=");
		builder.append(merchantType);
		builder.append(", federalID=");
		builder.append(federalID);
		builder.append(", taxID=");
		builder.append(taxID);
		builder.append(", transactionURLs=");
		builder.append(transactionURLs);
		builder.append(", industry=");
		builder.append(industry);
		builder.append(", mcc=");
		builder.append(mcc);
		builder.append(", contactPerson=");
		builder.append(contactPerson);
		builder.append(", contactPhone=");
		builder.append(contactPhone);
		builder.append(", contactEmail=");
		builder.append(contactEmail);
		builder.append(", addressBook=");
		builder.append(addressBook);
		builder.append(", effectiveDate=");
		builder.append(effectiveDate);
		builder.append(", terminateDate=");
		builder.append(terminateDate);
		builder.append(", timeZone=");
		builder.append(timeZone);
		builder.append(", timeZoneName=");
		builder.append(timeZoneName);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", status=");
		builder.append(status);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append(", salesId=");
		builder.append(salesId);
		builder.append(", agentId=");
		builder.append(agentId);
		builder.append(", pspId=");
		builder.append(pspId);
		builder.append(", rateConfigId=");
		builder.append(rateConfigId);
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
		result = prime * result + ((abbreviation == null) ? 0 : abbreviation.hashCode());
		result = prime * result + ((addressBook == null) ? 0 : addressBook.hashCode());
		result = prime * result + ((agentId == null) ? 0 : agentId.hashCode());
		result = prime * result + ((merchantCode == null) ? 0 : merchantCode.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((contactEmail == null) ? 0 : contactEmail.hashCode());
		result = prime * result + ((contactPerson == null) ? 0 : contactPerson.hashCode());
		result = prime * result + ((contactPhone == null) ? 0 : contactPhone.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((federalID == null) ? 0 : federalID.hashCode());
		result = prime * result + ((industry == null) ? 0 : industry.hashCode());
		result = prime * result + ((mcc == null) ? 0 : mcc.hashCode());
		result = prime * result + ((merchantType == null) ? 0 : merchantType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((parentID == null) ? 0 : parentID.hashCode());
		result = prime * result + ((pspId == null) ? 0 : pspId.hashCode());
		result = prime * result + ((rateConfigId == null) ? 0 : rateConfigId.hashCode());
		result = prime * result + ((salesId == null) ? 0 : salesId.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((taxID == null) ? 0 : taxID.hashCode());
		result = prime * result + ((terminateDate == null) ? 0 : terminateDate.hashCode());
		result = prime * result + ((timeZone == null) ? 0 : timeZone.hashCode());
		result = prime * result + ((timeZoneName == null) ? 0 : timeZoneName.hashCode());
		result = prime * result + ((transactionURLs == null) ? 0 : transactionURLs.hashCode());
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
		Merchant other = (Merchant) obj;
		if (abbreviation == null) {
			if (other.abbreviation != null)
				return false;
		} else if (!abbreviation.equals(other.abbreviation))
			return false;
		if (addressBook == null) {
			if (other.addressBook != null)
				return false;
		} else if (!addressBook.equals(other.addressBook))
			return false;
		if (agentId == null) {
			if (other.agentId != null)
				return false;
		} else if (!agentId.equals(other.agentId))
			return false;
		if (merchantCode == null) {
			if (other.merchantCode != null)
				return false;
		} else if (!merchantCode.equals(other.merchantCode))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (contactEmail == null) {
			if (other.contactEmail != null)
				return false;
		} else if (!contactEmail.equals(other.contactEmail))
			return false;
		if (contactPerson == null) {
			if (other.contactPerson != null)
				return false;
		} else if (!contactPerson.equals(other.contactPerson))
			return false;
		if (contactPhone == null) {
			if (other.contactPhone != null)
				return false;
		} else if (!contactPhone.equals(other.contactPhone))
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		if (federalID == null) {
			if (other.federalID != null)
				return false;
		} else if (!federalID.equals(other.federalID))
			return false;
		if (industry == null) {
			if (other.industry != null)
				return false;
		} else if (!industry.equals(other.industry))
			return false;
		if (mcc == null) {
			if (other.mcc != null)
				return false;
		} else if (!mcc.equals(other.mcc))
			return false;
		if (merchantType == null) {
			if (other.merchantType != null)
				return false;
		} else if (!merchantType.equals(other.merchantType))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (parentID == null) {
			if (other.parentID != null)
				return false;
		} else if (!parentID.equals(other.parentID))
			return false;
		if (pspId == null) {
			if (other.pspId != null)
				return false;
		} else if (!pspId.equals(other.pspId))
			return false;
		if (rateConfigId == null) {
			if (other.rateConfigId != null)
				return false;
		} else if (!rateConfigId.equals(other.rateConfigId))
			return false;
		if (salesId == null) {
			if (other.salesId != null)
				return false;
		} else if (!salesId.equals(other.salesId))
			return false;
		if (status != other.status)
			return false;
		if (taxID == null) {
			if (other.taxID != null)
				return false;
		} else if (!taxID.equals(other.taxID))
			return false;
		if (terminateDate == null) {
			if (other.terminateDate != null)
				return false;
		} else if (!terminateDate.equals(other.terminateDate))
			return false;
		if (timeZone == null) {
			if (other.timeZone != null)
				return false;
		} else if (!timeZone.equals(other.timeZone))
			return false;
		if (timeZoneName == null) {
			if (other.timeZoneName != null)
				return false;
		} else if (!timeZoneName.equals(other.timeZoneName))
			return false;
		if (transactionURLs == null) {
			if (other.transactionURLs != null)
				return false;
		} else if (!transactionURLs.equals(other.transactionURLs))
			return false;
		return true;
	}

}
