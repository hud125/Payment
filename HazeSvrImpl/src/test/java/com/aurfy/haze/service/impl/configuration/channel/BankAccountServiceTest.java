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

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.bank.BankAccountBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;

public class BankAccountServiceTest extends ServiceUnitTest {

	@Autowired
	private CRUDService service;

	private BankAccountBean randomBankAccountBean() {
		BankAccountBean bean = new BankAccountBean();
		bean.setID(SecurityUtils.UUID());
		bean.setBankId("0324036a-46df-454b-9010-ffb131014e84");
		bean.setBankCode("ICBC");
		bean.setBankAbbreviation("工行");
		bean.setBankName("中国工商银行");
		bean.setBankBranch("中国工商银行田林路支行");
		bean.setBankURL("//http://www.icbc.com.cn/icbc/");
		bean.setComments("test");
		
		bean.setAccountCurrency(Currency.CNY);
		bean.setAccountHolder("zhangcheng");
		bean.setAccountNumber("1234567891");
		bean.setAccountOpenDate(new Date());

		bean.setOwnerId("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setCreateDate(new Date());
		bean.setUpdateDate(new Date());
		return bean;
	}

	@Test
	@Transactional
	public void testCreateWithEmptyProperty() {
		try {
			BankAccountBean bean = randomBankAccountBean();
			bean.setID("");
			bean.setCreateDate(null);
			bean.setUpdateDate(null);

			BankAccountBean result = (BankAccountBean) service.create(bean);
			assertNotNull(result);
			assertNotNull(result.getID());
			assertNotNull(result.getCreateDate());
			assertNotNull(result.getUpdateDate());
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	private BankAccountBean internalCreate() throws RuntimeServiceException, ServiceException {
		return (BankAccountBean) service.create(randomBankAccountBean());
	}

	@Test
	@Transactional
	public void testCreate() {
		try {
			BankAccountBean bean = internalCreate();
			assertNotNull(bean);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testRetrieve() {
		try {
			BankAccountBean bean = internalCreate();
			BankAccountBean result = (BankAccountBean) service.retrieve(BankAccountBean.class, bean.getID());
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
			int oldSize = service.retrieveAll(BankAccountBean.class).size();
			int randomSize = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < randomSize; ++i) {
				internalCreate();
			}
			Pagination<CRUDBean> list = service.retrieve(BankAccountBean.class, 1, 10 + oldSize);
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
			List<CRUDBean> list = service.retrieveAll(BankAccountBean.class);
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
			BankAccountBean bean = internalCreate();
			Assert.assertNotNull(bean);
			Date oldDate = bean.getUpdateDate();

			bean.setBankName(prefix + RandomStringUtils.randomAlphanumeric(10));
			bean.setComments(prefix + RandomStringUtils.randomAlphanumeric(10));
			bean.setOwnerId(prefix + RandomStringUtils.randomAlphanumeric(10));
			bean.setUpdateDate(new Date());
			BankAccountBean result = (BankAccountBean) service.update(bean);

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
			BankAccountBean bean = internalCreate();
			assertNotNull(bean);
			assertTrue(service.delete(BankAccountBean.class, bean.getID()));
			//
			assertFalse(service.delete(BankAccountBean.class, "non_exist"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}