package com.aurfy.haze.utils.rule;

import java.io.Serializable;

/**
 * @author Hermano
 * 
 */
public enum Logic implements Serializable {
	And("&&"), Or("||");

	private Logic(String connector) {
		this.connector = connector;
	}

	private String connector;

	public String getConnector() {
		return connector;
	}

	public void setConnector(String connector) {
		this.connector = connector;
	}

	public static Logic parse(String code) {
		for (Logic result : values()) {
			if (result.getConnector().equals(code)) {
				return result;
			}
		}
		return null;
	}
}
