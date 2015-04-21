package com.aurfy.haze.dao.settlement;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.settlement.FreezePolicyEnum;
import com.aurfy.haze.core.model.settlement.ReconciliationDiffPolicyEnum;
import com.aurfy.haze.core.model.settlement.SettleConditionEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.mer.MerchantMapper;
import com.aurfy.haze.dao.infrastructure.MerchantMapperTest;
import com.aurfy.haze.entity.infra.mer.MerchantEntity;
import com.aurfy.haze.entity.settlement.MerSettleConfigEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class MerSettleConfigMapperTest extends PersistUnitTest {

	@Autowired
	private MerchantMapper merMapper;

	@Autowired
	private MerSettleConfigMapper mapper;

	@Test
	@Transactional
	public void testInsert() {
		MerSettleConfigEntity msc = newMerSettleConf();
		String oldId = msc.getID();
		int count = mapper.insert(msc);
		assertTrue(count == 1);
		String newId = msc.getID();
		assertTrue(oldId.equals(newId));
	}

	@Test
	@Transactional
	public void testDelete() {
		MerSettleConfigEntity msc = newMerSettleConf();
		int count = mapper.insert(msc);
		assertTrue(count == 1);
		assertTrue(mapper.delete(msc.getID()) == 1);
	}

	@Test
	@Transactional
	public void testSeleteOne() {
		MerSettleConfigEntity msc = newMerSettleConf();
		int count = mapper.insert(msc);
		assertTrue(count == 1);
		assertNotNull(mapper.selectOne(msc.getID()));

	}

	@Test
	@Transactional
	public void testSeleteAll() {
		int oldSize = mapper.selectAll().size();
		assertTrue(mapper.insert(newMerSettleConf()) == 1);
		assertTrue(mapper.selectAll().size() == oldSize + 1);
	}

	@Test
	@Transactional
	public void testUpdate() {
		MerSettleConfigEntity oldMsc = newMerSettleConf();
		int count = mapper.insert(oldMsc);
		assertTrue(count == 1);
		String settleCurrencies = "jUnitsc";
		oldMsc.setSettleCurrencies(settleCurrencies);
		assertTrue(mapper.update(oldMsc) == 1);
		MerSettleConfigEntity newMsc = mapper.selectOne(oldMsc.getID());
		assertTrue(newMsc.getSettleCurrencies().equals(settleCurrencies));
	}

	public MerSettleConfigEntity newMerSettleConf() {
		MerchantEntity mer = MerchantMapperTest.newMerchantWithNullAddr();
		// create a new merchant
		String oldId = mer.getID();
		int count = merMapper.insert(mer);
		String newId = mer.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);

		MerSettleConfigEntity msc = new MerSettleConfigEntity();
		msc.setID(SecurityUtils.UUID());
		msc.setMerId(mer.getID());
		msc.setSubMerID(null);
		msc.setSettleCurrencies("CNY");
		msc.setSettleCondition(SettleConditionEnum.BY_PERIOD);
		msc.setSettlePeriodDay(7);
		msc.setMaxDeliveryDay(15);
		msc.setReconciliationDiffPolicy(ReconciliationDiffPolicyEnum.AUTO_REFUND);
		msc.setDepositRate(BigDecimal.valueOf(0.01));
		msc.setFreezePolicy(FreezePolicyEnum.AUTO_FREEZE);

		return msc;
	}
}
