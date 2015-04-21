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

import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.configuration.channel.ChannelProviderClassifier;
import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.configuration.channel.ChannelProviderBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;

public class ChannelProviderServiceTest extends ServiceUnitTest {

	@Autowired
	private CRUDService service;

	private ChannelProviderBean randomChannelProviderBean() {
		ChannelProviderBean bean = new ChannelProviderBean();
		bean.setID(SecurityUtils.UUID());
		bean.setAcquirerId(SecurityUtils.UUID());
		bean.setProviderName("ADDDDDDD");
		bean.setProviderClassifier(ChannelProviderClassifier.UNIONPAY_EXPRESS_PAY);
		bean.setTransactionCurrencies("USD,CNY");
		bean.setSettlementCurrencies("CNY,USD");
		bean.setCardOrg(CardOrgEnum.VISA);
		bean.setOwnerId("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		bean.setCreateDate(new Date());
		bean.setUpdateDate(new Date());
		return bean;
	}

	@Test
	@Transactional
	public void testCreateWithEmptyProperty() {
		try {
			ChannelProviderBean bean = randomChannelProviderBean();
			bean.setID("");
			bean.setCreateDate(null);
			bean.setUpdateDate(null);

			ChannelProviderBean result = (ChannelProviderBean) service.create(bean);
			assertNotNull(result);
			assertNotNull(result.getID());
			assertNotNull(result.getCreateDate());
			assertNotNull(result.getUpdateDate());
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}

	private ChannelProviderBean internalCreate() throws RuntimeServiceException, ServiceException {
		return (ChannelProviderBean) service.create(randomChannelProviderBean());
	}

	@Test
	@Transactional
	public void testCreate() {
		try {
			ChannelProviderBean bean = internalCreate();
			assertNotNull(bean);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testRetrieve() {
		try {
			ChannelProviderBean bean = internalCreate();
			ChannelProviderBean result = (ChannelProviderBean) service
					.retrieve(ChannelProviderBean.class, bean.getID());
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
			int oldSize = service.retrieveAll(ChannelProviderBean.class).size();
			int randomSize = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < randomSize; ++i) {
				internalCreate();
			}
			Pagination<CRUDBean> list = service.retrieve(ChannelProviderBean.class, 1, 10 + oldSize);
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
			List<CRUDBean> listInital = service.retrieveAll(ChannelProviderBean.class);
			int randomSize = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < randomSize; ++i) {
				internalCreate();
			}
			List<CRUDBean> list = service.retrieveAll(ChannelProviderBean.class);
			assertNotNull(list);
			assertEquals(randomSize + listInital.size(), list.size());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testUpdate() {
		final String prefix = "Updated_";
		try {
			ChannelProviderBean bean = internalCreate();
			Assert.assertNotNull(bean);
			Date oldDate = bean.getUpdateDate();

			bean.setProviderClassifier(ChannelProviderClassifier.UNIONPAY_SECURE_PAY);
			bean.setTransactionCurrencies("CNY,USD");
			bean.setSettlementCurrencies("CNY,USD");
			bean.setCardOrg(CardOrgEnum.JCB);

			bean.setOwnerId(prefix + RandomStringUtils.randomAlphanumeric(10));
			bean.setUpdateDate(new Date(System.currentTimeMillis() + 10000000));
			ChannelProviderBean result = (ChannelProviderBean) service.update(bean);

			assertNotNull(result);
			assertTrue(ChannelProviderClassifier.UNIONPAY_SECURE_PAY.equals(result.getProviderClassifier()));
			assertTrue(result.getTransactionCurrencies().startsWith("CNY,USD"));
			assertTrue(result.getSettlementCurrencies().startsWith("CNY,USD"));
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
			ChannelProviderBean bean = internalCreate();
			assertNotNull(bean);
			assertTrue(service.delete(ChannelProviderBean.class, bean.getID()));
			//
			assertFalse(service.delete(ChannelProviderBean.class, "non_exist"));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
