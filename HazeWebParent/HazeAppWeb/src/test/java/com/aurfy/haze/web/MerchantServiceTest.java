package com.aurfy.haze.web;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.Test;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.aurfy.haze.service.api.infrastructure.MerchantService;
import com.aurfy.haze.service.client.ServiceClient;
import com.aurfy.haze.service.conf.ServiceConstant;
import com.aurfy.haze.service.exceptions.ServiceException;

public class MerchantServiceTest {

	protected MerchantService createMerchantServiceBySpringProxy() {
		HessianProxyFactoryBean bean = new HessianProxyFactoryBean();
		bean.setServiceUrl(ServiceClient.SVR_BASE_URL + ServiceConstant.Hessian.MERCHANT_SERVICE_URL);
		bean.setServiceInterface(MerchantService.class);
		bean.setAllowNonSerializable(true);
		bean.setHessian2(true);
		bean.setOverloadEnabled(true);
		bean.setDebug(true);
		bean.afterPropertiesSet();

		MerchantService service = (MerchantService) bean.getObject();
		assertNotNull("get remote merchant service proxy failed", service);
		return service;
	}

	protected MerchantService createMerchantServiceByClient() {
		try {
			return ServiceClient.createMerchantService();
		} catch (ServiceException e) {
			fail("createMerchantServiceByClient failed: " + e.getMessage());
			return null;
		}
	}

	protected void testMerchantService(MerchantService service) {
		try {
			// MerchantUser user = service.//dosomething
		} catch (Exception e) {
			if (e instanceof NotImplementedException) {
				// ignore
			} else {
				fail("call MerchantService failed: " + e.getMessage());
			}
		}
	}

	@Test
	public void testWithSpringProxy() {
		testMerchantService(createMerchantServiceBySpringProxy());
	}

	@Test
	public void testWithClient() {
		testMerchantService(createMerchantServiceByClient());
	}

}
