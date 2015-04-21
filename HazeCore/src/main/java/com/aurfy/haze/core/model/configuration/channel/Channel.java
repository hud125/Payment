package com.aurfy.haze.core.model.configuration.channel;

import java.util.Date;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.configuration.ChannelStatusEnum;

/**
 * 银行通道：可支持不同的交易和结算币种，第三方通道支持多个币种时，配置多条
 * 
 * @author zhangcheng
 *
 */
public class Channel extends TextualIDModel {
	private String acquirerId;
	private String acquirerName;
	private String channelProviderId;
	private String channelProviderName;
	private ChannelProviderClassifier providerClassifier;
	private Currency transactionCurrency;
	private Currency settlementCurrency;
	private CardOrgEnum cardOrg;
	private ChannelStatusEnum status;
	private Date dueDate;
	private String comments;

	public String getAcquirerName() {
		return acquirerName;
	}

	public void setAcquirerName(String acquirerName) {
		this.acquirerName = acquirerName;
	}

	/**
	 * card org: visa，master，jcb，unionpay,etc.
	 * 
	 * @return
	 */
	public CardOrgEnum getCardOrg() {
		return cardOrg;
	}

	public void setCardOrg(CardOrgEnum cardOrg) {
		this.cardOrg = cardOrg;
	}

	/**
	 * channel status: active, inactive, suspend
	 * 
	 * @return
	 */
	public ChannelStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ChannelStatusEnum status) {
		this.status = status;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAcquirerId() {
		return acquirerId;
	}

	public void setAcquirerId(String acquirerId) {
		this.acquirerId = acquirerId;
	}

	public Currency getTransactionCurrency() {
		return transactionCurrency;
	}

	public void setTransactionCurrency(Currency transactionCurrency) {
		this.transactionCurrency = transactionCurrency;
	}

	public Currency getSettlementCurrency() {
		return settlementCurrency;
	}

	public void setSettlementCurrency(Currency settlementCurrency) {
		this.settlementCurrency = settlementCurrency;
	}

	public String getChannelProviderId() {
		return channelProviderId;
	}

	public void setChannelProviderId(String channelProviderId) {
		this.channelProviderId = channelProviderId;
	}

	public ChannelProviderClassifier getProviderClassifier() {
		return providerClassifier;
	}

	public void setProviderClassifier(ChannelProviderClassifier providerName) {
		this.providerClassifier = providerName;
	}

	public String getChannelProviderName() {
		return channelProviderName;
	}

	public void setChannelProviderName(String channelProviderName) {
		this.channelProviderName = channelProviderName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((acquirerId == null) ? 0 : acquirerId.hashCode());
		result = prime * result + ((acquirerName == null) ? 0 : acquirerName.hashCode());
		result = prime * result + ((cardOrg == null) ? 0 : cardOrg.hashCode());
		result = prime * result + ((channelProviderId == null) ? 0 : channelProviderId.hashCode());
		result = prime * result + ((channelProviderName == null) ? 0 : channelProviderName.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((providerClassifier == null) ? 0 : providerClassifier.hashCode());
		result = prime * result + ((settlementCurrency == null) ? 0 : settlementCurrency.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transactionCurrency == null) ? 0 : transactionCurrency.hashCode());
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
		Channel other = (Channel) obj;
		if (acquirerId == null) {
			if (other.acquirerId != null)
				return false;
		} else if (!acquirerId.equals(other.acquirerId))
			return false;
		if (acquirerName == null) {
			if (other.acquirerName != null)
				return false;
		} else if (!acquirerName.equals(other.acquirerName))
			return false;
		if (cardOrg != other.cardOrg)
			return false;
		if (channelProviderId == null) {
			if (other.channelProviderId != null)
				return false;
		} else if (!channelProviderId.equals(other.channelProviderId))
			return false;
		if (channelProviderName == null) {
			if (other.channelProviderName != null)
				return false;
		} else if (!channelProviderName.equals(other.channelProviderName))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (providerClassifier == null) {
			if (other.providerClassifier != null)
				return false;
		} else if (!providerClassifier.equals(other.providerClassifier))
			return false;
		if (settlementCurrency == null) {
			if (other.settlementCurrency != null)
				return false;
		} else if (!settlementCurrency.equals(other.settlementCurrency))
			return false;
		if (status != other.status)
			return false;
		if (transactionCurrency == null) {
			if (other.transactionCurrency != null)
				return false;
		} else if (!transactionCurrency.equals(other.transactionCurrency))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Channel [acquirerId=");
		builder.append(acquirerId);
		builder.append(", acquirerName=");
		builder.append(acquirerName);
		builder.append(", channelProviderId=");
		builder.append(channelProviderId);
		builder.append(", channelProviderName=");
		builder.append(channelProviderName);
		builder.append(", providerClassifier=");
		builder.append(providerClassifier);
		builder.append(", transactionCurrency=");
		builder.append(transactionCurrency);
		builder.append(", settlementCurrency=");
		builder.append(settlementCurrency);
		builder.append(", cardOrg=");
		builder.append(cardOrg);
		builder.append(", status=");
		builder.append(status);
		builder.append(", dueDate=");
		builder.append(dueDate);
		builder.append(", comments=");
		builder.append(comments);
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
