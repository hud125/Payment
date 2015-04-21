package com.aurfy.haze.service.impl.configuration.channel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.configuration.ChannelStatusEnum;
import com.aurfy.haze.core.model.configuration.channel.ChannelProviderClassifier;
import com.aurfy.haze.core.model.page.Pagination;
import com.aurfy.haze.service.api.configuration.channel.ChannelService;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.bean.configuration.channel.ChannelParameterBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;

public class ChannelServiceTest extends ServiceUnitTest {

	@Autowired
	private ChannelService channelService;

	public ChannelBean newChannelBean() {
		ChannelBean channel = new ChannelBean();

		channel.setID(UUID.randomUUID().toString());
		channel.setAcquirerId("6a9a42ef-9b04-4a98-a97e-78aeae749bd3");
		channel.setAcquirerName("Junit_j1L1PRgR85");
		channel.setChannelProviderId("0d3dae31-f20f-46ba-b94b-22fb9c71a937");
		channel.setChannelProviderName("UNION EXPRSS");
		channel.setProviderClassifier(ChannelProviderClassifier.UNIONPAY_SECURE_PAY);
		channel.setTransactionCurrency(Currency.AED);
		channel.setSettlementCurrency(Currency.AUD);
		channel.setCardOrg(CardOrgEnum.JCB);
		channel.setStatus(ChannelStatusEnum.ACTIVE);
		channel.setDueDate(new Date());
		channel.setComments("test");
		channel.setCreateDate(new Date());
		channel.setUpdateDate(channel.getCreateDate());

		List<ChannelParameterBean> channelParams = new ArrayList<ChannelParameterBean>();
		for (int i = 0; i < 10; i++) {
			channelParams.add(newChannelParameterBean(i, channel.getID()));
		}

		channel.setChannelParams(channelParams);

		return channel;
	}

	public ChannelParameterBean newChannelParameterBean(int i, String channelId) {
		ChannelParameterBean channelParameter = new ChannelParameterBean();

		channelParameter.setID(SecurityUtils.UUID());
		channelParameter.setConfigKey("aaaa" + i);
		channelParameter.setChannelId(channelId);
		channelParameter.setConfigValue("bbbbb");
		return channelParameter;
	}

	private ChannelBean internalCreate() throws RuntimeServiceException, ServiceException {
		return (ChannelBean) channelService.create(newChannelBean());
	}

	@Test
	@Transactional
	// @Rollback(false)
	public void TestCreate() throws RuntimeServiceException, ServiceException {
		internalCreate();
	}

	@Test
	@Transactional
	public void TestRetrieve() throws RuntimeServiceException, ServiceException {
		ChannelBean channel = internalCreate();
		ChannelBean _channel = channelService.retrieve(channel.getID());
		assertNotNull(_channel);
	}

	@Test
	@Transactional
	public void testRetrieveWithPagination() {
		try {
			int oldSize = channelService.retrieveAll().size();
			int randomSize = RandomUtils.nextInt(1, 10);
			for (int i = 0; i < randomSize; ++i) {
				internalCreate();
			}
			Pagination<ChannelBean> list = channelService.retrieve(1, 10 + oldSize);
			assertNotNull(list.getPageData());
			assertEquals(randomSize + oldSize, list.getPageData().size());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void TestRetrieveWithParams() throws RuntimeServiceException, ServiceException {
		ChannelBean channel = internalCreate();

		ChannelBean _channel = channelService.retrieve(channel.getID(), true);
		assertNotNull(_channel);
		assertNotNull(_channel.getChannelParams());
		// 插入的channelParameter为10条
		assertNotNull(_channel.getChannelParams().size() == 10);

	}

	@Test
	@Transactional
	public void TestUpdate() throws RuntimeServiceException, ServiceException {
		ChannelBean channel = internalCreate();
		channel.setSettlementCurrency(Currency.AED);
		channelService.update(channel);

		ChannelBean _channel = channelService.retrieve(channel.getID());
		assertNotNull(_channel);
		assertTrue(Currency.AED.equals(_channel.getSettlementCurrency()));
	}

	@Test
	@Transactional
	public void TestUpdateParams() throws RuntimeServiceException, ServiceException {
		ChannelBean channel = internalCreate();
		List<ChannelParameterBean> channelParams = new ArrayList<ChannelParameterBean>();
		for (int i = 0; i < 8; i++) {
			channelParams.add(newChannelParameterBean(i, channel.getID()));
		}

		channelService.updateParams(channel.getID(), channelParams);

		ChannelBean _channel = channelService.retrieve(channel.getID(), true);
		assertNotNull(_channel);
		assertNotNull(_channel.getChannelParams());
		assertTrue(_channel.getChannelParams().size() == 8);
	}

	@Test
	@Transactional
	public void TestDelete() throws RuntimeServiceException, ServiceException {
		ChannelBean channel = internalCreate();
		channelService.delete(channel.getID());

		ChannelBean _channel = channelService.retrieve(channel.getID());
		assertNull(_channel);
	}

}
