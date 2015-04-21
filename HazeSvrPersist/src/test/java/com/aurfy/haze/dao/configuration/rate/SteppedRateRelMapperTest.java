package com.aurfy.haze.dao.configuration.rate;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.configuration.rate.RateConfigEntity;
import com.aurfy.haze.entity.configuration.rate.SteppedRateEntity;
import com.aurfy.haze.entity.configuration.rate.SteppedRateRelEntity;

public class SteppedRateRelMapperTest extends PersistUnitTest {

	@Autowired
	private SteppedRateRelMapper srrMapper;
	
	@Autowired
	private SteppedRateMapper srMapper;
	
	@Autowired
	private RateConfigMapper rateConfMapper;
	
	public SteppedRateRelEntity newSteppedRateRel() {
		SteppedRateEntity steppedRate = new SteppedRateMapperTest().newSteppedRate();
		int count = srMapper.insert(steppedRate);
		assertTrue(count == 1);

		RateConfigEntity rateConfig = new RateConfigMapperTest().newRateConfig();
		count = rateConfMapper.insert(rateConfig);
		assertTrue(count == 1);

		SteppedRateRelEntity srrEntity = new SteppedRateRelEntity();
		srrEntity.setSteppedRate(steppedRate);
		srrEntity.setRateConfig(rateConfig);
		return srrEntity;
	}

	public SteppedRateRelEntity getSteppedRateRel() {
		SteppedRateRelEntity srrEntity = newSteppedRateRel();
		int count = srrMapper.insert(srrEntity);
		assertTrue(count == 1);
		return srrEntity;
	}

	@Test
	@Transactional
	public void testCreateSteppedRateRel() {
		getSteppedRateRel();
	}
	
	@Test
	@Transactional
	public void testSelectSteppedRateRelByRateConf() {
		SteppedRateRelEntity srrEntity = getSteppedRateRel();
		SteppedRateRelEntity srrEntity2 = srrMapper.selectSteppedRateRelByRateConf(srrEntity);
		assertTrue(srrEntity.equals(srrEntity2));
	}

	@Test
	@Transactional
	public void testSelectSteppedRateRelBySteppedRate() {
		SteppedRateRelEntity srrEntity = getSteppedRateRel();
		SteppedRateRelEntity srrEntity2 = srrMapper.selectSteppedRateRelBySteppedRate(srrEntity);
		assertTrue(srrEntity.equals(srrEntity2));
	}

	@Test
	@Transactional
	public void testUpdateSteppedRateRelBySteppedRate() {
		SteppedRateRelEntity srrEntity = getSteppedRateRel();
		SteppedRateRelEntity newsrrEntity = newSteppedRateRel();
		newsrrEntity.setRateConfig(srrEntity.getRateConfig());
		int count = srrMapper.updateSteppedRateRelBySteppedRate(newsrrEntity);
		assertTrue(count == 1);
		assertTrue(!srrEntity.equals(newsrrEntity));
	}

	@Test
	@Transactional
	public void testDeleteSteppedRateRelByRateConfAndSteppedRate() {
		SteppedRateRelEntity srrEntity = getSteppedRateRel();
		int count = srrMapper.deleteSteppedRateRelByRateConfAndSteppedRate(srrEntity);
		assertTrue(count == 1);
	}
	
}
