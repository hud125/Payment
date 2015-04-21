package com.aurfy.haze.web.controller.configuration.infra;

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
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.utils.DateUtils;

@Controller
@RequestMapping(value = "auditLog")
public class AuditLogController {

	private static final Logger logger = LoggerFactory.getLogger(AuditLogController.class);

	private static final String AUDITLOG_LIST = "infra/auditLog/list";

	private static final String AUDITLOG = "infra/auditLog/auditLog";

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, String id) {
		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			crudService.delete(AuditLogBean.class, id);
		} catch (ServiceException e) {
			final String msg = formatMessage("delete AuditLog failed");
			logger.error(msg, e);
		}
		return list(request);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(AUDITLOG_LIST);
		int pageNum = NumberUtils.toInt(request.getParameter("page"), 0);
		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			Pagination<CRUDBean> paginations = crudService.retrieveBy(AuditLogBean.class, new AuditLogBean(), pageNum,
					10);
			mv.addObject("pagination", paginations);
			mv.addObject("systemModule", SystemModuleEnum.values());
			mv.addObject("auditAction", AuditActionEnum.values());
			mv.addObject("actionResult", ActionResultEnum.values());
		} catch (ServiceException e) {
			final String msg = formatMessage("get AuditLog List failed");
			logger.error(msg, e);
		}
		return mv;
	}

	@RequestMapping(value = "select", method = RequestMethod.GET)
	public ModelAndView select(HttpServletRequest request, String id) {
		ModelAndView mv = new ModelAndView(AUDITLOG);
		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			CRUDBean auditLog = crudService.retrieve(AuditLogBean.class, id);
			mv.addObject("autidLog", auditLog);
		} catch (ServiceException e) {
			final String msg = formatMessage("get AuditLog failed");
			logger.error(msg, e);
		}
		return mv;
	}

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public ModelAndView query(HttpServletRequest request, @ModelAttribute AuditLogBean auditLogBean, String startTime,
			String endTime) {
		ModelAndView mv = new ModelAndView(AUDITLOG_LIST);
		mv.addObject("auditLogBean", auditLogBean);
		mv.addObject("startTime", startTime);
		mv.addObject("endTime", endTime);
		if(startTime != null) {
			auditLogBean.setCreateDate(DateUtils.parseDateAuto(startTime));
		}
		if(endTime != null) {
			auditLogBean.setUpdateDate(DateUtils.parseDateAuto(endTime));
		}
		try {
			CRUDService crudService = ServiceClient.createCRUDService();
			Pagination<CRUDBean> auditLog = crudService.retrieveBy(AuditLogBean.class, auditLogBean, 1, 10);
			mv.addObject("pagination", auditLog);
			mv.addObject("systemModule", SystemModuleEnum.values());
			mv.addObject("auditAction", AuditActionEnum.values());
			mv.addObject("actionResult", ActionResultEnum.values());
		} catch (ServiceException e) {
			final String msg = formatMessage("get AuditLog failed");
			logger.error(msg, e);
		}
		return mv;
	}
}
