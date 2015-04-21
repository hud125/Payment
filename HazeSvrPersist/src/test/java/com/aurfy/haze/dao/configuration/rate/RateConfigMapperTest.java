package com.aurfy.haze.dao.configuration.rate;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.configuration.rate.RateRoleClassifierEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.configuration.rate.CalculateRateEntity;
import com.aurfy.haze.entity.configuration.rate.CalculateRateRelEntity;
import com.aurfy.haze.entity.configuration.rate.RateConfigEntity;
import com.aurfy.haze.entity.configuration.rate.SteppedRateEntity;
import com.aurfy.haze.entity.configuration.rate.SteppedRateRelEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class RateConfigMapperTest extends PersistUnitTest {

	@Autowired
	private RateConfigMapper rateConfMapper;
	
	@Autowired
	private SteppedRateRelMapper srrMapper;
	
	@Autowired
	private SteppedRateMapper srMapper;
	
	@Autowired
	private CalculateRateRelMapper crrMapper;

	@Autowired
	private CalculateRateMapper crMapper;

	public RateConfigEntity newRateConfig() {
		RateConfigEntity rcEntity = new RateConfigEntity();
		rcEntity.setID(SecurityUtils.UUID());
		rcEntity.setTargetId("1246dfc2-519c-4cf0-83fe-605addf36b1d");
		rcEntity.setRateRoleClassifier(RateRoleClassifierEnum.MERCHANT);
		rcEntity.setComments("junit test");
		rcEntity.setCreateDate(new Date());
		rcEntity.setUpdateDate(rcEntity.getCreateDate());
		return rcEntity;
	}

	public RateConfigEntity getRateConfig() {
		RateConfigEntity rcEntity = newRateConfig();
		int index = rateConfMapper.insert(rcEntity);
		assertTrue(index == 1);
		return rcEntity;
	}

	@Test
	@Transactional
	public void testCreateRateConfig() {
		getRateConfig();
	}

	@Test
	@Transactional
	public void testRetrieveRateConfig() {
		RateConfigEntity rcEntity = getRateConfig();
		RateConfigEntity rcEntity2 = rateConfMapper.selectOne(rcEntity.getID());
		assertTrue(rcEntity2.equals(rcEntity));
	}

	@Test
	@Transactional
	public void testUpdateRateConfig() {
		RateConfigEntity rcEntity = getRateConfig();

		RateConfigEntity rcEntity2 = new RateConfigEntity();
		rcEntity2.setID(rcEntity.getID());
		rcEntity2.setComments("fast test");
		int index = rateConfMapper.update(rcEntity2);
		assertTrue(index == 1);
	}

	@Test
	@Transactional
	public void testRemoveRateConfig() {
		RateConfigEntity rcEntity = getRateConfig();
		int index = rateConfMapper.delete(rcEntity.getID());
		assertTrue(index == 1);
	}

	public RateConfigEntity getRateConfig2() {
		RateConfigEntity rateConfig = newRateConfig();
		int count = rateConfMapper.insert(rateConfig);
		assertTrue(count == 1);
		
		CalculateRateEntity calculateRate = new CalculateRateMapperTest().newCalculateRate();
		count = crMapper.insert(calculateRate);
		assertTrue(count == 1);

		CalculateRateRelEntity crrEntity = new CalculateRateRelEntity();
		crrEntity.setCalculateRate(calculateRate);
		crrEntity.setRateConfig(rateConfig);
		count = crrMapper.insert(crrEntity);
		assertTrue(count == 1);
		
		SteppedRateEntity steppedRate = new SteppedRateMapperTest().newSteppedRate();
		count = srMapper.insert(steppedRate);
		assertTrue(count == 1);

		SteppedRateRelEntity srrEntity = new SteppedRateRelEntity();
		srrEntity.setSteppedRate(steppedRate);
		srrEntity.setRateConfig(rateConfig);
		count = srrMapper.insert(srrEntity);
		assertTrue(count == 1);
		
		List<CalculateRateEntity> calculateRates = new ArrayList<CalculateRateEntity>();
		calculateRates.add(calculateRate);
		
		List<SteppedRateEntity> steppedRates = new ArrayList<SteppedRateEntity>();
		steppedRates.add(steppedRate);
		
		rateConfig.setCalculateRates(calculateRates);
		rateConfig.setSteppedRates(steppedRates);
		return rateConfig;
	}
	
	@Test
	@Transactional
	public void testSelectOneWithParams() {
		RateConfigEntity rcEntity = getRateConfig2();
		RateConfigEntity rcEntity2 = rateConfMapper.selectOneWithParams(rcEntity);
		assertTrue(rcEntity.getCalculateRates().get(0).equals(rcEntity2.getCalculateRates().get(0)));
		assertTrue(rcEntity.getSteppedRates().get(0).equals(rcEntity2.getSteppedRates().get(0)));
	}
	
	@Test
	@Transactional
	public void testSelectByWithParams() {
		RateConfigEntity rcEntity = getRateConfig2();
		List<RateConfigEntity> rcEntitys = rateConfMapper.selectByWithParams(rcEntity);
		assertTrue(rcEntity.getCalculateRates().get(0).equals(rcEntitys.get(0).getCalculateRates().get(0)));
		assertTrue(rcEntity.getSteppedRates().get(0).equals(rcEntitys.get(0).getSteppedRates().get(0)));
	}
	
	

}
