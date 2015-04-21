package com.aurfy.haze.web.controller.txn;

import static com.aurfy.haze.utils.StringUtils.formatMessage;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.core.model.system.ActionResultEnum;
import com.aurfy.haze.core.model.system.AuditActionEnum;
import com.aurfy.haze.core.model.system.SystemModuleEnum;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.infra.AuditLogBean;
import com.aurfy.haze.service.bean.txn.MerTxnBean;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.utils.DateUtils;
import com.aurfy.haze.web.controller.configuration.infra.AuditLogController;
import com.aurfy.haze.web.helper.VOBuilder;
import com.aurfy.haze.web.vo.txn.MerTxnVO;


@Controller
@RequestMapping(value = "merTxn")
public class MerTxnController {

	private static final String MERTXN_LIST = "merTxn/list";
	private static final String MERTXN = "merTxn/merTxn";

	private static final Logger logger = LoggerFactory.getLogger(MerTxnController.class);
	
	private static final int pageSize = 10;
	
	@RequestMapping("")
	public ModelAndView index(HttpServletRequest request, @ModelAttribute("merTxn") MerTxnVO merTxn) {
		return new ModelAndView("merTxn/list");
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(MERTXN_LIST);
		int pageNum = NumberUtils.toInt(request.getParameter("page"), 0);
		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			Pagination<CRUDBean> paginations = crudService.retrieveBy(MerTxnBean.class, new MerTxnBean(), pageNum,
					10);
			mv.addObject("pagination", paginations);
			
//			mv.addObject("systemModule", SystemModuleEnum.values());
//			mv.addObject("auditAction", AuditActionEnum.values());
//			mv.addObject("actionResult", ActionResultEnum.values());
		} catch (ServiceException e) {
			final String msg = formatMessage("get MerTxn List failed");
			logger.error(msg, e);
		}
		return mv;
	}
	
	@RequestMapping(value = "select", method = RequestMethod.GET)
	public ModelAndView select(HttpServletRequest request, String id) {
		ModelAndView mv = new ModelAndView(MERTXN);
		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			CRUDBean merTxn = crudService.retrieve(MerTxnBean.class, id);
			mv.addObject("merTxn", merTxn);
		} catch (ServiceException e) {
			final String msg = formatMessage("get MerTxn failed");
			logger.error(msg, e);
		}
		return mv;
	}


}
