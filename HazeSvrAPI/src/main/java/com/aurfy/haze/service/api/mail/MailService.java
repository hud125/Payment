package com.aurfy.haze.service.api.mail;

import com.aurfy.haze.service.api.HazeService;
import com.aurfy.haze.service.bean.mail.MailBean;


public interface MailService extends HazeService{

	/**
	 * @param mailBean
	 * @return
	 */
	public boolean sendMail(MailBean mailBean);
	
}
