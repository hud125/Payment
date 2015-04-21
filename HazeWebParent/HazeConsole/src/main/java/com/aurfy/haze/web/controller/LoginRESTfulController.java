package com.aurfy.haze.web.controller;

import static com.aurfy.haze.utils.StringUtils.formatMessage;

import java.io.BufferedReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aurfy.haze.core.model.perm.ViewScope;
import com.aurfy.haze.core.model.tools.CipherAlgorithmEnum;
import com.aurfy.haze.service.api.infrastructure.UserService;
import com.aurfy.haze.service.api.perm.PermissionService;
import com.aurfy.haze.service.bean.infra.UserBean;
import com.aurfy.haze.service.bean.infra.UserLoginRequest;
import com.aurfy.haze.service.bean.perm.PermAssignmentBean;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.web.perm.ScopePermission;
import com.aurfy.haze.web.session.SessionConfig;

@Controller
public class LoginRESTfulController {

	private static final Logger logger = LoggerFactory.getLogger(LoginRESTfulController.class);

	@RequestMapping(value = "/loginRESTful", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request) {
		String body = getBody(request);

		JSONObject payload = JSONObject.fromObject(body.toString());
		JSONObject res = new JSONObject();
		if (!payload.containsKey("identication")) {
			res.put("code", "-1");
			res.put("message", "The value of identication is must.");
			return res.toString();
		}

		JSONObject identication = (JSONObject) payload.get("identication");
		String type = identication.getString("type");
		if (!"basic".equals(type)) {
			res.put("code", "-1");
			res.put("message", "type is error!");
			return res.toString();
		}

		JSONObject data = (JSONObject) payload.get("data");
		// checkout username and password
		String username = (String) identication.get("username");
		String password = (String) identication.get("password");
		UserLoginRequest userLogin = new UserLoginRequest();
		userLogin.setUsername(username);
		userLogin.setPassword(password);
		userLogin.setHashPolicy(CipherAlgorithmEnum.SHA3);
		UserBean userBean = null;

		
		try {
			UserService userService = ServiceClient.createUserService();
			userBean = userService.login(userLogin);
			
			//necessary to login, since initialize the user's permission
			PermissionService permService = ServiceClient.createPermissionService();
			List<PermAssignmentBean> permAssignments = permService.listPermission(userBean.getID());
			System.out.println(permAssignments.toString());
		} catch (ServiceException e) {
			final String msg = formatMessage("create service failed or others");
			logger.error(msg, e);
		}
		
		// default set an user in session
		JSONObject res_data = new JSONObject();
		res_data.put("session_id", SessionConfig.setSession(userBean));
		res.put("code", "0");
		res.put("message", "success");
		res.put("data", res_data);
		return res.toString();
	}

	@ScopePermission("HomeController_listUsers")
	@RequestMapping(value = "/listUsers", method = RequestMethod.POST)
	@ResponseBody
	public String listUsers(HttpServletRequest request) {
		String entryKey = (String) request.getAttribute("entryKey");
		ViewScope viewScope = null;
		try {
			PermissionService permService = ServiceClient.createPermissionService();
			viewScope = permService.getViewScopeByEntryKey(entryKey);
			System.out.println(viewScope);
		} catch (ServiceException e) {
			final String msg = formatMessage("create service failed or others");
			logger.error(msg, e);
		}
		return viewScope.toString();
	}

	public static String getBody(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			final String msg = formatMessage("get http request payload failed");
			logger.error(msg, e);
		}
		return sb.toString();
	}

}
