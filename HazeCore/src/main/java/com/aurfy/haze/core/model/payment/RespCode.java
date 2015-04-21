package com.aurfy.haze.core.model.payment;

/**
 * Response code
 * 
 * @author hermano
 *
 */
public class RespCode {

	private String rawRespCode;
	private HazeRespCode mappedRespCode;

	public RespCode() {
	}

	public String getRawRespCode() {
		return rawRespCode;
	}

	public void setRawRespCode(String rawRespCode) {
		this.rawRespCode = rawRespCode;
	}

	public HazeRespCode getMappedRespCode() {
		return mappedRespCode;
	}

	public void setMappedRespCode(HazeRespCode mappedRespCode) {
		this.mappedRespCode = mappedRespCode;
	}

}
