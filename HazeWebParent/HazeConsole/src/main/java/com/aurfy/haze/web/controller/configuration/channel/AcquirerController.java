package com.aurfy.haze.web.controller.configuration.channel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

import com.aurfy.haze.core.model.Model;
import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.configuration.channel.AcquirerBean;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.web.helper.BeanBuilder;
import com.aurfy.haze.web.helper.VOBuilder;
import com.aurfy.haze.web.vo.configuration.channel.AcquirerVO;

@Controller
@RequestMapping("acquirer")
public class AcquirerController {

	private static final Logger logger = LoggerFactory.getLogger(AcquirerController.class);

	private static final int pageSize = 10;
	/**
	 * 新增或者修改
	 */
	private static final String EDIT_VM = "configuration/channel/acquirer/edit";
	private static final String ADD_VM = "configuration/channel/acquirer/add";
	private static final String LIST_VM = "configuration/channel/acquirer/list";
	private static final String MESSAGE_VM = "message";
	/**
	 * 操作提示信息
	 */
	private static final String UPDATE_SUCCESS_MESSAGE = "update success";
	private static final String SAVE_SUCCESS_MESSAGE = "save success";
	/**
	 * 跳转列表页面路径
	 */
	private static final String LIST_URL = "/acquirer/list";

	@RequestMapping("add")
	public ModelAndView add(HttpServletRequest request, @ModelAttribute("acquirer") AcquirerVO acquirer) {
		return new ModelAndView(ADD_VM);
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, @ModelAttribute("acquirer") @Validated AcquirerVO acquirer,
			BindingResult errors) throws ServiceException {
		if (errors.hasErrors()) {
			return new ModelAndView(EDIT_VM);
		}

		CRUDService crudService = ServiceClient.createCRUDService();
		acquirer.setOwnerId("test");

		AcquirerBean acquirerBean = BeanBuilder.vo2Bean(AcquirerBean.class, acquirer);
		crudService.create(acquirerBean);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", SAVE_SUCCESS_MESSAGE);
		map.put("refererURL", LIST_URL);
		return new ModelAndView(MESSAGE_VM, "map", map);
	}

	@RequestMapping("edit/{id}")
	public ModelAndView edit(HttpServletRequest request, @PathVariable String id) {
		AcquirerVO acquirer = (AcquirerVO) createModel();

		return new ModelAndView(EDIT_VM, "acquirer", acquirer);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, @ModelAttribute("acquirer") @Validated AcquirerVO acquirer,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return new ModelAndView(EDIT_VM);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", UPDATE_SUCCESS_MESSAGE);
		map.put("refererURL", LIST_URL);
		return new ModelAndView(MESSAGE_VM, "map", map);
	}

	/**
	 * list , have pagination
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {

		Pagination<AcquirerVO> pagination = null;
		int pageNum = NumberUtils.toInt(request.getParameter("page"), 0);
		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			pagination = VOBuilder.bean2VO(AcquirerVO.class,
					crudService.retrieve(AcquirerBean.class, pageNum, pageSize));
		} catch (Exception e) {
			logger.error("found no data");
		}

		return new ModelAndView(LIST_VM, "pagination", pagination);

	}

	private Model createModel() {
		AcquirerVO model = new AcquirerVO();
		model.setID("1213");
		model.setAcquirerName("阿里巴巴");
		model.setCreateDate(new Date());
		model.setComments("just for test");

		return model;
	}

}
