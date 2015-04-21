package com.aurfy.haze.service.impl.infra;

import static org.junit.Assert.fail;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.infra.UserStatusEnum;
import com.aurfy.haze.dao.infra.PasswordRecoveryMapper;
import com.aurfy.haze.entity.infra.PasswordRecoveryEntity;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.infrastructure.UserService;
import com.aurfy.haze.service.bean.infra.UserBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;

public class PasswordRecoveryServiceTest extends ServiceUnitTest {

	@Autowired
	private UserService userService;
	@Autowired
	private CRUDService crudService;
	@Autowired
	private PasswordRecoveryMapper passwordRecovery;

	@Test
	@Transactional
	public void testGetBackPassword() {
		UserBean userBean = newUser();
		try {
			crudService.create(userBean);
		} catch (ServiceException | RuntimeServiceException e) {
			fail(e.getMessage());
		}
		try {
			userService.sendRecoveryEmail(userBean.getName());
			PasswordRecoveryEntity passwordRevovery = passwordRecovery.selectOne(userBean.getID());
			Assert.assertNotNull(passwordRevovery);
			Assert.assertTrue(userService.validateRecoveryAuthKey(userBean.getName(), passwordRevovery.getAuthKey()));

			String newPassword = "abcd1234";
			Assert.assertTrue(userService.resetPassword(userBean.getName(), newPassword));
			UserBean newUserBean = (UserBean) crudService.retrieve(UserBean.class, userBean.getID());
			Assert.assertNotNull(newUserBean);
			Assert.assertEquals(newUserBean.getEncryptedPasswd(),
					SecurityUtils.SHA3(SecurityUtils.SHA3(newPassword) + newUserBean.getPasswdSalt()));
			Assert.assertTrue(passwordRevovery.getExpiryDate().before(new Date()));
		} catch (RuntimeServiceException | ServiceException e) {
			fail(e.getMessage());
		}
	}

	private UserBean newUser() {
		UserBean userBean = new UserBean();
		userBean.setID(SecurityUtils.UUID());
		userBean.setEmail("414039913@qq.com");
		userBean.setLicenseAgreed(true);
		// no user id set
		userBean.setName("Junit_" + RandomStringUtils.randomAlphanumeric(6));
		final String salt = RandomStringUtils.randomAlphanumeric(6);
		userBean.setEncryptedPasswd("");
		userBean.setPasswdSalt(salt);
		userBean.setRegIP("127.0.0.1");
		userBean.setComments("Random user created by JUnit service test");
		userBean.setStatus(UserStatusEnum.ACTIVE);
		userBean.setCreateDate(new Date());
		userBean.setUpdateDate(userBean.getCreateDate());
		return userBean;

	}
}
