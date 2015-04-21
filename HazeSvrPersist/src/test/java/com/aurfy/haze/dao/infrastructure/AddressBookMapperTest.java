package com.aurfy.haze.dao.infrastructure;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.AddressBookMapper;
import com.aurfy.haze.dao.infra.CityMapper;
import com.aurfy.haze.dao.infra.CountryMapper;
import com.aurfy.haze.dao.infra.StateProvMapper;
import com.aurfy.haze.entity.infra.AddressBookEntity;
import com.aurfy.haze.entity.infra.CityEntity;
import com.aurfy.haze.entity.infra.CountryEntity;
import com.aurfy.haze.entity.infra.StateProvEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class AddressBookMapperTest extends PersistUnitTest {

	@Autowired
	private AddressBookMapper mapper;
	
	@Autowired
	private CountryMapper coMapper;
	
	@Autowired
	private StateProvMapper stMapper;
	
	@Autowired
	private CityMapper ciMapper;
	
	public CityEntity getCity(){
		//create a new country
		CountryEntity coEntity = new CountryMapperTest().newCountryEntity();
		String oldId = coEntity.getID();
		int count = coMapper.insert(coEntity);
		String newId = coEntity.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		
		StateProvEntity spe = new StateProvEntity();
		spe.setID(SecurityUtils.UUID());
		spe.setCountry(coEntity);
		spe.setCreateDate(new Date());
		spe.setUpdateDate(spe.getCreateDate());
		spe.setName("上海市");
		
		//create a new state
		oldId = spe.getID();
		count = stMapper.insert(spe);
		newId = spe.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		
		CityEntity city = new CityEntity();
		city.setID(SecurityUtils.UUID());
		city.setName("徐汇区");
		city.setCountry(spe.getCountry());
		city.setState(spe);
		city.setCreateDate(new Date());
		city.setUpdateDate(city.getCreateDate());
		oldId = city.getID();
		count = ciMapper.insert(city);
		newId = city.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return city;
	}
	
	
	public AddressBookEntity newAddressBook(){
		CityEntity ci_entity = getCity();
		
		AddressBookEntity addr = new AddressBookEntity();
		addr.setID(SecurityUtils.UUID());
		addr.setCountry(ci_entity.getCountry());
		addr.setState(ci_entity.getState());
		addr.setCity(ci_entity);
		addr.setZipCode("200030");
		addr.setStreetName1("田林路");
		addr.setStreetName2("140号");
		addr.setString("不知");
		addr.setFullName("田林路140号");
		addr.setPhoneNumber("123456");
		addr.setOwnerId("e15820e1-a99b-43e6-a5d1-9ad8ff95d2db");
		addr.setCreateDate(new Date());
		addr.setUpdateDate(addr.getCreateDate());
		return addr;
	}
	
	public AddressBookEntity getNewAddr(){
		AddressBookEntity addr = newAddressBook();
		String oldId = addr.getID();
		int count = mapper.insert(addr);
		String newId = addr.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return addr;
	}
	
	@Test
	@Transactional
	public void testInsert(){
		getNewAddr();
	}
	
	@Test
	@Transactional
	public void testSelectOne() {
		//get country associated with the given id
		AddressBookEntity addr = getNewAddr();
		AddressBookEntity addr2 = mapper.selectOne(addr.getID());
		assertNotNull(addr2);
		assertTrue(addr.getID().equals(addr2.getID()));
	}
	
	@Test
	@Transactional
	public void testUpdate(){
		AddressBookEntity addr = getNewAddr();
		AddressBookEntity upAddr = new AddressBookEntity();
		upAddr.setUpdateDate(new Date());
		upAddr.setID(addr.getID());
		int count = mapper.update(upAddr);
		assertTrue(count == 1);
	}
	
	@Test
	@Transactional
	public void testDelete() {
		AddressBookEntity addr = getNewAddr();
		// delete the new addressBook
		int count = mapper.delete(addr.getID());
		assertTrue(count == 1);
	}
}
