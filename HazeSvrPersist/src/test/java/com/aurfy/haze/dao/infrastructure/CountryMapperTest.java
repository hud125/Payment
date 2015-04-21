package com.aurfy.haze.dao.infrastructure;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.CountryMapper;
import com.aurfy.haze.entity.infra.CountryEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class CountryMapperTest extends PersistUnitTest {

	@Autowired
	private CountryMapper mapper;

	public CountryEntity newCountryEntity() {
		CountryEntity entityCtr = new CountryEntity();
		// no country id set
		entityCtr.setID(SecurityUtils.UUID());
		entityCtr.setName("China");
		entityCtr.setAbbreviation("CN");
		entityCtr.setCountryCode("86");
		entityCtr.setCreateDate(new Date());
		entityCtr.setUpdateDate(entityCtr.getCreateDate());
		return entityCtr;
	}

	/**
	 * insert new country in system.
	 * 
	 * @return
	 */
	public CountryEntity getNewCountry() {
		// create a new country
		CountryEntity coEntity = newCountryEntity();
		String oldId = coEntity.getID();
		int count = mapper.insert(coEntity);
		String newId = coEntity.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return coEntity;
	}

	@Test
	@Transactional
	public void testInsert() {
		// create a new country
		getNewCountry();
	}

	@Test
	@Transactional
	public void testSelect() {
		// get country by the given id
		CountryEntity newCountry = getNewCountry();
		CountryEntity CountryEntity = mapper.selectOne(newCountry.getID());
		assertNotNull(CountryEntity);
		assertTrue(CountryEntity.getID().equals(newCountry.getID()));
	}

	@Test
	@Transactional
	public void testSelectBy() {
		// get country by the given name
		CountryEntity newCountry = getNewCountry();
		List<CountryEntity> coEntityList = mapper.selectBy(newCountry, 0, 10);
		assertTrue(coEntityList.size() > 0);
	}

	@Test
	@Transactional
	public void testUpdate() {
		CountryEntity newCountry = getNewCountry();
		CountryEntity coEntity = new CountryEntity();
		coEntity.setName("America");
		coEntity.setAbbreviation("US");
		coEntity.setCountryCode("00");
		coEntity.setCreateDate(new Date());
		coEntity.setUpdateDate(coEntity.getCreateDate());
		coEntity.setID(newCountry.getID());

		int count = mapper.update(coEntity);
		CountryEntity co = mapper.selectOne(coEntity.getID());
		assertTrue(count == 1);
		assertTrue(!coEntity.equals(newCountry));
		assertTrue(coEntity.equals(co));
	}

	@Test
	@Transactional
	public void testDelete() {
		CountryEntity newCountry = getNewCountry();
		// delete the new country
		int count = mapper.delete(newCountry.getID());
		assertTrue(count == 1);
	}

}
