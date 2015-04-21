package com.aurfy.haze.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SenderController {

	private static final Logger logger = LoggerFactory.getLogger(SenderController.class);

	public SenderController() {
	}

	@RequestMapping("/pay/send.action")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
}
