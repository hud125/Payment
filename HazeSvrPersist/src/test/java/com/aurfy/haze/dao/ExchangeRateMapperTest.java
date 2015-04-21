package com.aurfy.haze.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.dao.infra.ExchangeRateMapper;
import com.aurfy.haze.entity.common.HelpQAEntity;
import com.aurfy.haze.entity.infra.ExchangeRateEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class ExchangeRateMapperTest extends PersistUnitTest {

	@Autowired
	private ExchangeRateMapper mapper;

	private ExchangeRateEntity newExchangeRateEntity() {
		ExchangeRateEntity entityExRate = new ExchangeRateEntity();
		entityExRate.setID(SecurityUtils.UUID());
		entityExRate.setExchangeName("JunitName_" + RandomStringUtils.randomAlphanumeric(6));
		entityExRate.setFromCurrency(Currency.USD);
		entityExRate.setToCurrency(Currency.USD);
		entityExRate.setBuyRate(new BigDecimal("1.0000"));
		entityExRate.setCashBuyRate(new BigDecimal("1.0000"));
		entityExRate.setSellRate(new BigDecimal("1.0000"));
		entityExRate.setCashSellRate(new BigDecimal("1.0000"));
		entityExRate.setActive(true);
		entityExRate.setComments("This is test ExchangeRate");
		entityExRate.setOwnerId("Sam");
		entityExRate.setCreateDate(new Date());
		entityExRate.setUpdateDate(entityExRate.getCreateDate());
		return entityExRate;
	}

	/**
	 * insert new exchangeRate in system.
	 * 
	 * @return
	 */
	public ExchangeRateEntity getNewExchangeRate() {
		// create a new excheangeRate
		ExchangeRateEntity coEntity = newExchangeRateEntity();
		String oldId = coEntity.getID();
		int count = mapper.insert(coEntity);
		String newId = coEntity.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return coEntity;
	}

	@Test
	@Transactional
	public void testInsertExchangeRate() {
		getNewExchangeRate();
	}

	@Test
	@Transactional
	public void testSelectExchangeRate() {

		ExchangeRateEntity newExchangeRate = getNewExchangeRate();
		;
		ExchangeRateEntity ExchangeRateEntity = mapper.selectOne(newExchangeRate.getID());
		assertNotNull(ExchangeRateEntity);
		assertTrue(newExchangeRate.getID().equals(ExchangeRateEntity.getID()));
	}
	
	@Test
	@Transactional
	public void testSelectAllExchangeRate() {

		int oldSize = mapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			getNewExchangeRate();
		}
		assertTrue(oldSize + randomSize == mapper.selectAll().size());
	}

	@Test
	@Transactional
	public void testSelectExchangeRateByName() {

		ExchangeRateEntity newExchangeRate = getNewExchangeRate();
		String name = newExchangeRate.getExchangeName();
		ExchangeRateEntity coEntityList = mapper.selectExchangeRateByName(name);
		assertTrue(coEntityList.getExchangeName().equals(newExchangeRate.getExchangeName()));
	}

	@Test
	@Transactional
	public void testSelectExchangeRateByCurrency() {

		ExchangeRateEntity newExchangeRate = getNewExchangeRate();
		Currency fromCurrency = newExchangeRate.getFromCurrency();
		Currency toCurrency = newExchangeRate.getToCurrency();
		ExchangeRateEntity tempExchangeRate = mapper.selectExchangeRateByCurrency(fromCurrency.getName(),
				toCurrency.getName());
		assertTrue(newExchangeRate.equals(tempExchangeRate));
	}

	@Test
	@Transactional
	public void testListExchangeRate() {
		for (int i = 0; i < RandomUtils.nextInt(1, 10); ++i) {
			getNewExchangeRate();
		}
		List<ExchangeRateEntity> exchangeRates = mapper.selectAll();
		assertTrue(exchangeRates.size() > 0);
	}
	
	@Test
	@Transactional
	public void testCountBy() {
		ExchangeRateEntity coentity = getNewExchangeRate();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			getNewExchangeRate();
		}
		assertNotNull(mapper.countBy(coentity));
	}
	
	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = mapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for(int i = 0;i<randomSize;i++) {
			getNewExchangeRate();
		}
		assertTrue(oldSize + randomSize == mapper.countAll());
	}

	@Test
	@Transactional
	public void testUpdateExchangeRate() {
		ExchangeRateEntity newExchangeRate = getNewExchangeRate();
		ExchangeRateEntity coEntity = new ExchangeRateEntity();

		coEntity.setExchangeName("JunitName_" + RandomStringUtils.randomAlphanumeric(6));
		coEntity.setFromCurrency(Currency.USD);
		coEntity.setToCurrency(Currency.USD);
		coEntity.setBuyRate(new BigDecimal("1.0000"));
		coEntity.setCashBuyRate(new BigDecimal("1.0000"));
		coEntity.setSellRate(new BigDecimal("1.0000"));
		coEntity.setCashSellRate(new BigDecimal("1.0000"));
		coEntity.setActive(true);
		coEntity.setComments("This is test ExchangeRate");
		coEntity.setOwnerId("Elise");
		coEntity.setCreateDate(new Date());
		coEntity.setUpdateDate(coEntity.getCreateDate());
		coEntity.setID(newExchangeRate.getID());

		int count = mapper.update(coEntity);
		ExchangeRateEntity co = mapper.selectOne(coEntity.getID());
		assertTrue(count == 1);
		assertTrue(!coEntity.equals(newExchangeRate));
		assertTrue(!co.equals(newExchangeRate));
		assertTrue(coEntity.equals(co));
	}

	@Test
	@Transactional
	public void testDeleteExchangeRate() {
		ExchangeRateEntity newExchangeRate = getNewExchangeRate();
		int count = mapper.delete(newExchangeRate.getID());
		assertTrue(count == 1);
	}

}

