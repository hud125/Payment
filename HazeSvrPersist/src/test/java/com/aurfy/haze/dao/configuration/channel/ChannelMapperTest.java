package com.aurfy.haze.dao.configuration.channel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.configuration.ChannelStatusEnum;
import com.aurfy.haze.core.model.configuration.channel.ChannelProviderClassifier;
import com.aurfy.haze.core.model.infra.mer.MerchantStatusEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.mer.MerchantMapper;
import com.aurfy.haze.entity.configuration.channel.ChannelEntity;
import com.aurfy.haze.entity.configuration.channel.ChannelMappingEntity;
import com.aurfy.haze.entity.infra.mer.MerchantEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class ChannelMapperTest extends PersistUnitTest {

	@Autowired
	private ChannelMapper channelMapper;

	@Autowired
	private ChannelParameterMapper channelParameterMapper;

	@Autowired
	private ChannelMappingMapper channelMappingMapper;

	@Autowired
	private MerchantMapper merchantMapper;

	public ChannelEntity insertChannelEntity() {
		ChannelEntity channel = newChannelEntity();
		channelMapper.insert(channel);
		return channel;
	}

	@Test
	@Transactional
	public void testInsert() {
		insertChannelEntity();
	}

	@Test
	@Transactional
	public void testSelectOne() {
		ChannelEntity channel = insertChannelEntity();
		ChannelEntity newChannel = channelMapper.selectOne(channel.getID());
		assertTrue(channel.equals(newChannel));
	}

	@Test
	@Transactional
	public void testSelectOneWithParams() {
		ChannelEntity channel = newChannelEntity();
		String channelId = channel.getID();
		channelMapper.insert(channel);
		for (int i = 0; i < 10; i++) {
			channelParameterMapper.insert(ChannelParameterMapperTest.newChannelParameterEntity(i, channelId));
		}
		ChannelEntity newChannel = channelMapper.selectOneWithParams(channel.getID());
		assertNotNull(newChannel);
		assertNotNull(newChannel.getChannelParams());
		assertTrue(newChannel.getChannelParams().size() == 10);
	}

	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = channelMapper.countAll();
		for (int i = 0; i < 10; i++) {
			insertChannelEntity();
		}

		int size = channelMapper.countAll();
		assertTrue(10 == (size - oldSize));
	}

	@Test
	@Transactional
	public void testSelectAll() {
		int oldSize = channelMapper.countAll();
		for (int i = 0; i < 10; i++) {
			insertChannelEntity();

		}
		List<ChannelEntity> list = channelMapper.selectAll();

		assertNotNull(list);
		assertTrue((list.size() - oldSize) == 10);
	}

	@Test
	@Transactional
	public void testCountBy() {
		ChannelEntity filterEntity = new ChannelEntity();
		int oldSize = channelMapper.countBy(filterEntity);
		for (int i = 0; i < 10; i++) {
			insertChannelEntity();
		}

		int size = channelMapper.countBy(filterEntity);
		assertTrue(10 == (size - oldSize));
	}

	@Test
	@Transactional
	public void testSelectBy() {
		for (int i = 0; i < 10; i++) {
			insertChannelEntity();

		}
		ChannelEntity filterEntity = new ChannelEntity();
		List<ChannelEntity> list = channelMapper.selectBy(filterEntity, 0, 10);

		assertNotNull(list);
		assertTrue(list.size() == 10);
	}

	@Test
	@Transactional
	public void testUpdate() {
		ChannelEntity channel = newChannelEntity();

		channelMapper.insert(channel);
		channel.setAcquirerId(SecurityUtils.UUID());
		channel.setAcquirerName("BCC");
		channel.setChannelProviderId(SecurityUtils.UUID());
		channel.setProviderClassifier(ChannelProviderClassifier.UNIONPAY_EXPRESS_PAY);
		channel.setTransactionCurrency(Currency.AED);
		channel.setSettlementCurrency(Currency.AUD);
		channel.setCardOrg(CardOrgEnum.JCB);
		channel.setStatus(ChannelStatusEnum.ACTIVE);
		channel.setDueDate(new Date());
		channel.setComments("test");
		channel.setUpdateDate(new Date());

		int count = channelMapper.update(channel);
		assertTrue(count == 1);
		ChannelEntity newAcq = channelMapper.selectOne(channel.getID());
		assertTrue(channel.equals(newAcq));
	}

	@Test
	@Transactional
	public void testDelete() {
		ChannelEntity channel = insertChannelEntity();

		int count = channelMapper.delete(channel.getID());
		assertTrue(count == 1);
		ChannelEntity channel2 = channelMapper.selectOne(channel.getID());
		assertTrue(channel2 == null);
	}

	@Test
	@Transactional
	public void testCascadedDelete() {
		// merchant
		MerchantEntity merchantEntity = newMerchant();
		merchantEntity.setMerchantCode(RandomStringUtils.randomAlphanumeric(10));
		merchantEntity.setMcc(RandomStringUtils.randomNumeric(4));
		merchantMapper.insert(merchantEntity);

		// default channel, with no parameter
		ChannelEntity channel = newChannelEntity();
		channel.setTransactionCurrency(Currency.AED);
		assertTrue(1 == channelMapper.insert(channel));

		// mapping
		ChannelMappingEntity channelMappingEntity = new ChannelMappingEntity();
		channelMappingEntity.setID(SecurityUtils.UUID());
		channelMappingEntity.setChannelId(channel.getID());
		channelMappingEntity.setMerId(merchantEntity.getID());
		channelMappingEntity.setDefaultChannel(false);
		channelMappingMapper.insert(channelMappingEntity);

		assertNotNull(channelMappingMapper.selectOne(channelMappingEntity.getID()));

		int count = channelMapper.delete(channel.getID());
		// affected row is 2 here!!!
		// one for ChannelEntity and the other one for ChannelMappingEntity
		assertTrue(count == 2);
		assertNull(channelMappingMapper.selectOne(channelMappingEntity.getID()));
	}

	@Test
	@Transactional
	public void testSelectMerChannelWithParms() {
		String merId = createChannelAndMapping();
		ChannelEntity channelEntity = channelMapper.selectMerChannelWithParms(merId, Currency.USD);
		assertNotNull(channelEntity);
		assertTrue(0 == CollectionUtils.size(channelEntity.getChannelParams()));
		channelEntity = channelMapper.selectMerChannelWithParms(merId, Currency.JPY);
		assertNotNull(channelEntity);
		assertTrue(3 == CollectionUtils.size(channelEntity.getChannelParams()));
	}

	@Test
	@Transactional
	public void testSelectMerDefaultChannelWithParms() {
		String merId = createChannelAndMapping();
		ChannelEntity channelEntity = channelMapper.selectMerDefaultChannelWithParams(merId);
		assertNotNull(channelEntity);
		assertTrue(0 == CollectionUtils.size(channelEntity.getChannelParams()));
	}

	/**
	 * @return merchant ID
	 */
	private String createChannelAndMapping() {

		// merchant
		MerchantEntity merchantEntity = newMerchant();
		merchantMapper.insert(merchantEntity);

		// default channel, with no parameter
		ChannelEntity usdChannel = newChannelEntity();
		usdChannel.setTransactionCurrency(Currency.USD);
		channelMapper.insert(usdChannel);

		// other channel, with 3 parameters
		ChannelEntity jpyChannel = newChannelEntity();
		jpyChannel.setID(SecurityUtils.UUID());
		jpyChannel.setTransactionCurrency(Currency.JPY);
		channelMapper.insert(jpyChannel);

		for (int i = 0; i < 3; i++) {
			channelParameterMapper.insert(ChannelParameterMapperTest.newChannelParameterEntity(i, jpyChannel.getID()));
		}

		ChannelMappingEntity channelMappingEntity = new ChannelMappingEntity();
		channelMappingEntity.setID(SecurityUtils.UUID());
		channelMappingEntity.setChannelId(usdChannel.getID());
		channelMappingEntity.setMerId(merchantEntity.getID());
		channelMappingEntity.setDefaultChannel(true);
		channelMappingMapper.insert(channelMappingEntity);

		channelMappingEntity = new ChannelMappingEntity();
		channelMappingEntity.setID(SecurityUtils.UUID());
		channelMappingEntity.setChannelId(jpyChannel.getID());
		channelMappingEntity.setMerId(merchantEntity.getID());
		channelMappingMapper.insert(channelMappingEntity);
		channelMappingEntity.setDefaultChannel(false);

		return merchantEntity.getID();
	}

	public MerchantEntity newMerchant() {
		MerchantEntity mer = new MerchantEntity();
		mer.setID(SecurityUtils.UUID());
		mer.setName("京东");
		mer.setAbbreviation("CN");
		mer.setMerchantType("1");// glbpay中：正品1，仿品2，虚拟物品3
		mer.setFederalID("1");
		mer.setTaxID("1");
		mer.setTransactionURLs("1");
		mer.setIndustry("1");
		mer.setContactPerson("刘强东");
		mer.setContactPhone("15618387186");
		mer.setContactEmail("test@126.com");
		mer.setAddressBook(null);
		mer.setEffectiveDate(new Date());
		mer.setTerminateDate(new Date());
		mer.setTimeZone(1.5f);
		mer.setTimeZoneName("CN");
		mer.setComments("ok");
		mer.setOwnerId("1");
		mer.setStatus(MerchantStatusEnum.ACTIVE);
		mer.setCreateDate(new Date());
		mer.setUpdateDate(mer.getCreateDate());
		mer.setMerchantCode(RandomStringUtils.randomAlphanumeric(10));
		mer.setMcc(RandomStringUtils.randomNumeric(4));
		return mer;
	}

	public ChannelEntity newChannelEntity() {
		ChannelEntity channel = new ChannelEntity();
		channel.setID(SecurityUtils.UUID());
		channel.setAcquirerId(SecurityUtils.UUID());
		channel.setAcquirerName(RandomStringUtils.randomAlphanumeric(10));
		channel.setChannelProviderId(SecurityUtils.UUID());
		channel.setChannelProviderName("UNION EXPRESS");
		channel.setProviderClassifier(ChannelProviderClassifier.UNIONPAY_EXPRESS_PAY);
		channel.setTransactionCurrency(Currency.CNY);
		channel.setSettlementCurrency(Currency.USD);
		channel.setCardOrg(getRandomEnum(CardOrgEnum.class));
		channel.setStatus(getRandomEnum(ChannelStatusEnum.class));
		channel.setDueDate(new Date());
		channel.setComments("test");
		channel.setCreateDate(new Date());
		channel.setUpdateDate(channel.getCreateDate());
		return channel;
	}
}
