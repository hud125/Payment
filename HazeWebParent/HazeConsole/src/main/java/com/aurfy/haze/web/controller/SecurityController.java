package com.aurfy.haze.web.controller;

import static com.aurfy.haze.utils.StringUtils.formatMessage;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurfy.haze.service.api.security.SecurityService;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.service.exceptions.ServiceException;

@Controller
public class SecurityController {

	private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

	@RequestMapping(value = "/generateRSAPair", method = RequestMethod.POST)
	@ResponseBody
	public String generateRSAPair(HttpServletRequest request) {
		try {
			//1.get keyPair by the RSA algorithm
			SecurityService secService = ServiceClient.createSecurityService();
			
		} catch (ServiceException e) {
			final String msg = formatMessage("create service failed or others");
			logger.error(msg, e);
		}
		return "success";
	}

}
