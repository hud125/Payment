package com.aurfy.haze.service.impl;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.core.model.system.ActionResultEnum;
import com.aurfy.haze.core.model.system.AuditActionEnum;
import com.aurfy.haze.core.model.system.SystemModuleEnum;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.infra.AuditLogBean;
import com.aurfy.haze.service.bean.infra.mer.MerchantBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.infra.MerchantServiceTest;
import com.aurfy.haze.utils.SecurityUtils;

public class CRUDServiceTest extends ServiceUnitTest {

	@Autowired
	private CRUDService service;

	private static AuditLogBean createAuditLogBean() {
		AuditLogBean bean = new AuditLogBean();
		bean.setID(SecurityUtils.UUID());
		bean.setModule(getRandomEnum(SystemModuleEnum.class));
		bean.setAction(getRandomEnum(AuditActionEnum.class));
		bean.setResult(getRandomEnum(ActionResultEnum.class));
		bean.setParam1("Junit_P1_" + RandomStringUtils.randomAlphanumeric(3));
		bean.setParam2("Junit_P2_" + RandomStringUtils.randomAlphanumeric(3));
		bean.setParam3("Junit_P3_" + RandomStringUtils.randomAlphanumeric(3));
		bean.setParam4("Junit_P4_" + RandomStringUtils.randomAlphanumeric(3));
		bean.setParam5("Junit_P5_" + RandomStringUtils.randomAlphanumeric(3));
		bean.setOwnerId("Junit dao tester");
		bean.setCreateDate(new Date());
		bean.setUpdateDate(bean.getCreateDate());
		return bean;
	}

	@Test
	@Transactional
	public void testCreate() {
		CRUDBean bean = createAuditLogBean();
		try {
			service.create(bean);
		} catch (RuntimeServiceException | ServiceException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	@Transactional
	public void testRetrieveBy() {
		CRUDBean bean = new MerchantServiceTest().newMerchant();
		try {
			service.create(bean);
			Pagination<CRUDBean> pagination = service.retrieveBy(bean.getClass(), bean, 0, 10);
			assertTrue(pagination.getPageData().size() > 0);
		} catch (RuntimeServiceException | ServiceException e) {
			fail(e.getMessage());
		}
	}
}
