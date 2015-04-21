package com.aurfy.haze.web.controller.configuration.channel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.configuration.channel.ChannelProviderClassifier;
import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.utils.SecurityUtils;
import com.aurfy.haze.web.vo.configuration.channel.ChannelProviderVO;

@Controller
@RequestMapping("channelProvider")
public class ChannelProviderController {

	private static final Logger logger = LoggerFactory.getLogger(ChannelProviderController.class);

	private static final int pageSize = 1;
	/**
	 * 新增或者修改
	 */
	private static final String EDIT_VM = "configuration/channel/channelProvider/edit";
	private static final String LIST_VM = "configuration/channel/channelProvider/list";
	private static final String MESSAGE_VM = "message";
	/**
	 * 操作提示信息
	 */
	private static final String UPDATE_SUCCESS_MESSAGE = "update success";
	private static final String SAVE_SUCCESS_MESSAGE = "save success";
	/**
	 * 跳转列表页面路径
	 */
	private static final String LIST_URL = "/channelProvider/list/";

	@RequestMapping("add")
	public ModelAndView add(HttpServletRequest request) {
		ChannelProviderVO channelProvider = new ChannelProviderVO();

		return new ModelAndView(EDIT_VM, "channelProvider", channelProvider);
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request,
			@ModelAttribute("channelProvider") @Validated ChannelProviderVO channelProvider, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ModelAndView(EDIT_VM);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", SAVE_SUCCESS_MESSAGE);
		map.put("refererURL", LIST_URL + channelProvider.getAcquirerId());
		return new ModelAndView(MESSAGE_VM, "map", map);
	}

	@RequestMapping("edit/{id}")
	public ModelAndView edit(HttpServletRequest request, @PathVariable String id) {
		ChannelProviderVO channelProvider = (ChannelProviderVO) createModel();

		return new ModelAndView(EDIT_VM, "channelProvider", channelProvider);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request,
			@ModelAttribute("channelProvider") @Validated ChannelProviderVO channelProvider, BindingResult errors) {
		if (errors.hasErrors()) {
			return new ModelAndView(EDIT_VM);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", UPDATE_SUCCESS_MESSAGE);
		map.put("refererURL", LIST_URL + channelProvider.getAcquirerId());
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
		Pagination<ChannelProviderVO> pagination = null;
		int pageNum = NumberUtils.toInt(request.getParameter("page"), 0);
		pagination = createPagination();

		return new ModelAndView(LIST_VM, "pagination", pagination);
	}

	private Pagination createPagination() {

		Pagination pagination = new Pagination(10, 1, 5);
		pagination.setPageData(createList());

		return pagination;
	}

	private List<Model> createList() {
		List<Model> list = new ArrayList<Model>();
		for (int i = 0; i <= 10; i++) {
			list.add(createModel());
		}

		return list;
	}

	private Model createModel() {
		ChannelProviderVO model = new ChannelProviderVO();
		model.setID(UUID.randomUUID().toString());
		model.setAcquirerId(SecurityUtils.UUID());
		model.setProviderClassifier(ChannelProviderClassifier.UNIONPAY_EXPRESS_PAY);
		model.setTransactionCurrencies("USD");
		model.setSettlementCurrencies("CNY");
		model.setCardOrg(CardOrgEnum.VISA);
		model.setCreateDate(new Date());
		model.setUpdateDate(model.getCreateDate());
		model.setOwnerId("cheng201");
		model.setSupport3D(true);
		model.setSupportCardNoTrasmit(true);

		return model;
	}
}
