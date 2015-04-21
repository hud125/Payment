package com.aurfy.haze.service.impl.infra;

import static org.junit.Assert.fail;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.aurfy.haze.core.model.infra.UserStatusEnum;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.infrastructure.UserService;
import com.aurfy.haze.service.bean.infra.UserBean;
import com.aurfy.haze.service.bean.infra.UserLoginRequest;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;

public class UserServiceTest extends ServiceUnitTest {

	@Autowired
	private CRUDService service;
	@Autowired
	private UserService userService;

	@Test
	@Transactional
	public void testCreateUser() {
		UserBean userBean = new UserBean();
		userBean.setID("BCCCCCC");
		userBean.setEmail("chengzhang@glbpay.com");
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
		try {
			UserBean resultUser = (UserBean) service.create(userBean);
			System.out.println(resultUser);
		} catch (ServiceException | RuntimeServiceException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	public UserLoginRequest createRequest() {
		UserLoginRequest request = new UserLoginRequest();

		request.setUsername("aurfytest");
		request.setPassword("123456");

		return request;
	}

	@Test
	@Transactional
	public void testLogin() throws RuntimeServiceException, ServiceException {

		UserBean userBean = userService.login(createRequest());
		Assert.notNull(userBean);
	}
}
