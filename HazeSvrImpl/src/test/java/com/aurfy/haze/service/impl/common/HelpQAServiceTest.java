package com.aurfy.haze.service.impl.common;

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
import com.aurfy.haze.service.bean.common.HelpQABean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;

public class HelpQAServiceTest extends ServiceUnitTest {

	@Autowired
	private CRUDService service;

	private HelpQABean randomHelpQABean() {
		HelpQABean bean = new HelpQABean();
		bean.setID(SecurityUtils.UUID());
		bean.setKey("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setTopic("Junit_" + RandomStringUtils.randomAlphanumeric(20));
		bean.setAnswer("Junit_" + RandomStringUtils.randomAlphanumeric(30));
		bean.setCreateDate(new Date());
		bean.setUpdateDate(new Date());
		return bean;
	}

	@Test
	@Transactional
	public void testCreateWithEmptyProperty() {
		try {
			HelpQABean bean = randomHelpQABean();
			bean.setID("");
			bean.setCreateDate(null);
			bean.setUpdateDate(null);

			HelpQABean result = (HelpQABean) service.create(bean);
			assertNotNull(result);
			assertNotNull(result.getID());
			assertNotNull(result.getCreateDate());
			assertNotNull(result.getUpdateDate());
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	private HelpQABean internalCreate() throws RuntimeServiceException, ServiceException {
		return (HelpQABean) service.create(randomHelpQABean());
	}

	@Test
	@Transactional
	public void testCreate() {
		try {
			HelpQABean bean = internalCreate();
			assertNotNull(bean);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testRetrieve() {
		try {
			HelpQABean bean = internalCreate();
			HelpQABean result = (HelpQABean) service.retrieve(HelpQABean.class, bean.getID());
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
			int oldSize = service.retrieveAll(HelpQABean.class).size();
			int randomSize = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < randomSize; ++i) {
				internalCreate();
			}
			Pagination<CRUDBean> list = service.retrieve(HelpQABean.class, 1, 10 + oldSize);
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
			int randomSize = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < randomSize; ++i) {
				internalCreate();
			}
			List<CRUDBean> list = service.retrieveAll(HelpQABean.class);
			assertNotNull(list);
			assertEquals(randomSize, list.size());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testUpdate() {
		final String prefix = "Updated_";
		try {
			HelpQABean bean = internalCreate();
			Assert.assertNotNull(bean);
			Date oldDate = bean.getUpdateDate();
			
			bean.setKey(prefix + RandomStringUtils.randomAlphanumeric(10));
			bean.setTopic(prefix + RandomStringUtils.randomAlphanumeric(20));
			bean.setAnswer(prefix + RandomStringUtils.randomAlphanumeric(30));
			bean.setUpdateDate(new Date());
			HelpQABean result = (HelpQABean) service.update(bean);
			
			assertNotNull(result);
			assertTrue(result.getKey().startsWith(prefix));
			assertTrue(result.getTopic().startsWith(prefix));
			assertTrue(result.getAnswer().startsWith(prefix));
			assertTrue(result.getUpdateDate().after(oldDate));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testDelete() {
		try {
			HelpQABean bean = internalCreate();
			assertNotNull(bean);
			assertTrue(service.delete(HelpQABean.class, bean.getID()));
			//
			assertFalse(service.delete(HelpQABean.class, "non_exist"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
