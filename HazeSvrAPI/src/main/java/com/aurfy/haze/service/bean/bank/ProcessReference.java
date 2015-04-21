package com.aurfy.haze.service.bean.bank;

/**
 * the reference object for bank processing. <br/>
 * client should use this to check the [payment] response status.<br/>
 * this is for internal use only, not for query API.
 * 
 * @author hermano
 *
 */
public class ProcessReference {

	private String processId;

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}
	
}
