package com.aurfy.haze.core.model.txn;

import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.infra.ProductClassifierEnum;

/**
 * Merchant MerTxn
 * 
 * @author hermano
 *
 */
public class MerTxn extends TextualIDModel {

	private MerchantOrder merOrder;
	private TxnStatusEnum txnStatus;
	private String ownerId;
	private ProductClassifierEnum productClassifier;
	private String terminalId;
	private String protocolVer;
	private String charsetEncoding; // TODO: to Charset
	private SignMethod signMethod;

	public MerchantOrder getMerOrder() {
		return merOrder;
	}

	public void setMerOrder(MerchantOrder merOrder) {
		this.merOrder = merOrder;
	}

	public TxnStatusEnum getTxnStatus() {
		return txnStatus;
	}

	public void setTxnStatus(TxnStatusEnum txnStatus) {
		this.txnStatus = txnStatus;
	}

	/**
	 * the userid of sale
	 * @return
	 */
	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public ProductClassifierEnum getProductClassifier() {
		return productClassifier;
	}

	public void setProductClassifier(ProductClassifierEnum productClassifier) {
		this.productClassifier = productClassifier;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getProtocolVer() {
		return protocolVer;
	}

	public void setProtocolVer(String protocolVer) {
		this.protocolVer = protocolVer;
	}

	public String getCharsetEncoding() {
		return charsetEncoding;
	}

	public void setCharsetEncoding(String charsetEncoding) {
		this.charsetEncoding = charsetEncoding;
	}

	public SignMethod getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(SignMethod signMethod) {
		this.signMethod = signMethod;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MerTxn [merOrder=");
		builder.append(merOrder);
		builder.append(", txnStatus=");
		builder.append(txnStatus);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append(", productClassifier=");
		builder.append(productClassifier);
		builder.append(", terminalId=");
		builder.append(terminalId);
		builder.append(", protocolVer=");
		builder.append(protocolVer);
		builder.append(", charsetEncoding=");
		builder.append(charsetEncoding);
		builder.append(", signMethod=");
		builder.append(signMethod);
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
		result = prime * result + ((charsetEncoding == null) ? 0 : charsetEncoding.hashCode());
		result = prime * result + ((merOrder == null) ? 0 : merOrder.hashCode());
		result = prime * result + ((txnStatus == null) ? 0 : txnStatus.hashCode());
		result = prime * result + ((ownerId == null) ? 0 : ownerId.hashCode());
		result = prime * result + ((productClassifier == null) ? 0 : productClassifier.hashCode());
		result = prime * result + ((protocolVer == null) ? 0 : protocolVer.hashCode());
		result = prime * result + ((signMethod == null) ? 0 : signMethod.hashCode());
		result = prime * result + ((terminalId == null) ? 0 : terminalId.hashCode());
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
		MerTxn other = (MerTxn) obj;
		if (charsetEncoding == null) {
			if (other.charsetEncoding != null)
				return false;
		} else if (!charsetEncoding.equals(other.charsetEncoding))
			return false;
		if (merOrder == null) {
			if (other.merOrder != null)
				return false;
		} else if (!merOrder.equals(other.merOrder))
			return false;
		if (txnStatus != other.txnStatus)
			return false;
		if (ownerId == null) {
			if (other.ownerId != null)
				return false;
		} else if (!ownerId.equals(other.ownerId))
			return false;
		if (productClassifier != other.productClassifier)
			return false;
		if (protocolVer == null) {
			if (other.protocolVer != null)
				return false;
		} else if (!protocolVer.equals(other.protocolVer))
			return false;
		if (signMethod == null) {
			if (other.signMethod != null)
				return false;
		} else if (!signMethod.equals(other.signMethod))
			return false;
		if (terminalId == null) {
			if (other.terminalId != null)
				return false;
		} else if (!terminalId.equals(other.terminalId))
			return false;
		return true;
	}

}
