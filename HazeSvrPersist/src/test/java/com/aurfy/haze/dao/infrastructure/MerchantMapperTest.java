package com.aurfy.haze.dao.infrastructure;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.infra.mer.MerchantStatusEnum;
import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.AddressBookMapper;
import com.aurfy.haze.dao.infra.CityMapper;
import com.aurfy.haze.dao.infra.CountryMapper;
import com.aurfy.haze.dao.infra.StateProvMapper;
import com.aurfy.haze.dao.infra.mer.MerchantMapper;
import com.aurfy.haze.entity.infra.AddressBookEntity;
import com.aurfy.haze.entity.infra.CityEntity;
import com.aurfy.haze.entity.infra.CountryEntity;
import com.aurfy.haze.entity.infra.StateProvEntity;
import com.aurfy.haze.entity.infra.mer.MerchantEntity;
import com.aurfy.haze.utils.SecurityUtils;

/**
 * 说明：merchant中只取addressbook，而不取country，state和city。 但是unittest中，必须先创建country，state和city，才能创建addressbook。
 * 所以在做原始类和生成结果的比较时，需要通过changeMerchant方法将原始类转换一下。
 */
public class MerchantMapperTest extends PersistUnitTest {

	@Autowired
	private AddressBookMapper addrMapper;

	@Autowired
	private CountryMapper coMapper;

	@Autowired
	private StateProvMapper stMapper;

	@Autowired
	private CityMapper ciMapper;

	@Autowired
	private MerchantMapper mapper;

	public CityEntity getCity() {
		// create a new country
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

		// create a new state
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

	public AddressBookEntity getAddressBook() {
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

		String oldId = addr.getID();
		int count = addrMapper.insert(addr);
		String newId = addr.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return addr;
	}

	public MerchantEntity newMerchant() {
		MerchantEntity mer = new MerchantEntity();
		mer.setID(SecurityUtils.UUID());
		mer.setName("京东");
		mer.setAbbreviation("CN");
		mer.setMerchantType("1");// glbpay中：正品1，仿品2，虚拟物品3
		mer.setFederalID("1");
		mer.setTaxID("1");
		mer.setTransactionURLs("1");
		mer.setIndustry("1");
		mer.setMcc("test");
		mer.setContactPerson("刘强东");
		mer.setContactPhone("15618387186");
		mer.setContactEmail("test@126.com");
		mer.setAddressBook(getAddressBook());
		mer.setEffectiveDate(new Date());
		mer.setTerminateDate(new Date());
		mer.setTimeZone(1.5f);
		mer.setTimeZoneName("CN");
		mer.setComments("ok");
		mer.setOwnerId("1");
		mer.setStatus(MerchantStatusEnum.ACTIVE);
		mer.setCreateDate(new Date());
		mer.setUpdateDate(mer.getCreateDate());
		mer.setMerchantCode("BCCCCC2");
		return mer;
	}

	public MerchantEntity getNewMerchant() {
		// create a new merchant
		MerchantEntity merEntity = newMerchant();
		String oldId = merEntity.getID();
		int count = mapper.insert(merEntity);
		String newId = merEntity.getID();
		assertTrue(oldId.equals(newId));
		assertTrue(count == 1);
		return merEntity;
	}

	@Test
	@Transactional
	public void testInsert() {
		getNewMerchant();
	}

	@Test
	@Transactional
	public void testSelectOne() {
		// get merchant by the given id
		MerchantEntity newMer = getNewMerchant();
		MerchantEntity newMer2 = mapper.selectOne(newMer.getID());
		assertNotNull(newMer2);
		newMer = changeMerchant(newMer);
		assertTrue(newMer2.equals(newMer));
	}

	@Test
	@Transactional
	public void testSelectBy() {
		MerchantEntity newMer = getNewMerchant();
		List<MerchantEntity> merEntitys = mapper.selectBy(newMer, 0, 10);
		assertNotNull(merEntitys);
		newMer = changeMerchant(newMer);
		assertTrue(merEntitys.contains(newMer));
		assertTrue(merEntitys.size() > 0);
	}

	@Test
	@Transactional
	public void testCountBy() {
		MerchantEntity newMer = newMerchant();
		mapper.insert(newMer);
		int num = mapper.countBy(newMer);
		assertTrue(num > 0);
	}

	@Test
	@Transactional
	public void testUpdateMerchant() {
		MerchantEntity newMer = getNewMerchant();
		MerchantEntity merEntity = new MerchantEntity();
		merEntity.setName("测试2");
		merEntity.setAbbreviation("US");
		merEntity.setMerchantType("2");// glbpay中：正品1，仿品2，虚拟物品3
		merEntity.setFederalID("2");
		merEntity.setTaxID("2");
		merEntity.setTransactionURLs("2");
		merEntity.setIndustry("2");
		merEntity.setContactPerson("张三2");
		merEntity.setContactPhone("123456789");
		merEntity.setContactEmail("test@qq.com");
		merEntity.setAddressBook(null);
		merEntity.setEffectiveDate(new Date());
		merEntity.setTerminateDate(new Date());
		merEntity.setTimeZone(8.9f);
		merEntity.setTimeZoneName("US");
		merEntity.setComments("yes");
		merEntity.setOwnerId("2");
		merEntity.setCreateDate(new Date());
		merEntity.setUpdateDate(merEntity.getCreateDate());
		merEntity.setID(newMer.getID());

		int count = mapper.update(merEntity);
		MerchantEntity mer = mapper.selectOne(merEntity.getID());
		assertTrue(count == 1);
		assertTrue(!merEntity.equals(newMer));
		assertTrue(!merEntity.equals(mer));
	}

	@Test
	@Transactional
	public void testDelete() {
		MerchantEntity newMer = getNewMerchant();
		// delete the new merchant
		int count = mapper.delete(newMer.getID());
		assertTrue(count == 1);
	}

	public MerchantEntity changeMerchant(MerchantEntity newMer) {
		CountryEntity country = new CountryEntity();
		country.setID(newMer.getAddressBook().getCountry().getID());
		country.setName(newMer.getAddressBook().getCountry().getName());
		country.setCountryCode(newMer.getAddressBook().getCountry().getCountryCode());
		newMer.getAddressBook().setCountry(country);
		StateProvEntity state = new StateProvEntity();
		state.setID(newMer.getAddressBook().getState().getID());
		state.setName(newMer.getAddressBook().getState().getName());
		state.setCountry(country);
		newMer.getAddressBook().setState(state);
		CityEntity city = new CityEntity();
		city.setID(newMer.getAddressBook().getCity().getID());
		city.setName(newMer.getAddressBook().getCity().getName());
		city.setCountry(country);
		city.setState(state);
		newMer.getAddressBook().setCity(city);
		return newMer;
	}

	public static MerchantEntity newMerchantWithNullAddr() {
		MerchantEntity mer = new MerchantEntity();
		mer.setID(SecurityUtils.UUID());
		mer.setName("京东");
		mer.setAbbreviation("CN");
		mer.setMerchantType("1");// glbpay中：正品1，仿品2，虚拟物品3
		mer.setFederalID("1");
		mer.setTaxID("1");
		mer.setTransactionURLs("1");
		mer.setIndustry("1");
		mer.setMcc("1");
		mer.setContactPerson("刘强东");
		mer.setContactPhone("15618387186");
		mer.setContactEmail("test@126.com");
		mer.setAddressBook(null);
		mer.setEffectiveDate(new Date());
		mer.setTerminateDate(new Date());
		mer.setTimeZone(1.5f);
		mer.setTimeZoneName("CN");
		mer.setComments("ok");
		mer.setOwnerId("1");
		mer.setStatus(MerchantStatusEnum.ACTIVE);
		mer.setCreateDate(new Date());
		mer.setUpdateDate(mer.getCreateDate());
		mer.setMerchantCode("B1111");
		return mer;
	}
}
