package com.aurfy.haze.web.controller.payment;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurfy.haze.service.api.bank.BankService;
import com.aurfy.haze.service.bean.payment.PayResponse;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.web.vo.payment.PayQueryReqVO;

@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class QueryResultController {

	private static final Logger logger = LoggerFactory.getLogger(QueryResultController.class);
	
	@RequestMapping(value = "/queryResult/queryExpressPayResult", method = RequestMethod.POST)
	@ResponseBody
	public String queryResult(HttpServletRequest request, @ModelAttribute("payQueryReq") @Validated PayQueryReqVO payQueryReq,
			BindingResult errors) {
		try {
			//TODO 是否需要添加signature的验证
			BankService bankService = ServiceClient.createBankService();
			PayResponse payResponse = bankService.queryResultByOrderId(payQueryReq.getOrderId());
			if(payResponse != null){
				return payResponse.getStatus().toString();
			}
			return null;
		} catch (ServiceException e) {
			logger.error("generate signature faild", e);
			throw new RuntimeException("generate signature faild", e);
		}
	}
}
