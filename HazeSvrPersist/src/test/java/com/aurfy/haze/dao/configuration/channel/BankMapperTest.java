package com.aurfy.haze.dao.configuration.channel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.configuration.CardOrgEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.configuration.channel.BankEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class BankMapperTest extends PersistUnitTest {

	@Autowired
	private BankMapper mapper;

	public static BankEntity newBankEntity() {
		BankEntity bank = new BankEntity();
		
		String random = "Junit_";
		
		bank.setID(SecurityUtils.UUID());
		bank.setBankCode(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setBankAbbreviation(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setBankName(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setBankBranch(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setBankURL(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setComments(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setOwnerId(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setCreateDate(new Date());
		bank.setUpdateDate(bank.getCreateDate());
		return bank;
	}

	/**
	 * insert new bank in system.
	 * 
	 * @return
	 */
	public BankEntity internalCreateEntity() {
		// create a new Bank
		BankEntity bank = newBankEntity();
		mapper.insert(bank);
		return bank;
	}

	@Test
	@Transactional
	public void testInsert() {
		internalCreateEntity();
	}

	@Test
	@Transactional
	public void testSelectOne() {
		BankEntity bank = internalCreateEntity();
		BankEntity newAcc = mapper.selectOne(bank.getID());
		assertTrue(bank.equals(newAcc));
	}

	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = mapper.countAll();
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();
		}

		int size = mapper.countAll();
		assertTrue(10 == (size - oldSize));
	}

	@Test
	@Transactional
	public void testSelectAll() {
		int oldSize = mapper.countAll();
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();

		}
		List<BankEntity> list = mapper.selectAll();

		assertNotNull(list);
		assertTrue((list.size() - oldSize) == 10);
	}

	@Test
	@Transactional
	public void testCountBy() {
		BankEntity filterEntity = new BankEntity();
		int oldSize = mapper.countBy(filterEntity);
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();
		}

		int size = mapper.countBy(filterEntity);
		assertTrue(10 == (size - oldSize));
	}

	@Test
	@Transactional
	public void testSelectBy() {
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();

		}
		BankEntity filterEntity = new BankEntity();
		List<BankEntity> list = mapper.selectBy(filterEntity, 0, 10);

		assertNotNull(list);
		assertTrue(list.size() == 10);
	}

	@Test
	@Transactional
	public void testUpdate() {
		BankEntity bank = internalCreateEntity();

		String random = "Update_";
		bank.setBankCode(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setBankAbbreviation(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setBankName(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setBankBranch(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setBankURL(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setComments(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setOwnerId(random + RandomStringUtils.randomAlphanumeric(10));
		bank.setUpdateDate(new Date());
		
		int count = mapper.update(bank);
		assertTrue(count == 1);
		BankEntity newAcq = mapper.selectOne(bank.getID());
		assertTrue(bank.equals(newAcq));
	}

	@Test
	@Transactional
	public void testDelete() {
		BankEntity bank = internalCreateEntity();

		int count = mapper.delete(bank.getID());
		assertTrue(count == 1);
		BankEntity bank2 = mapper.selectOne(bank.getID());
		assertTrue(bank2 == null);
	}
}
