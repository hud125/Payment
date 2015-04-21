package com.aurfy.haze.service.impl.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.configuration.ChannelStatusEnum;
import com.aurfy.haze.core.model.configuration.channel.ChannelProviderClassifier;
import com.aurfy.haze.core.model.infra.mer.MerchantStatusEnum;
import com.aurfy.haze.core.model.settlement.FreezePolicyEnum;
import com.aurfy.haze.core.model.settlement.ReconciliationDiffPolicyEnum;
import com.aurfy.haze.core.model.settlement.SettleConditionEnum;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.infrastructure.MerchantService;
import com.aurfy.haze.service.bean.configuration.channel.ChannelBean;
import com.aurfy.haze.service.bean.infra.mer.MerchantBean;
import com.aurfy.haze.service.bean.settlement.MerSettleConfigBean;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.service.impl.configuration.channel.ChannelServiceTest;
import com.aurfy.haze.utils.SecurityUtils;

public class MerchantConfigServiceTest extends ServiceUnitTest {

	@Autowired
	private MerchantService merSvr;

	@Autowired
	private CRUDService crudSvr;

	@Test
	@Transactional
	public void testGetChannel() {
		try {
			String merId = createChannelAndMapping();
			ChannelBean channel;

			// check by currency
			channel = merSvr.getChannel(merId, Currency.USD);
			assertNotNull(channel);
			assertEquals(Currency.USD, channel.getTransactionCurrency());

			channel = merSvr.getChannel(merId, Currency.JPY);
			assertNotNull(channel);
			assertEquals(Currency.JPY, channel.getTransactionCurrency());

			// check by default (non-exist currency)
			channel = merSvr.getChannel(merId, Currency.AUD);
			assertNotNull(channel);
			assertEquals(Currency.USD, channel.getTransactionCurrency());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testGetSettleConfig() {
		try {
			MerSettleConfigBean merSettleConfigBean = newMerSettleConf();
			merSettleConfigBean = (MerSettleConfigBean) crudSvr.create(merSettleConfigBean);
			assertNotNull(merSettleConfigBean);
			assertNotNull(merSvr.getSettleConfig(merSettleConfigBean.getID()));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * @return merchant id
	 */
	private String createChannelAndMapping() throws Exception {
		// merchant
		MerchantBean merchant = newMerchant();
		crudSvr.create(merchant);

		// default channel
		ChannelBean usdChannel = new ChannelServiceTest().newChannelBean();
		usdChannel.setTransactionCurrency(Currency.USD);
		usdChannel = (ChannelBean) crudSvr.create(usdChannel);

		boolean r1 = merSvr.addChannelMapping(merchant.getID(), usdChannel.getID(), true);
		assertTrue(r1);

		// other channel
		ChannelBean jpyChannel = new ChannelServiceTest().newChannelBean();
		jpyChannel.setTransactionCurrency(Currency.JPY);
		jpyChannel = (ChannelBean) crudSvr.create(jpyChannel);

		boolean r2 = merSvr.addChannelMapping(merchant.getID(), jpyChannel.getID(), false);
		assertTrue(r2);

		return merchant.getID();
	}

	private MerchantBean newMerchant() {
		MerchantBean mer = new MerchantBean();
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

	public ChannelBean newChannelBean() {
		ChannelBean channel = new ChannelBean();
		channel.setID(UUID.randomUUID().toString());
		channel.setAcquirerId(SecurityUtils.UUID());
		channel.setAcquirerName(RandomStringUtils.randomAlphabetic(10));
		channel.setChannelProviderId(SecurityUtils.UUID());
		channel.setProviderClassifier(ChannelProviderClassifier.UNIONPAY_EXPRESS_PAY);
		channel.setTransactionCurrency(Currency.AED);
		channel.setSettlementCurrency(Currency.AUD);
		channel.setCardOrg(getRandomEnum(CardOrgEnum.class));
		channel.setStatus(getRandomEnum(ChannelStatusEnum.class));
		channel.setDueDate(new Date());
		channel.setComments("test");
		channel.setCreateDate(new Date());
		channel.setUpdateDate(channel.getCreateDate());
		return channel;
	}

	public MerSettleConfigBean newMerSettleConf() throws Exception {
		// merchant
		MerchantBean merchant = newMerchant();
		merchant = (MerchantBean) crudSvr.create(merchant);

		MerSettleConfigBean msc = new MerSettleConfigBean();
		msc.setID(SecurityUtils.UUID());
		msc.setMerId(merchant.getID());
		msc.setSubMerID(null);
		msc.setSettleCurrencies("CNY,USD,JPY");
		msc.setSettleCondition(getRandomEnum(SettleConditionEnum.class));
		msc.setSettlePeriodDay(7);
		msc.setMaxDeliveryDay(30);
		msc.setReconciliationDiffPolicy(ReconciliationDiffPolicyEnum.AUTO_REFUND);
		msc.setDepositRate(BigDecimal.valueOf(0.01));
		msc.setFreezePolicy(getRandomEnum(FreezePolicyEnum.class));
		return msc;
	}
}
