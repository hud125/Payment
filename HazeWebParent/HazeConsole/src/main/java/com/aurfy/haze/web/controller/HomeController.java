package com.aurfy.haze.web.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		String lang = request.getParameter("lang");
		String[] allLangs = new String[] { "en", "zh" };
		if (StringUtils.isNotEmpty(lang) && Arrays.asList(allLangs).contains(lang)) {
			return new ModelAndView("index", "cacheUpdate", 1);
		}
		return new ModelAndView("index");
	}

	@RequestMapping("test")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response, String name) {
		response.setCharacterEncoding("UTF-8");
		// System.out.println(request.getSession().get);
		request.setAttribute("name1", name);
		return new ModelAndView("test", "name", name);
	}

	@RequestMapping("left")
	public ModelAndView left(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("left");
	}
}