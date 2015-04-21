package com.aurfy.haze.service.impl.infra;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.aurfy.haze.conf.HazeDefaultConfig;
import com.aurfy.haze.core.model.infra.UserStatusEnum;
import com.aurfy.haze.dao.infra.PasswordRecoveryMapper;
import com.aurfy.haze.dao.infra.UserMapper;
import com.aurfy.haze.entity.infra.PasswordRecoveryEntity;
import com.aurfy.haze.entity.infra.UserEntity;
import com.aurfy.haze.service.api.infrastructure.UserService;
import com.aurfy.haze.service.api.mail.MailService;
import com.aurfy.haze.service.api.security.SecurityService;
import com.aurfy.haze.service.bean.infra.UserBean;
import com.aurfy.haze.service.bean.infra.UserLoginRequest;
import com.aurfy.haze.service.bean.mail.MailBean;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.exceptions.ParameterValidationException;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.BaseHazeService;
import com.aurfy.haze.service.impl.helper.BeanBuilder;
import com.aurfy.haze.utils.RegexUtils;
import com.aurfy.haze.utils.SecurityUtils;
import com.aurfy.haze.utils.StringUtils;

@Service(AOP_NAME.USER_SERVICE)
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserServiceImpl extends BaseHazeService implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private static final String passwordRecoveryTitle = HazeDefaultConfig.getMailProps().getProperty(
			"mail_PasswordRecoveryTitle");
	private static final String passwordRecoveryText1 = HazeDefaultConfig.getMailProps().getProperty(
			"mail_PasswordRecoveryText1");
	private static final String passwordRecoveryText2 = HazeDefaultConfig.getMailProps().getProperty(
			"mail_PasswordRecoveryText2");
	private static final String passwordRecoveryURL = HazeDefaultConfig.getMailProps().getProperty(
			"mail_PasswordRecoveryURL");
	private static final String recoveryAuthKeyValidTime = HazeDefaultConfig.getSecurityProps().getProperty(
			"PASSWORDRECOVERYURL_VALID_TIME");

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private MailService mailService;
	@Autowired
	private SecurityService securityService;

	@Autowired
	private PasswordRecoveryMapper passwordRecoveryMapper;

	private static final String USER_NOT_EXIST = "the user does not exist";
	private static final String USERNAME_OR_PASSWORD_WRONG = "the username or password is wrong";
	private static final String USER_FOBIDDEN = "the user is fobidden to login";
	private static final String EMAIL_FORMAT_FAIL = "invalid email format";
	private static final String AUTH_KEY_TIMEOUT = "this URL is timeout";

	public UserServiceImpl() {
	}

	@Override
	public UserBean selectUserByName(String userName) throws ServiceException, RuntimeServiceException {
		UserEntity userEntity = userMapper.selectByName(userName);
		if (userEntity == null) {
			throw new RuntimeServiceException(USER_NOT_EXIST);
		}
		UserBean user = BeanBuilder.toUserBean(userEntity);
		user.setPasswdSalt("");
		user.setEncryptedPasswd("");
		return user;
	}

	@Override
	public UserBean selectUserByNameAndPasswd(String loginName, String passwdDigest) throws ServiceException,
			RuntimeServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBean login(@Validated UserLoginRequest request) throws ServiceException, RuntimeServiceException {
		UserEntity userEntity = userMapper.selectByName(request.getUsername());
		if (userEntity == null) {
			logger.error("the user {} login fail: {}", request.getUsername(), USER_NOT_EXIST);
			throw new RuntimeServiceException(USER_NOT_EXIST);
		}
		String password = securityService.encryptUserPassword(request.getPassword(), userEntity.getPasswdSalt());
		if (!StringUtils.equals(password, userEntity.getEncryptedPasswd())) {
			logger.error("the user {} login fail: {}", request.getUsername(), USERNAME_OR_PASSWORD_WRONG);
			throw new RuntimeServiceException(USERNAME_OR_PASSWORD_WRONG);
		}
		if (!UserStatusEnum.ACTIVE.equals(userEntity.getStatus())) {
			logger.error("the user {} login fail: {}", request.getUsername(), USER_FOBIDDEN);
			throw new RuntimeServiceException(USER_FOBIDDEN);
		}

		UserBean user = BeanBuilder.toUserBean(userEntity);
		user.setPasswdSalt("");
		user.setEncryptedPasswd("");

		return user;
	}

	@Override
	public void sendRecoveryEmail(String loginName) throws ServiceException, RuntimeServiceException {
		UserEntity userEntity = userMapper.selectByName(loginName);
		if (userEntity == null) {
			throw new RuntimeServiceException(USER_NOT_EXIST);
		} else {
			String userEmail = userEntity.getEmail();
			if (!RegexUtils.isValidEmail(userEmail)) {
				throw new ParameterValidationException(EMAIL_FORMAT_FAIL);
			}

			String authKey = RandomStringUtils.randomAlphanumeric(16);
			Date date = new Date();
			PasswordRecoveryEntity passwordRecoveryEntity = passwordRecoveryMapper.selectOne(userEntity.getID());
			if (passwordRecoveryEntity == null) {
				passwordRecoveryEntity = new PasswordRecoveryEntity();
				passwordRecoveryEntity.setID(userEntity.getID());
				passwordRecoveryEntity.setAuthKey(authKey);
				passwordRecoveryEntity.setCreateDate(date);
				passwordRecoveryEntity.setExpiryDate(new Date(date.getTime()
						+ Integer.valueOf(recoveryAuthKeyValidTime)));
				passwordRecoveryMapper.insert(passwordRecoveryEntity);
			} else {

				passwordRecoveryEntity.setAuthKey(authKey);
				passwordRecoveryEntity.setExpiryDate(new Date(date.getTime()
						+ Integer.valueOf(recoveryAuthKeyValidTime)));
				passwordRecoveryEntity.setUpdateDate(date);
				passwordRecoveryMapper.update(passwordRecoveryEntity);
			}

			MailBean mailBean = new MailBean();
			List<String> addressList = new ArrayList<String>();
			addressList.add(userEmail);
			mailBean.setAddressees(addressList);
			mailBean.setTitle(passwordRecoveryTitle);
			StringBuffer text = new StringBuffer();
			text.append(passwordRecoveryText1);
			text.append(authKey);
			text.append(passwordRecoveryText2);
			text.append(passwordRecoveryURL);
			text.append("?userName=");
			text.append(loginName);
			text.append("&authKey=");
			text.append(authKey);
			mailBean.setText(text.toString());
			mailService.sendMail(mailBean);
		}
	}

	@Override
	public boolean validateRecoveryAuthKey(String userName, String authKey) throws ServiceException,
			RuntimeServiceException {
		PasswordRecoveryEntity passwordRecoveryEntity = passwordRecoveryMapper.selectByAuthkey(authKey);
		if (passwordRecoveryEntity == null) {
			return false;
		}
		if (passwordRecoveryEntity.getExpiryDate().before(new Date())) {
			throw new ParameterValidationException(AUTH_KEY_TIMEOUT);
		}
		UserEntity userEntity = userMapper.selectOne(passwordRecoveryEntity.getID());
		if (userEntity == null) {
			throw new RuntimeServiceException(USER_NOT_EXIST);
		}
		if (StringUtils.equals(userEntity.getName(), userName)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean resetPassword(String userName, String newPassword) throws ServiceException, RuntimeServiceException {
		UserEntity userEntity = userMapper.selectByName(userName);
		if (userEntity == null) {
			throw new RuntimeServiceException(USER_NOT_EXIST);
		}
		PasswordRecoveryEntity passwordRecoveryEntity = passwordRecoveryMapper.selectOne(userEntity.getID());
		if (passwordRecoveryEntity == null) {
			return false;
		}
		Date newDate = new Date();
		passwordRecoveryEntity.setExpiryDate(newDate);
		passwordRecoveryEntity.setUpdateDate(newDate);
		passwordRecoveryMapper.update(passwordRecoveryEntity);

		final String salt = RandomStringUtils.randomAlphanumeric(6);
		userEntity.setEncryptedPasswd(SecurityUtils.SHA3(SecurityUtils.SHA3(newPassword) + salt));
		userEntity.setPasswdSalt(salt);
		userEntity.setUpdateDate(newDate);
		userMapper.update(userEntity);
		return true;
	}

}
