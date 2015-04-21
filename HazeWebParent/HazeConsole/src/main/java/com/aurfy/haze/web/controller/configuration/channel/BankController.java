package com.aurfy.haze.web.controller.configuration.channel;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.configuration.channel.BankBean;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.service.exceptions.ObjectNotFoundException;
import com.aurfy.haze.web.helper.BeanBuilder;
import com.aurfy.haze.web.helper.VOBuilder;
import com.aurfy.haze.web.vo.configuration.channel.BankVO;

@Controller
@RequestMapping(value = "/bank")
public class BankController {

	private static final Logger logger = LoggerFactory.getLogger(BankController.class);

	private static final int pageSize = 1;

	/**
	 * list bank, have pagination
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		Pagination<BankVO> pagination = null;
		int pageNum = NumberUtils.toInt(request.getParameter("page"), 0);
		try {
			CRUDService crudService = ServiceClient.createCRUDService();

			pagination = VOBuilder.bean2VO(BankVO.class, crudService.retrieve(BankBean.class, pageNum, pageSize));

		} catch (Exception e) {
			logger.error("find banks fail: {}", e);
		}

		return new ModelAndView("configuration/channel/bank/list", "pagination", pagination);
	}

	/**
	 * to add page
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request, @ModelAttribute("bank") @Validated BankVO bank,
			BindingResult errors) {
		return new ModelAndView("configuration/channel/bank/add");
	}

	/**
	 * delete bank
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @PathVariable String id) {
		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			crudService.delete(BankBean.class, id);
		} catch (ObjectNotFoundException e) {
			logger.error("find bank fail: {}", e);
			return new ModelAndView("redirect:" + "/bank/list");
		} catch (Exception e) {
			logger.error("find bank fail: {}", e);
			return new ModelAndView("redirect:" + "/bank/list");
		}

		return new ModelAndView("redirect:" + "/bank/list");
	}

	/**
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request, @PathVariable String id) {
		BankVO bankVO = null;
		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			bankVO = VOBuilder.bean2VO(BankVO.class, crudService.retrieve(BankBean.class, id));
		} catch (ObjectNotFoundException e) {
			logger.error("find bank fail: {}", e);
			return new ModelAndView("redirect:" + "/bank/list");
		} catch (Exception e) {
			logger.error("find bank fail: {}", e);
			return new ModelAndView("redirect:" + "/bank/list");
		}

		return new ModelAndView("configuration/channel/bank/edit", "bank", bankVO);
	}

	/**
	 * 
	 * create a new bank record
	 * 
	 * @param request
	 * @param bank
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, @Validated BankVO bank) {

		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			String ownerId = "1";
			bank.setOwnerId(ownerId);
			crudService.create(BeanBuilder.vo2Bean(BankBean.class, bank));
		} catch (Exception e) {
			logger.error("find bank fail: {}", e);
			return new ModelAndView("redirect:" + "/bank/list");
		}

		return new ModelAndView("redirect:" + "/bank/list");
	}

	/**
	 * 
	 * update bank
	 * 
	 * @param request
	 * @param bank
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, @Validated BankVO bank) {

		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			crudService.update(BeanBuilder.toBankBean(bank));
		} catch (Exception e) {
			logger.error("create bank fail: {}", e);
			return new ModelAndView("configuration/channel/bank/edit", "bank", bank);
		}

		return new ModelAndView("redirect:" + "/bank/list");
	}
}
