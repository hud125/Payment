package com.aurfy.haze.service.impl.configuration.channel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.configuration.channel.AcquirerBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;

public class AcquirerServiceTest extends ServiceUnitTest {

	@Autowired
	private CRUDService service;

	private AcquirerBean randomAcquirerBean() {
		AcquirerBean bean = new AcquirerBean();
		bean.setID(SecurityUtils.UUID());
		bean.setAcquirerName("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setComments("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setOwnerId("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setCreateDate(new Date());
		bean.setUpdateDate(new Date());
		return bean;
	}

	@Test
	@Transactional
	public void testCreateWithEmptyProperty() {
		try {
			AcquirerBean bean = randomAcquirerBean();
			bean.setID("");
			bean.setCreateDate(null);
			bean.setUpdateDate(null);

			AcquirerBean result = (AcquirerBean) service.create(bean);
			assertNotNull(result);
			assertNotNull(result.getID());
			assertNotNull(result.getCreateDate());
			assertNotNull(result.getUpdateDate());
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	private AcquirerBean internalCreate() throws RuntimeServiceException, ServiceException {
		return (AcquirerBean) service.create(randomAcquirerBean());
	}

	@Test
	@Transactional
	public void testCreate() {
		try {
			AcquirerBean bean = internalCreate();
			assertNotNull(bean);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testRetrieve() {
		try {
			AcquirerBean bean = internalCreate();
			AcquirerBean result = (AcquirerBean) service.retrieve(AcquirerBean.class, bean.getID());
			assertNotNull(result);
			assertEquals(bean, result);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testRetrieveWithPagination() {
		try {
			int oldSize = service.retrieveAll(AcquirerBean.class).size();
			int randomSize = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < randomSize; ++i) {
				internalCreate();
			}
			Pagination<CRUDBean> list = service.retrieve(AcquirerBean.class, 1, 10 + oldSize);
			assertNotNull(list.getPageData());
			assertEquals(randomSize + oldSize, list.getPageData().size());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testRetrieveAll() {
		try {
			List<CRUDBean> listInital = service.retrieveAll(AcquirerBean.class);
			int randomSize = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < randomSize; ++i) {
				internalCreate();
			}
			List<CRUDBean> list = service.retrieveAll(AcquirerBean.class);
			assertNotNull(list);
			assertEquals(randomSize+listInital.size(), list.size());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testUpdate() {
		final String prefix = "Updated_";
		try {
			AcquirerBean bean = internalCreate();
			Assert.assertNotNull(bean);
			Date oldDate = bean.getUpdateDate();

			bean.setAcquirerName(prefix + RandomStringUtils.randomAlphanumeric(10));
			bean.setComments(prefix + RandomStringUtils.randomAlphanumeric(10));
			bean.setOwnerId(prefix + RandomStringUtils.randomAlphanumeric(10));
			bean.setUpdateDate(new Date());
			AcquirerBean result = (AcquirerBean) service.update(bean);

			assertNotNull(result);
			assertTrue(result.getAcquirerName().startsWith(prefix));
			assertTrue(result.getComments().startsWith(prefix));
			assertTrue(result.getOwnerId().startsWith(prefix));
			assertTrue(result.getUpdateDate().after(oldDate));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testDelete() {
		try {
			AcquirerBean bean = internalCreate();
			assertNotNull(bean);
			assertTrue(service.delete(AcquirerBean.class, bean.getID()));
			//
			assertFalse(service.delete(AcquirerBean.class, "non_exist"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
