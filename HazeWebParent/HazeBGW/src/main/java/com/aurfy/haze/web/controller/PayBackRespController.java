package com.aurfy.haze.web.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurfy.haze.service.api.bank.BankPostProcessService;
import com.aurfy.haze.service.client.ServiceClient;

@Controller
public class PayBackRespController {

	private static final Logger logger = LoggerFactory.getLogger(PayBackRespController.class);

	private static final String DEFAULT_ENCODING = "UTF-8";

	@RequestMapping(value = "/payBackResp/{bankOrderId}", method = RequestMethod.POST)
	@ResponseBody
	public String payBackResp(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("bankOrderId") String bankOrderId) {
		logger.debug("ENTER THE PayBackRespController");
		try {
			request.setCharacterEncoding(DEFAULT_ENCODING);
			BankPostProcessService service = ServiceClient.createBankPostProcessService();
			Map<String, String> map = toMap(request.getParameterMap());
			service.onCallback(bankOrderId, map);
		} catch (Exception e) {
			logger.error("error happen to PayBackRespController ", e);
		}

		return null;
	}

	private Map<String, String> toMap(Map<String, String[]> param) {
		Iterator<Entry<String, String[]>> entries = param.entrySet().iterator();
		Map<String, String> map = new HashMap<String, String>(param.size());
		while (entries.hasNext()) {
			Entry<String, String[]> entry = entries.next();
			String value = entry.getValue()[0];
			map.put(entry.getKey(), value);
		}
		return map;
	}

}
