package com.aurfy.haze.dao.infrastructure;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.tools.VariableMapClassifierEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.VariableMapMapper;
import com.aurfy.haze.entity.infra.VariableMapEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class VariableMapMapperTest extends PersistUnitTest {

	@Autowired
	private VariableMapMapper mapper;

	public VariableMapEntity newVariableMapEntity() {
		VariableMapEntity var = new VariableMapEntity();
		var.setID(SecurityUtils.UUID());
		var.setClassifer(VariableMapClassifierEnum.MerSettleDeliveryDeadlineDay);
		var.setKeyName("lang");
		var.setMapValue("EN");
		var.setCreateDate(new Date());
		var.setUpdateDate(var.getCreateDate());
		return var;
	}

	public VariableMapEntity getNewVariableMap() {
		VariableMapEntity varEntity = newVariableMapEntity();
		String oldId = varEntity.getID();
		int count = mapper.insert(varEntity);
		String newId = varEntity.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return varEntity;
	}

	@Test
	@Transactional
	public void TestInsert() {
		getNewVariableMap();
	}

	@Test
	@Transactional
	public void testSelectCountry() {
		// get variableMap by the given id
		VariableMapEntity varEntity = getNewVariableMap();
		VariableMapEntity newVar = mapper.selectOne(varEntity.getID());
		assertNotNull(newVar);
		assertTrue(varEntity.getID().equals(newVar.getID()));
	}

	@Test
	@Transactional
	public void testSelectVariableMapByKeyName() {
		// get variableMap by the given keyName
		VariableMapEntity varEntity = getNewVariableMap();
		String keyName = varEntity.getKeyName();
		List<VariableMapEntity> varEntityList = mapper.selectVariableMapByKeyName(keyName);
		assertTrue(varEntityList.size() > 0);
	}

	@Test
	@Transactional
	public void testUpdateCountry() {
		VariableMapEntity var = getNewVariableMap();
		VariableMapEntity upVarEntity = new VariableMapEntity();
		upVarEntity.setClassifer(VariableMapClassifierEnum.MerSettleDeliveryDeadlineDay);
		upVarEntity.setKeyName("lang");
		upVarEntity.setMapValue("EN");
		upVarEntity.setCreateDate(new Date());
		upVarEntity.setUpdateDate(upVarEntity.getCreateDate());
		upVarEntity.setID(var.getID());

		int count = mapper.update(upVarEntity);
		VariableMapEntity NewVar = mapper.selectOne(upVarEntity.getID());
		assertTrue(count == 1);
		assertTrue(!upVarEntity.equals(var));
		assertTrue(upVarEntity.equals(NewVar));
	}

	@Test
	@Transactional
	public void testDeleteCountry() {
		VariableMapEntity varEntity = getNewVariableMap();
		// delete the new variableMap
		int count = mapper.delete(varEntity.getID());
		assertTrue(count == 1);
	}

}
