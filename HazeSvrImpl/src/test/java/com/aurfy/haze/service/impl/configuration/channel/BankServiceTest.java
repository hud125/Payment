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
import com.aurfy.haze.service.bean.configuration.channel.BankBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;

public class BankServiceTest extends ServiceUnitTest {

	@Autowired
	private CRUDService service;

	private BankBean randomBankBean() {
		BankBean bean = new BankBean();
		bean.setID(SecurityUtils.UUID());

		bean.setBankCode("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setBankAbbreviation("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setBankName("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setBankBranch("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setBankURL("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setComments("test");

		bean.setOwnerId("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setCreateDate(new Date());
		bean.setUpdateDate(new Date());
		return bean;
	}

	@Test
	@Transactional
	public void testCreateWithEmptyProperty() {
		try {
			BankBean bean = randomBankBean();
			bean.setID("");
			bean.setCreateDate(null);
			bean.setUpdateDate(null);

			BankBean result = (BankBean) service.create(bean);
			assertNotNull(result);
			assertNotNull(result.getID());
			assertNotNull(result.getCreateDate());
			assertNotNull(result.getUpdateDate());
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	private BankBean internalCreate() throws RuntimeServiceException, ServiceException {
		return (BankBean) service.create(randomBankBean());
	}

	@Test
	@Transactional
	public void testCreate() {
		try {
			BankBean bean = internalCreate();
			assertNotNull(bean);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testRetrieve() {
		try {
			BankBean bean = internalCreate();
			BankBean result = (BankBean) service.retrieve(BankBean.class, bean.getID());
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
			int oldSize = service.retrieveAll(BankBean.class).size();
			int randomSize = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < randomSize; ++i) {
				internalCreate();
			}
			Pagination<CRUDBean> list = service.retrieve(BankBean.class, 1, 10 + oldSize);
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
			List<CRUDBean> listInit = service.retrieveAll(BankBean.class);
			int randomSize = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < randomSize; ++i) {
				internalCreate();
			}
			List<CRUDBean> list = service.retrieveAll(BankBean.class);
			assertNotNull(list);
			assertEquals(randomSize + listInit.size(), list.size());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testUpdate() {
		final String prefix = "Updated_";
		try {
			BankBean bean = internalCreate();
			Assert.assertNotNull(bean);
			Date oldDate = bean.getUpdateDate();

			bean.setBankName(prefix + RandomStringUtils.randomAlphanumeric(10));
			bean.setComments(prefix + RandomStringUtils.randomAlphanumeric(10));
			bean.setOwnerId(prefix + RandomStringUtils.randomAlphanumeric(10));
			bean.setUpdateDate(new Date());
			BankBean result = (BankBean) service.update(bean);

			assertNotNull(result);
			assertTrue(result.getBankName().startsWith(prefix));
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
			BankBean bean = internalCreate();
			assertNotNull(bean);
			assertTrue(service.delete(BankBean.class, bean.getID()));
			//
			assertFalse(service.delete(BankBean.class, "non_exist"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
