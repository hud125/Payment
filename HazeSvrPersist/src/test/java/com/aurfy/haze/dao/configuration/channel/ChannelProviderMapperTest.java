package com.aurfy.haze.dao.configuration.channel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.configuration.channel.ChannelProviderClassifier;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.configuration.channel.ChannelProviderEntity;
import com.aurfy.haze.utils.SecurityUtils;

/**
 * 测试类应该有bug，需要重新测试
 *
 */
public class ChannelProviderMapperTest extends PersistUnitTest {

	@Autowired
	private ChannelProviderMapper mapper;

	public ChannelProviderEntity internalCreateEntity() {
		ChannelProviderEntity channelProvider = new ChannelProviderEntity();
		channelProvider.setID(SecurityUtils.UUID());
		channelProvider.setAcquirerId(SecurityUtils.UUID());
		channelProvider.setProviderName("ADDDDDDD");
		channelProvider.setProviderClassifier(ChannelProviderClassifier.UNIONPAY_SECURE_PAY);
		channelProvider.setTransactionCurrencies("USD");
		channelProvider.setSettlementCurrencies("CNY");
		channelProvider.setCardOrg(CardOrgEnum.VISA);
		channelProvider.setOwnerId("cheng201");
		channelProvider.setCreateDate(new Date());
		channelProvider.setUpdateDate(channelProvider.getCreateDate());
		channelProvider.setBankAccountId("a2532b43-cd98-46bf-bbdb-57c3bd16dcb2");

		String oldId = channelProvider.getID();
		int count = mapper.insert(channelProvider);
		assertTrue(count == 1);
		String newId = channelProvider.getID();
		assertTrue(newId.equals(oldId));
		return channelProvider;
	}

	@Test
	@Transactional
	public void testInsert() {
		internalCreateEntity();
	}

	@Test
	@Transactional
	public void testSelectOne() {
		ChannelProviderEntity channelProvider = internalCreateEntity();
		ChannelProviderEntity newAcc = mapper.selectOne(channelProvider.getID());
		assertTrue(channelProvider.equals(newAcc));
	}

	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = mapper.countAll();
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();
		}

		int size = mapper.countAll();
		assertTrue(10 == (size - oldSize));
	}

	@Test
	@Transactional
	public void testSelectAll() {
		int oldSize = mapper.countAll();
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();

		}
		List<ChannelProviderEntity> list = mapper.selectAll();

		assertNotNull(list);
		assertTrue((list.size() - oldSize) == 10);
	}

	@Test
	@Transactional
	public void testCountBy() {
		ChannelProviderEntity filterEntity = new ChannelProviderEntity();
		int oldSize = mapper.countBy(filterEntity);
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();
		}

		int size = mapper.countBy(filterEntity);
		assertTrue(10 == (size - oldSize));
	}

	@Test
	@Transactional
	public void testSelectBy() {
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();

		}
		ChannelProviderEntity filterEntity = new ChannelProviderEntity();
		List<ChannelProviderEntity> list = mapper.selectBy(filterEntity, 0, 10);

		assertNotNull(list);
		assertTrue(list.size() == 10);
	}

	@Test
	@Transactional
	public void testUpdate() {
		ChannelProviderEntity channelProvider = internalCreateEntity();

		channelProvider.setAcquirerId(SecurityUtils.UUID());
		channelProvider.setProviderClassifier(ChannelProviderClassifier.UNIONPAY_SECURE_PAY);
		channelProvider.setTransactionCurrencies("CNY");
		channelProvider.setSettlementCurrencies("USD");
		channelProvider.setCardOrg(CardOrgEnum.JCB);
		channelProvider.setOwnerId("SSSSSSSS");
		channelProvider.setUpdateDate(new Date());

		int count = mapper.update(channelProvider);
		assertTrue(count == 1);
		ChannelProviderEntity newAcq = mapper.selectOne(channelProvider.getID());
		assertTrue(channelProvider.equals(newAcq));
	}

	@Test
	@Transactional
	public void testDelete() {
		ChannelProviderEntity channelProvider = internalCreateEntity();

		int count = mapper.delete(channelProvider.getID());
		assertTrue(count == 1);
		ChannelProviderEntity channelProvider2 = mapper.selectOne(channelProvider.getID());
		assertTrue(channelProvider2 == null);
	}
}
