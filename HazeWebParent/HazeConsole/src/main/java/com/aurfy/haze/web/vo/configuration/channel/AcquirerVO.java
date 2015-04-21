package com.aurfy.haze.web.vo.configuration.channel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.aurfy.haze.core.model.configuration.channel.Acquirer;
import com.aurfy.haze.web.vo.VO;

public class AcquirerVO extends Acquirer implements VO {

	@NotBlank(message = "{acquirer.name.not.empty}")
	@Length(min = 3)
	public String getAcquirerName() {
		return super.getAcquirerName();
	}
}
