package com.aurfy.haze.dao.configuration.rate;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.rate.RateRoleClassifierEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.configuration.rate.SteppedRateEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class SteppedRateMapperTest extends PersistUnitTest {

	@Autowired
	private SteppedRateMapper srMapper;

	public SteppedRateEntity newSteppedRate() {
		SteppedRateEntity srEntity = new SteppedRateEntity();
		srEntity.setID(SecurityUtils.UUID());
		srEntity.setTargetId(SecurityUtils.UUID());
		srEntity.setCurrency(Currency.CNY);
		srEntity.setRateRoleClassifier(RateRoleClassifierEnum.PSP);
		srEntity.setMinBound(new BigInteger("234567856785432"));
		srEntity.setPercentage(new BigDecimal("0.0020"));
		srEntity.setFixedAmount(new BigDecimal("35456.6898"));
		srEntity.setMinAmount(new BigDecimal("7854.2400"));
		srEntity.setMaxAmount(new BigDecimal("8644.9065"));
		srEntity.setComments(RandomStringUtils.randomAlphanumeric(50));
		srEntity.setOwnerId(SecurityUtils.UUID());
		srEntity.setCreateDate(new Date());
		srEntity.setUpdateDate(srEntity.getCreateDate());

		return srEntity;
	}
	
	public SteppedRateEntity getSteppedRate() {
		SteppedRateEntity srEntity = newSteppedRate();
		int count = srMapper.insert(srEntity);
		assertTrue(count == 1);
		return srEntity;
	}

	@Test
	@Transactional
	public void testCreateSteppedRate() {
		getSteppedRate();
	}

	@Test
	@Transactional
	public void testRetrieveSteppedRate() {
		SteppedRateEntity srEntity = getSteppedRate();
		SteppedRateEntity srEntity2 = srMapper.selectOne(srEntity.getID());
		assertTrue(srEntity2.equals(srEntity));
	}

	@Test
	@Transactional
	public void testUpdateSteppedRate() {
		SteppedRateEntity srEntity = getSteppedRate();

		SteppedRateEntity srEntity2 = new SteppedRateEntity();
		srEntity2.setID(srEntity.getID());
		srEntity2.setComments("fast test");
		int index = srMapper.update(srEntity2);
		assertTrue(index == 1);
	}

	@Test
	@Transactional
	public void testRemoveSteppedRate() {
		SteppedRateEntity srEntity = getSteppedRate();
		int index = srMapper.delete(srEntity.getID());
		assertTrue(index == 1);
	}

}
