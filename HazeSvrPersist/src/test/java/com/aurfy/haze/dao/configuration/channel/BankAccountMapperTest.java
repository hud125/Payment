package com.aurfy.haze.dao.configuration.channel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.Currency;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.AddressBookMapper;
import com.aurfy.haze.dao.infrastructure.CountryMapperTest;
import com.aurfy.haze.entity.configuration.channel.BankAccountEntity;
import com.aurfy.haze.entity.infra.AddressBookEntity;
import com.aurfy.haze.entity.infra.CityEntity;
import com.aurfy.haze.entity.infra.CountryEntity;
import com.aurfy.haze.entity.infra.StateProvEntity;
import com.aurfy.haze.utils.SecurityUtils;

/**
 * 测试类应该有bug，需要重新测试
 *
 */
public class BankAccountMapperTest extends PersistUnitTest {

	@Autowired
	private BankAccountMapper mapper;

	@Autowired
	private AddressBookMapper addMapper;

	// @Autowired
	// private BankMapper accMapper;
	//
	// @Autowired
	// private MerchantMapper merMapper;

	public AddressBookEntity newAddressBookEntity() {

		CountryEntity coEntity = new CountryMapperTest().newCountryEntity();

		StateProvEntity spe = new StateProvEntity();
		spe.setID(SecurityUtils.UUID());
		spe.setCountry(coEntity);
		spe.setCreateDate(new Date());
		spe.setUpdateDate(spe.getCreateDate());
		spe.setName("上海市");

		CityEntity city = new CityEntity();
		city.setID(SecurityUtils.UUID());
		city.setName("徐汇区");
		city.setCountry(spe.getCountry());
		city.setState(spe);
		city.setCreateDate(new Date());
		city.setUpdateDate(city.getCreateDate());

		AddressBookEntity addr = new AddressBookEntity();
		addr.setID(SecurityUtils.UUID());
		addr.setCountry(city.getCountry());
		addr.setState(city.getState());
		addr.setCity(city);
		addr.setZipCode("200030");
		addr.setStreetName1("田林路");
		addr.setStreetName2("140号");
		addr.setString("不知");
		addr.setFullName("田林路140号");
		addr.setPhoneNumber("123456");
		addr.setOwnerId("e15820e1-a99b-43e6-a5d1-9ad8ff95d2db");
		addr.setCreateDate(new Date());
		addr.setUpdateDate(addr.getCreateDate());
		int count = addMapper.insert(addr);
		assertTrue(count == 1);
		return addr;
	}

	public BankAccountEntity newBankAccEntity() {
		AddressBookEntity addressBook = newAddressBookEntity();

		BankAccountEntity acc = new BankAccountEntity();

		String random = "Junit_";

		acc.setID(SecurityUtils.UUID());
		acc.setBankCode(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setBankAbbreviation(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setBankName(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setBankBranch(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setBankURL(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setBankId(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setAccountCurrency(Currency.CNY);
		acc.setAccountHolder("zj");
		acc.setAccountNumber("1234567891234567");
		acc.setAccountOpenDate(new Date());
		acc.setAddressBook(addressBook);
		acc.setComments(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setOwnerId(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setCreateDate(new Date());
		acc.setUpdateDate(acc.getCreateDate());

		return acc;
	}

	/**
	 * insert new acc in system.
	 * 
	 * @return
	 */
	public BankAccountEntity internalCreateEntity() {
		// create a new accAccount
		BankAccountEntity acc = newBankAccEntity();
		mapper.insert(acc);
		return acc;
	}

	@Test
	@Transactional
	public void testInsert() {
		internalCreateEntity();
	}

	@Test
	@Transactional
	public void testSelectOne() {
		BankAccountEntity acc = internalCreateEntity();
		BankAccountEntity newAcc = mapper.selectOne(acc.getID());
		acc = changeBankAcc(acc);
		newAcc = changeBankAcc(newAcc);
		assertTrue(acc.equals(newAcc));
	}

	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = mapper.countAll();
		int randomSize = RandomUtils.nextInt(1, 10);
		for (int i = 0; i < randomSize; i++) {
			internalCreateEntity();
		}

		int size = mapper.countAll();
		assertTrue(size == oldSize + randomSize);
	}

	@Test
	@Transactional
	public void testSelectAll() {
		int oldSize = mapper.countAll();
		int randomSize = RandomUtils.nextInt(1, 10);
		for (int i = 0; i < randomSize; i++) {
			internalCreateEntity();

		}
		List<BankAccountEntity> list = mapper.selectAll();

		assertNotNull(list);
		assertTrue((list.size() - oldSize) == randomSize);
	}

	@Test
	@Transactional
	public void testCountBy() {
		BankAccountEntity filterEntity = new BankAccountEntity();
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
		BankAccountEntity filterEntity = new BankAccountEntity();
		List<BankAccountEntity> list = mapper.selectBy(filterEntity, 0, 10);

		assertNotNull(list);
		assertTrue(list.size() == 10);
	}

	@Test
	@Transactional
	public void testUpdate() {

		AddressBookEntity addressBook = newAddressBookEntity();

		BankAccountEntity acc = internalCreateEntity();

		String random = "Update_";

		acc.setBankCode(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setBankAbbreviation(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setBankName(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setBankBranch(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setBankURL(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setBankId(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setAccountCurrency(Currency.CNY);
		acc.setAccountHolder("zj");
		acc.setAccountNumber("1234567891234567");
		acc.setAccountOpenDate(new Date());
		acc.setAddressBook(addressBook);
		acc.setComments(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setOwnerId(random + RandomStringUtils.randomAlphanumeric(10));
		acc.setUpdateDate(new Date());

		int count = mapper.update(acc);
		assertTrue(count == 1);
		BankAccountEntity newAcq = mapper.selectOne(acc.getID());
		acc = changeBankAcc(acc);
		newAcq = changeBankAcc(newAcq);
		assertTrue(acc.equals(newAcq));
	}

	@Test
	@Transactional
	public void testDelete() {
		BankAccountEntity acc = internalCreateEntity();

		int count = mapper.delete(acc.getID());
		assertTrue(count == 1);
		BankAccountEntity acc2 = mapper.selectOne(acc.getID());
		assertTrue(acc2 == null);
	}

	public BankAccountEntity changeBankAcc(BankAccountEntity newAcc) {
		CountryEntity country = new CountryEntity();
		country.setID(newAcc.getAddressBook().getCountry().getID());
		country.setName(newAcc.getAddressBook().getCountry().getName());
		country.setCountryCode(newAcc.getAddressBook().getCountry().getCountryCode());
		newAcc.getAddressBook().setCountry(country);
		StateProvEntity state = new StateProvEntity();
		state.setID(newAcc.getAddressBook().getState().getID());
		state.setName(newAcc.getAddressBook().getState().getName());
		state.setCountry(country);
		newAcc.getAddressBook().setState(state);
		CityEntity city = new CityEntity();
		city.setID(newAcc.getAddressBook().getCity().getID());
		city.setName(newAcc.getAddressBook().getCity().getName());
		city.setCountry(country);
		city.setState(state);
		newAcc.getAddressBook().setCity(city);
		return newAcc;
	}

}
