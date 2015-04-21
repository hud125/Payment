package com.aurfy.haze.dao.infrastructure;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.CityMapper;
import com.aurfy.haze.dao.infra.CountryMapper;
import com.aurfy.haze.dao.infra.StateProvMapper;
import com.aurfy.haze.entity.infra.CityEntity;
import com.aurfy.haze.entity.infra.CountryEntity;
import com.aurfy.haze.entity.infra.StateProvEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class CityMapperTest extends PersistUnitTest {
	
	@Autowired
	private CityMapper mapper;
	
	@Autowired
	private StateProvMapper state_mapper;
	
	@Autowired
	private CountryMapper country_mapper;
	
	/**
	 * create new state of java obj.
	 * if no country in system, create one.
	 * @return
	 */
	public StateProvEntity newStateProvEntity(){
		//create a new country
		CountryEntity coEntity = new CountryMapperTest().newCountryEntity();
		String oldId = coEntity.getID();
		int count = country_mapper.insert(coEntity);
		String newId = coEntity.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		
		StateProvEntity spe = new StateProvEntity();
		spe.setID(SecurityUtils.UUID());
		spe.setCountry(coEntity);
		spe.setCreateDate(new Date());
		spe.setUpdateDate(spe.getCreateDate());
		spe.setName("上海市");
		return spe;
	}
	
	public CityEntity newCityEntity(){
		//create a new state
		StateProvEntity stateProv = newStateProvEntity();
		String oldId = stateProv.getID();
		int count = state_mapper.insert(stateProv);
		String newId = stateProv.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		
		CityEntity city = new CityEntity();
		city.setID(SecurityUtils.UUID());
		city.setName("徐汇区");
		city.setCountry(stateProv.getCountry());
		city.setState(stateProv);
		city.setCreateDate(new Date());
		city.setUpdateDate(city.getCreateDate());
		return city;
	}
	
	/**
	 * get max city_id in system.
	 * if none, create one
	 * @return
	 */
	public CityEntity getNewCity(){
		CityEntity city = newCityEntity();
		//create a new state
		String oldId = city.getID();
		int count = mapper.insert(city);
		String newId = city.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return city;
	}
	
	@Test
	@Transactional
	public void TestInsert(){
		getNewCity();
	}
	
	@Test
	@Transactional
	public void testSelectOne() {
		//get country associated with the given id
		CityEntity city = getNewCity();
		CityEntity CityEntity = mapper.selectOne(city.getID());
		assertNotNull(CityEntity);
		assertTrue(CityEntity.getID().equals(city.getID()));
	}
	
	@Test
	@Transactional
	public void testSelectAll(){
		getNewCity();
		List<CityEntity> ciEntityList = mapper.selectAll();
		assertTrue(ciEntityList.size() > 0);
	}
	
	@Test
	@Transactional
	public void testUpdate(){
		CityEntity city = getNewCity();
		CityEntity ciEntity = new CityEntity();
		ciEntity.setName("黄浦区");
		ciEntity.setUpdateDate(new Date());
		ciEntity.setID(city.getID());
		int count = mapper.update(ciEntity);
		assertTrue(count == 1);
	}
	
	@Test
	@Transactional
	public void testDeleteCity(){
		CityEntity city = getNewCity();
		//delete the new city
		int count = mapper.delete(city.getID());
		assertTrue(count == 1);
	}
}
