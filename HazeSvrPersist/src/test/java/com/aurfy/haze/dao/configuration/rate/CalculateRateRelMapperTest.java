package com.aurfy.haze.dao.configuration.rate;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.configuration.rate.CalculateRateEntity;
import com.aurfy.haze.entity.configuration.rate.CalculateRateRelEntity;
import com.aurfy.haze.entity.configuration.rate.RateConfigEntity;

public class CalculateRateRelMapperTest extends PersistUnitTest {

	@Autowired
	private CalculateRateRelMapper crrMapper;

	@Autowired
	private RateConfigMapper rateConfMapper;

	@Autowired
	private CalculateRateMapper crMapper;

	public CalculateRateRelEntity newCalculateRateRel() {
		CalculateRateEntity calculateRate = new CalculateRateMapperTest().newCalculateRate();
		int count = crMapper.insert(calculateRate);
		assertTrue(count == 1);

		RateConfigEntity rateConfig = new RateConfigMapperTest().newRateConfig();
		count = rateConfMapper.insert(rateConfig);
		assertTrue(count == 1);

		CalculateRateRelEntity crrEntity = new CalculateRateRelEntity();
		crrEntity.setCalculateRate(calculateRate);
		crrEntity.setRateConfig(rateConfig);
		return crrEntity;
	}

	public CalculateRateRelEntity getCalculateRateRel() {
		CalculateRateRelEntity crrEntity = newCalculateRateRel();
		int count = crrMapper.insert(crrEntity);
		assertTrue(count == 1);
		return crrEntity;
	}

	@Test
	@Transactional
	public void testCreateCalculateRateRel() {
		getCalculateRateRel();
	}

	@Test
	@Transactional
	public void testSelectCalculateRateRelByRateConf() {
		CalculateRateRelEntity crrEntity = getCalculateRateRel();
		CalculateRateRelEntity crrEntity2 = crrMapper.selectCalculateRateRelByRateConf(crrEntity);
		assertTrue(crrEntity.equals(crrEntity2));
	}

	@Test
	@Transactional
	public void testSelectCalculateRateRelByCalculateRate() {
		CalculateRateRelEntity crrEntity = getCalculateRateRel();
		CalculateRateRelEntity crrEntity2 = crrMapper.selectCalculateRateRelByCalculateRate(crrEntity);
		assertTrue(crrEntity.equals(crrEntity2));
	}

	@Test
	@Transactional
	public void testUpdateCalculateRateRelByCalculateRate() {
		CalculateRateRelEntity crrEntity = getCalculateRateRel();
		CalculateRateRelEntity newCrrEntity = newCalculateRateRel();
		newCrrEntity.setRateConfig(crrEntity.getRateConfig());
		int count = crrMapper.updateCalculateRateRelByCalculateRate(newCrrEntity);
		assertTrue(count == 1);
		assertTrue(!crrEntity.equals(newCrrEntity));
	}

	@Test
	@Transactional
	public void testDeleteCalculateRateRelByRateConfAndCalculateRate() {
		CalculateRateRelEntity crrEntity = getCalculateRateRel();
		int count = crrMapper.deleteCalculateRateRelByRateConfAndCalculateRate(crrEntity);
		assertTrue(count == 1);
	}

}
