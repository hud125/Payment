package com.aurfy.haze.dao.infrastructure;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.CountryMapper;
import com.aurfy.haze.dao.infra.StateProvMapper;
import com.aurfy.haze.entity.infra.CountryEntity;
import com.aurfy.haze.entity.infra.StateProvEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class StateProvMapperTest extends PersistUnitTest {

	@Autowired
	private StateProvMapper mapper;
	
	@Autowired
	private CountryMapper country_mapper;
	
//	@Autowired
//	private DataSourceTransactionManager dtManager;
	
	/**
	 * create new state of java obj.
	 * if no country in system, create one.
	 * @return
	 */
	public StateProvEntity newStateProvEntity(){
		//first new a country
		CountryEntity co = new CountryMapperTest().newCountryEntity();
		String oldId = co.getID();
		int count = country_mapper.insert(co);
		String newId = co.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		
		//then new a state
		StateProvEntity spe = new StateProvEntity();
		spe.setID(SecurityUtils.UUID());
		spe.setCountry(co);
		spe.setCreateDate(new Date());
		spe.setUpdateDate(spe.getCreateDate());
		spe.setName("上海市");
		return spe;
	}
	
	/**
	 * insert new state in system.
	 * @return
	 */
	public StateProvEntity getNewState(){
		//create a new state
		StateProvEntity stateProv = newStateProvEntity();
		String oldId = stateProv.getID();
		int count = mapper.insert(stateProv);
		String newId = stateProv.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		
		return stateProv;
	}
	
	@Test
	@Transactional
	public void testInsert(){
		//create a new state
		getNewState();
	}
	
	@Test
	@Transactional
	public void testSelect() {
		//get state associated with the given id
		StateProvEntity spe = getNewState();
		StateProvEntity stateProvEntity = mapper.selectOne(spe.getID());
		assertNotNull(stateProvEntity);
		assertTrue(stateProvEntity.getID().equals(spe.getID()));
	}
	
	@Test
	@Transactional
	public void testSelectAll(){
		getNewState();
		List<StateProvEntity> spes = mapper.selectAll();
		assertNotNull(spes);
	}
	
	@Test
	@Transactional
	public void testUpdate(){
		//get max state_id
		StateProvEntity oldspe = getNewState();
		StateProvEntity newspe = new StateProvEntity();
		newspe.setName("SH");
		newspe.setUpdateDate(new Date());
		newspe.setID(oldspe.getID());
		int count = mapper.update(newspe);
		assertTrue(count == 1);
	}
	
	@Test
	@Transactional
	public void testDelete(){
		//get max state_id
		StateProvEntity spe = getNewState();
		//delete the new state
		int count = mapper.delete(spe.getID());
		assertTrue(count == 1);
	}
	
	@Test
	@Transactional
	public void testListStateProvsByCountry(){
		StateProvEntity sp = getNewState();
		List<StateProvEntity> splist = mapper.listStateProvsByCountry(sp.getID());
		assertNotNull(splist);
	}
}
