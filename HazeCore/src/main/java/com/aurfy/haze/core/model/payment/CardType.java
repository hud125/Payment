package com.aurfy.haze.core.model.payment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author hermano
 *
 */
public class CardType implements Serializable {

	private static final long serialVersionUID = -1102776930090164731L;
	
	private static Map<String, CardType> codeMap = new HashMap<String, CardType>(5);
	private static List<CardType> allValues = new ArrayList<CardType>(5);

	/**
	 * All Card
	 */
	public static final CardType AllCards = new CardType("*", "allCards");

	/**
	 * 借记卡
	 */
	public static final CardType DebitCard = new CardType("01", "debitCard");

	/**
	 * 贷记卡
	 */
	public static final CardType CreditCard = new CardType("02", "creditCard");

	/**
	 * 预付费卡
	 */
	public static final CardType PrepaidCard = new CardType("03", "prepaidCard");

	private String code;
	private String caption;

	private CardType(String code, String caption) {
		this.code = code;
		this.caption = caption;
		codeMap.put(code, this);
		allValues.add(this);
	}

	public String getCode() {
		return code;
	}

	public String getCaption() {
		return caption;
	}

	public static CardType parseByCode(String code) {
		return codeMap.get(code);
	}

	public static List<CardType> values() {
		return Collections.unmodifiableList(allValues);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CardType [code=");
		builder.append(code);
		builder.append(", caption=");
		builder.append(caption);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caption == null) ? 0 : caption.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		CardType other = (CardType) obj;
		if (caption == null) {
			if (other.caption != null)
				return false;
		} else if (!caption.equals(other.caption))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}


}
