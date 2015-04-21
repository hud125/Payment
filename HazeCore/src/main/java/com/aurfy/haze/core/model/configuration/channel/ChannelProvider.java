package com.aurfy.haze.core.model.configuration.channel;

import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;

public class ChannelProvider extends TextualIDModel {

	private String acquirerId;
	private String providerName;
	/**
	 * 每一个provider都有一个处理银行业务的handler
	 */
	private ChannelProviderClassifier providerClassifier;
	/**
	 * 银行账户Id
	 */
	private String bankAccountId;
	private String transactionCurrencies;
	private String settlementCurrencies;
	private CardOrgEnum cardOrg;
	private boolean supportCardNoTrasmit;
	private boolean support3D;
	private boolean supportDCC;
	/**
	 * 渠道供应属性集 json格式字符串
	 */
	private String channelParamKeys;
	private String comments;
	private String ownerId;

	public boolean isSupportCardNoTrasmit() {
		return supportCardNoTrasmit;
	}

	public void setSupportCardNoTrasmit(boolean supportCardNoTrasmit) {
		this.supportCardNoTrasmit = supportCardNoTrasmit;
	}

	public boolean isSupport3D() {
		return support3D;
	}

	public void setSupport3D(boolean support3d) {
		support3D = support3d;
	}

	public boolean isSupportDCC() {
		return supportDCC;
	}

	public void setSupportDCC(boolean supportDCC) {
		this.supportDCC = supportDCC;
	}

	public String getTransactionCurrencies() {
		return transactionCurrencies;
	}

	public void setTransactionCurrencies(String transactionCurrencies) {
		this.transactionCurrencies = transactionCurrencies;
	}

	public String getSettlementCurrencies() {
		return settlementCurrencies;
	}

	public void setSettlementCurrencies(String settlementCurrencies) {
		this.settlementCurrencies = settlementCurrencies;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getAcquirerId() {
		return acquirerId;
	}

	public void setAcquirerId(String acquirerId) {
		this.acquirerId = acquirerId;
	}

	public ChannelProviderClassifier getProviderClassifier() {
		return providerClassifier;
	}

	public void setProviderClassifier(ChannelProviderClassifier providerClassifier) {
		this.providerClassifier = providerClassifier;
	}

	public CardOrgEnum getCardOrg() {
		return cardOrg;
	}

	public void setCardOrg(CardOrgEnum cardOrg) {
		this.cardOrg = cardOrg;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(String bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public String getChannelParamKeys() {
		return channelParamKeys;
	}

	public void setChannelParamKeys(String channelParamKeys) {
		this.channelParamKeys = channelParamKeys;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((acquirerId == null) ? 0 : acquirerId.hashCode());
		result = prime * result + ((bankAccountId == null) ? 0 : bankAccountId.hashCode());
		result = prime * result + ((cardOrg == null) ? 0 : cardOrg.hashCode());
		result = prime * result + ((channelParamKeys == null) ? 0 : channelParamKeys.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((providerClassifier == null) ? 0 : providerClassifier.hashCode());
		result = prime * result + ((providerName == null) ? 0 : providerName.hashCode());
		result = prime * result + ((settlementCurrencies == null) ? 0 : settlementCurrencies.hashCode());
		result = prime * result + (support3D ? 1231 : 1237);
		result = prime * result + (supportCardNoTrasmit ? 1231 : 1237);
		result = prime * result + (supportDCC ? 1231 : 1237);
		result = prime * result + ((transactionCurrencies == null) ? 0 : transactionCurrencies.hashCode());
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
		ChannelProvider other = (ChannelProvider) obj;
		if (acquirerId == null) {
			if (other.acquirerId != null)
				return false;
		} else if (!acquirerId.equals(other.acquirerId))
			return false;
		if (bankAccountId == null) {
			if (other.bankAccountId != null)
				return false;
		} else if (!bankAccountId.equals(other.bankAccountId))
			return false;
		if (cardOrg != other.cardOrg)
			return false;
		if (channelParamKeys == null) {
			if (other.channelParamKeys != null)
				return false;
		} else if (!channelParamKeys.equals(other.channelParamKeys))
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
		if (providerClassifier == null) {
			if (other.providerClassifier != null)
				return false;
		} else if (!providerClassifier.equals(other.providerClassifier))
			return false;
		if (providerName == null) {
			if (other.providerName != null)
				return false;
		} else if (!providerName.equals(other.providerName))
			return false;
		if (settlementCurrencies == null) {
			if (other.settlementCurrencies != null)
				return false;
		} else if (!settlementCurrencies.equals(other.settlementCurrencies))
			return false;
		if (support3D != other.support3D)
			return false;
		if (supportCardNoTrasmit != other.supportCardNoTrasmit)
			return false;
		if (supportDCC != other.supportDCC)
			return false;
		if (transactionCurrencies == null) {
			if (other.transactionCurrencies != null)
				return false;
		} else if (!transactionCurrencies.equals(other.transactionCurrencies))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelProvider [acquirerId=");
		builder.append(acquirerId);
		builder.append(", providerName=");
		builder.append(providerName);
		builder.append(", providerClassifier=");
		builder.append(providerClassifier);
		builder.append(", bankAccountId=");
		builder.append(bankAccountId);
		builder.append(", transactionCurrencies=");
		builder.append(transactionCurrencies);
		builder.append(", settlementCurrencies=");
		builder.append(settlementCurrencies);
		builder.append(", cardOrg=");
		builder.append(cardOrg);
		builder.append(", supportCardNoTrasmit=");
		builder.append(supportCardNoTrasmit);
		builder.append(", support3D=");
		builder.append(support3D);
		builder.append(", supportDCC=");
		builder.append(supportDCC);
		builder.append(", channelParamKeys=");
		builder.append(channelParamKeys);
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

}
