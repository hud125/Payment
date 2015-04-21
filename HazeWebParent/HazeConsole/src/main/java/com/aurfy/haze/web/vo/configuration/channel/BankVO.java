package com.aurfy.haze.web.vo.configuration.channel;

import org.hibernate.validator.constraints.NotBlank;

import com.aurfy.haze.core.model.configuration.channel.Bank;
import com.aurfy.haze.web.vo.VO;

public class BankVO extends Bank implements VO {

	@NotBlank(message = "{bank.code.not.empty}")
	public String getBankCode() {
		return super.getBankCode();
	}

	@NotBlank
	public String getBankAbbreviation() {
		return super.getBankAbbreviation();
	}

	public String getBankName() {
		return super.getBankName();
	}

	public String getBankBranch() {
		return super.getBankBranch();
	}

	public String getBankURL() {
		return super.getBankURL();
	}

	public String getComments() {
		return super.getComments();
	}

	public String getOwner() {
		return super.getOwnerId();
	}

}
