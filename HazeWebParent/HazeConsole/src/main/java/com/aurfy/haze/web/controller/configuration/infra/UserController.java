package com.aurfy.haze.web.controller.configuration.infra;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aurfy.haze.core.model.infra.UserStatusEnum;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.infrastructure.UserService;
import com.aurfy.haze.service.bean.infra.UserBean;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.service.exceptions.ParameterValidationException;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.utils.SecurityUtils;
import com.aurfy.haze.utils.StringUtils;

@Controller
@RequestMapping(value = "user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static final String GETBACKPASSWORD_MAIN_PAGE = "user/getBackPassWord";

	private static final String CREATEUSER_MAIN_PAGE = "user/applicationAccount";

	private static final String CREATEUSER_OVER_JUMP = "user/application_Overjump";

	private static final String JSON_SUCCESS = "{msg:'success', data:'ok'}";

	private static final String APPLICATION_ACCOUNT_SUCC = "{msg:'success', data:'Application account is success'}";

	private static final String APPLICATION_ACCOUNT_FAIL = "{msg:'success', data:'Application account is fail'}";

	private static final String JSON_USER_OCCUPIED = "{msg:'error', data:'This user name is occupied'}";

	private static final String JSON_NOT_FOUND_USER = "{msg:'error', data:'Not find user'}";

	private static final String JSON_USERNAME_NULL = "{msg:'error', data:'The username is null'}";

	private static final String JSON_VALIDCODE_NULL = "{msg:'error', data:'The validCode is null'}";

	private static final String JSON_PASSWORD_NULL = "{msg:'error', data:'The password is null'}";

	private static final String JSON_SECRET_KEY_NULL = "{msg:'error', data:'The secret key is null'}";

	private static final String JSON_SEND_EMAIL_FIAL = "{msg:'error', data:'send email fial'}";

	private static final String JSON_AUTH_KEY_ERROR = "{msg:'error', data:'secret Key is failure or timeout'}";

	private static final String JSON_RESETPASSWORD_FAIL = "{msg:'error', data:'reset pasword is fail'}";

	private static final String JSON_VALID_AUTH_KEY = "{msg:'error', data:'validate secret Key Fail'}";

	private static final String JSON_VALIDCODE_TIMEOUT = "{msg:'error', data:'The validCode is timeout'}";

	private static final String JSON_VALIDCODE_FAIL = "{msg:'error', data:'validCode is fail'}";

	@RequestMapping(value = "application", method = RequestMethod.GET)
	public ModelAndView applicationAccount(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(CREATEUSER_MAIN_PAGE);
		return mv;
	}

	@RequestMapping(value = "validUserName", method = RequestMethod.GET)
	@ResponseBody
	public String validUserName(String userName) {
		if (!StringUtils.isNotBlank(StringUtils.trim(userName))) {
			return JSON_USERNAME_NULL;
		} else {
			try {
				UserService userService = ServiceClient.createUserService();
				UserBean userBean = userService.selectUserByName(userName);
				if (userBean != null && StringUtils.equals(userBean.getName(), userName)) {
					return JSON_USER_OCCUPIED;
				}
			} catch (RuntimeServiceException e) {
				return JSON_SUCCESS;
			} catch (ServiceException e) {
				return JSON_SUCCESS;
			}
		}
		return JSON_SUCCESS;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ModelAndView createAccount(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(CREATEUSER_OVER_JUMP);
		UserBean userBean = new UserBean();
		String username = StringUtils.trim(request.getParameter("username"));
		if (StringUtils.isNotBlank(username)) {
			userBean.setName(username);
		} else {
			mv.addObject("msgPage", APPLICATION_ACCOUNT_FAIL);
			return mv;
		}
		String email = StringUtils.trim(request.getParameter("email"));
		if (StringUtils.isNotBlank(email)) {
			userBean.setEmail(email);
		} else {
			mv.addObject("msgPage", APPLICATION_ACCOUNT_FAIL);
			return mv;
		}
		String firstName = StringUtils.trim(request.getParameter("firstName"));
		if (StringUtils.isNotBlank(firstName)) {
			userBean.setFirstName(firstName);
		} else {
			mv.addObject("msgPage", APPLICATION_ACCOUNT_FAIL);
			return mv;
		}
		String lastName = StringUtils.trim(request.getParameter("lastName"));
		if (StringUtils.isNotBlank(lastName)) {
			userBean.setLastName(lastName);
		} else {
			mv.addObject("msgPage", APPLICATION_ACCOUNT_FAIL);
			return mv;
		}
		String remarks = StringUtils.trim(request.getParameter("remarks"));
		userBean.setComments(remarks);
		String securityQuestion = request.getParameter("securityQuestion");
		if (StringUtils.isNotBlank(StringUtils.trim(securityQuestion))) {
			userBean.setSecurityQuestion(securityQuestion);
		} else {
			mv.addObject("msgPage", APPLICATION_ACCOUNT_FAIL);
			return mv;
		}
		String securityAnswer = request.getParameter("securityAnswer");
		if (StringUtils.isNotBlank(StringUtils.trim(securityAnswer))) {
			userBean.setSecurityAnswer(securityAnswer);
		} else {
			mv.addObject("msgPage", APPLICATION_ACCOUNT_FAIL);
			return mv;
		}
		String password = StringUtils.trim(request.getParameter("password"));
		if (StringUtils.isNotBlank(password)) {
			Date date = new Date();
			String passwdSalt = RandomStringUtils.randomAlphanumeric(6);
			userBean.setEncryptedPasswd(SecurityUtils.SHA3(SecurityUtils.SHA3(password) + passwdSalt));
			userBean.setPasswdSalt(passwdSalt);
			userBean.setLicenseAgreed(false);
			userBean.setRegIP(request.getRemoteAddr());
			userBean.setStatus(UserStatusEnum.ACTIVE);
			userBean.setCreateDate(date);
			try {
				CRUDService crudService = ServiceClient.createCRUDService();
				UserBean bean = (UserBean) crudService.create(userBean);
				if(StringUtils.equals(bean.getName(), username)) {
					mv.addObject("msgPage", APPLICATION_ACCOUNT_SUCC);
				} else {
					mv.addObject("msgPage", APPLICATION_ACCOUNT_FAIL);
					return mv;
				}
			} catch (RuntimeServiceException e) {
				logger.error("create user", e);
				mv.addObject("msgPage", APPLICATION_ACCOUNT_FAIL);
				return mv;
			} catch (ServiceException e) {
				logger.error("create user", e);
				mv.addObject("msgPage", APPLICATION_ACCOUNT_FAIL);
				return mv;
			}
		} else {
			mv.addObject("msgPage", APPLICATION_ACCOUNT_FAIL);
		}
		return mv;
	}

	@RequestMapping(value = "getBackPassWord", method = RequestMethod.GET)
	public ModelAndView getBackPassWord(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(GETBACKPASSWORD_MAIN_PAGE);
		String userName = StringUtils.trim(request.getParameter("userName"));
		String authKey = StringUtils.trim(request.getParameter("authKey"));
		if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(authKey)) {
			mv.addObject("userName", userName);
			mv.addObject("authKey", authKey);
			try {
				UserService userService = ServiceClient.createUserService();
				if (!userService.validateRecoveryAuthKey(userName, authKey)) {
					// secretKeyFail
					mv.addObject("msgPage", JSON_AUTH_KEY_ERROR);
					return mv;
				}
			} catch (RuntimeServiceException e) {
				// user not find
				mv.addObject("msgPage", JSON_NOT_FOUND_USER);
				return mv;
			} catch (ParameterValidationException e) {
				// time out authkey
				mv.addObject("msgPage", JSON_AUTH_KEY_ERROR);
				return mv;
			} catch (ServiceException e) {
				mv.addObject("msgPage", JSON_VALID_AUTH_KEY);
				return mv;
			}
		} else {
			mv.addObject("userName", "");
			mv.addObject("authKey", "");
		}
		mv.addObject("msgPage", JSON_SUCCESS);
		return mv;
	}

	@RequestMapping(value = "getSecretKey", method = RequestMethod.POST)
	@ResponseBody
	public String sendRecoveryEmail(HttpServletRequest request) {
		String userName = StringUtils.trim(request.getParameter("userName"));
		String validCode = StringUtils.trim(request.getParameter("validCode"));
		if (!StringUtils.isNotBlank(userName)) {
			return JSON_USERNAME_NULL;
		}
		if (!StringUtils.isNotBlank(validCode)) {
			return JSON_VALIDCODE_NULL;
		}
		HttpSession session = request.getSession();
		if (!StringUtils.equals((String) session.getAttribute("validCode"), validCode)) {
			return JSON_VALIDCODE_FAIL;
		}
		Long date = new Date().getTime();
		if (date > (Long) session.getAttribute("validCodeTime")) {
			return JSON_VALIDCODE_TIMEOUT;
		}
		session.setAttribute("validCodeTime", date);
		try {
			UserService userService = ServiceClient.createUserService();
			userService.sendRecoveryEmail(userName);
		} catch (RuntimeServiceException e) {
			// user not find
			return JSON_NOT_FOUND_USER;
		} catch (ParameterValidationException e) {
			// send fail
			return JSON_SEND_EMAIL_FIAL;
		} catch (ServiceException e) {
			return JSON_SEND_EMAIL_FIAL;
		}
		return JSON_SUCCESS;
	}

	@RequestMapping(value = "validate", method = RequestMethod.GET)
	@ResponseBody
	public String validateSecretKey(String userName, String secretKey) {
		userName = StringUtils.trim(userName);
		secretKey = StringUtils.trim(secretKey);
		if (!StringUtils.isNotBlank(userName)) {
			return JSON_USERNAME_NULL;
		}
		if (!StringUtils.isNotBlank(secretKey)) {
			return JSON_SECRET_KEY_NULL;
		}
		try {
			UserService userService = ServiceClient.createUserService();
			if (!userService.validateRecoveryAuthKey(userName, secretKey)) {
				// secretKeyFail
				return JSON_AUTH_KEY_ERROR;
			}
		} catch (RuntimeServiceException e) {
			// user not find
			return JSON_NOT_FOUND_USER;
		} catch (ParameterValidationException e) {
			// time out authkey
			return JSON_AUTH_KEY_ERROR;
		} catch (ServiceException e) {
			return JSON_VALID_AUTH_KEY;
		}
		return JSON_SUCCESS;
	}

	@RequestMapping(value = "reset", method = RequestMethod.POST)
	@ResponseBody
	public String resetPassWord(HttpServletRequest request) {
		String userName = StringUtils.trim(request.getParameter("userName"));
		if (!StringUtils.isNotBlank(userName)) {
			return JSON_USERNAME_NULL;
		}
		String newPassWord = StringUtils.trim(request.getParameter("passWord"));
		if (!StringUtils.isNotBlank(newPassWord)) {
			return JSON_PASSWORD_NULL;
		}
		try {
			UserService userService = ServiceClient.createUserService();
			if (!userService.resetPassword(userName, newPassWord)) {
				return JSON_RESETPASSWORD_FAIL;
			}
		} catch (RuntimeServiceException e) {
			// user not find
			return JSON_NOT_FOUND_USER;
		} catch (ServiceException e) {
			return JSON_RESETPASSWORD_FAIL;
		}
		return JSON_SUCCESS;
	}

}
