package com.aurfy.haze.web.vo;

public class BaseVO implements VO {

	private String protocolVer;
	private String charsetEncoding; // TODO: to Charset
	private String signMethod;
	private String signature;

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

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		this.signMethod = signMethod;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
