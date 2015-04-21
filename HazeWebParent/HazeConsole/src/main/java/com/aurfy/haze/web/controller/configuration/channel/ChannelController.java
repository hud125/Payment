package com.aurfy.haze.web.controller.configuration.channel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

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

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.Model;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.configuration.ChannelStatusEnum;
import com.aurfy.haze.core.model.configuration.channel.ChannelProviderClassifier;
import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.configuration.channel.ChannelService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.configuration.channel.AcquirerBean;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.utils.SecurityUtils;
import com.aurfy.haze.web.helper.BeanBuilder;
import com.aurfy.haze.web.helper.VOBuilder;
import com.aurfy.haze.web.vo.configuration.channel.ChannelVO;

@Controller
@RequestMapping("channel")
public class ChannelController {

	private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);

	private static final int pageSize = 2;
	/**
	 * 新增或者修改
	 */
	private static final String EDIT_VM = "configuration/channel/edit";
	private static final String LIST_VM = "configuration/channel/list";
	private static final String MESSAGE_VM = "message";
	/**
	 * 操作提示信息
	 */
	private static final String UPDATE_SUCCESS_MESSAGE = "update success";
	private static final String SAVE_SUCCESS_MESSAGE = "save success";
	/**
	 * 跳转列表页面路径
	 */
	private static final String LIST_URL = "/channel/list";

	@RequestMapping("add")
	public ModelAndView add(HttpServletRequest request, @ModelAttribute("channel") ChannelVO channel) {
		return new ModelAndView(EDIT_VM);
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, @ModelAttribute("channel") @Validated ChannelVO channel,
			BindingResult errors) {
		try {
			if (errors.hasErrors()) {
				return new ModelAndView(EDIT_VM);
			}
			ChannelService channelService = ServiceClient.createChannelService();
			ChannelBean bean = BeanBuilder.vo2Bean(ChannelBean.class, channel);
			bean.setID(SecurityUtils.UUID());
			channelService.create(bean);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", SAVE_SUCCESS_MESSAGE);
			map.put("refererURL", LIST_URL);
			return new ModelAndView(MESSAGE_VM, "map", map);
		} catch (Exception e) {
			logger.error("create channel fail: {}", e);
			return new ModelAndView(EDIT_VM);
		}

	}

	@RequestMapping("edit/{id}")
	public ModelAndView edit(HttpServletRequest request, @PathVariable String id) {
		ChannelVO channel = (ChannelVO) createModel();

		return new ModelAndView(EDIT_VM, "channel", channel);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, @ModelAttribute("channel") @Validated ChannelVO channel,
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
		Pagination<ChannelVO> pagination = null;
		int pageNum = NumberUtils.toInt(request.getParameter("page"), 0);
		try {
			CRUDService crudService = ServiceClient.createCRUDService();

			pagination = VOBuilder.bean2VO(ChannelVO.class, crudService.retrieve(ChannelBean.class, pageNum, pageSize));
		} catch (Exception e) {
			logger.error("found no data");
		}

		return new ModelAndView(LIST_VM, "pagination", pagination);
	}

	/**
	 * list all acquirer
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "listAcquirer", method = RequestMethod.GET)
	public ModelAndView listAcquirer(HttpServletRequest request) {

		JSONArray list = new JSONArray();
		// list.add("helllo");
		List<CRUDBean> acquirers = null;
		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			acquirers = crudService.retrieveAll(AcquirerBean.class);
			for (CRUDBean bean : acquirers) {
				list.add((AcquirerBean) bean);
			}

		} catch (Exception e) {
			logger.error("found no data");
		}

		return new ModelAndView("ajax", "data", list);
	}

	private Model createModel() {
		ChannelVO channel = new ChannelVO();
		channel.setID(UUID.randomUUID().toString());
		channel.setAcquirerId(SecurityUtils.UUID());
		channel.setAcquirerName("BCC");
		channel.setChannelProviderId(SecurityUtils.UUID());
		channel.setProviderClassifier(ChannelProviderClassifier.UNIONPAY_EXPRESS_PAY);
		channel.setTransactionCurrency(Currency.CNY);
		channel.setSettlementCurrency(Currency.CNY);
		channel.setCardOrg(CardOrgEnum.MASTER);
		channel.setStatus(ChannelStatusEnum.ACTIVE);
		channel.setDueDate(new Date());
		channel.setComments("test");
		channel.setCreateDate(new Date());
		channel.setUpdateDate(channel.getCreateDate());

		return channel;
	}
}
