package com.aurfy.haze.dao.configuration.rate;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.core.model.configuration.rate.RateAlgorithmEnum;
import com.aurfy.haze.core.model.configuration.rate.RateClassifierEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.configuration.rate.CalculateRateEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class CalculateRateMapperTest extends PersistUnitTest {

	@Autowired
	private CalculateRateMapper crMapper;

	public CalculateRateEntity newCalculateRate() {
		CalculateRateEntity crEntity = new CalculateRateEntity();
		crEntity.setID(SecurityUtils.UUID());
		crEntity.setCardOrg(CardOrgEnum.MASTER);
		crEntity.setCurrency(Currency.CNY);
		crEntity.setRateClassifier(RateClassifierEnum.SURVEY);
		crEntity.setRateAlgorithm(RateAlgorithmEnum.PERCENTAGE);
		crEntity.setPercentage(new BigDecimal("0.0020"));
		crEntity.setFixedAmount(new BigDecimal("35456.6898"));
		crEntity.setMinAmount(new BigDecimal("7854.2400"));
		crEntity.setMaxAmount(new BigDecimal("8644.9065"));
		crEntity.setComments(RandomStringUtils.randomAlphanumeric(50));
		crEntity.setOwnerId(SecurityUtils.UUID());
		crEntity.setCreateDate(new Date());
		crEntity.setUpdateDate(crEntity.getCreateDate());

		return crEntity;
	}

	public CalculateRateEntity getCalculateRate() {
		CalculateRateEntity crEntity = newCalculateRate();
		int count = crMapper.insert(crEntity);
		assertTrue(count == 1);
		return crEntity;
	}

	@Test
	@Transactional
	public void testCreateCalculateRate() {
		getCalculateRate();
	}

	@Test
	@Transactional
	public void testRetrieveCalculateRate() {
		CalculateRateEntity crEntity = getCalculateRate();
		CalculateRateEntity crEntity2 = crMapper.selectOne(crEntity.getID());
		assertTrue(crEntity2.equals(crEntity));
	}

	@Test
	@Transactional
	public void testUpdateCalculateRate() {
		CalculateRateEntity crEntity = getCalculateRate();

		CalculateRateEntity crEntity2 = new CalculateRateEntity();
		crEntity2.setID(crEntity.getID());
		crEntity2.setComments("fast test");
		int index = crMapper.update(crEntity2);
		assertTrue(index == 1);
	}

	@Test
	@Transactional
	public void testRemoveCalculateRate() {
		CalculateRateEntity crEntity = getCalculateRate();
		int index = crMapper.delete(crEntity.getID());
		assertTrue(index == 1);
	}

}
