package com.aurfy.haze.service.impl.converter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.aurfy.haze.entity.infra.AddressBookEntity;
import com.aurfy.haze.entity.infra.CountryEntity;
import com.aurfy.haze.service.bean.infra.AddressBookBean;
import com.aurfy.haze.service.bean.infra.CountryBean;
import com.aurfy.haze.utils.SecurityUtils;

public class DefaultConverterTest {

	@Test
	public void testEntity2Bean() {
		AddressBookEntity entity = newAddressBookEntity();
		AddressBookBean bean = (AddressBookBean) new DefaultConverter().entity2Bean(AddressBookBean.class, entity);
		assertNotNull(bean);
		assertTrue(bean.getCountry() instanceof CountryBean);
		assertNull(bean.getCity());
	}

	private static AddressBookEntity newAddressBookEntity() {

		AddressBookEntity addr = new AddressBookEntity();
		addr.setID(SecurityUtils.UUID());
		addr.setCountry(newCountryEntity());
		addr.setState(null);
		addr.setCity(null);
		addr.setZipCode("200030");
		addr.setStreetName1("田林路");
		addr.setStreetName2("140号");
		addr.setString("不知");
		addr.setFullName("田林路140号");
		addr.setPhoneNumber("123456");
		addr.setOwnerId(SecurityUtils.UUID());
		addr.setCreateDate(new Date());
		addr.setUpdateDate(addr.getCreateDate());
		return addr;
	}

	private static CountryEntity newCountryEntity() {
		CountryEntity country = new CountryEntity();
		country.setID(SecurityUtils.UUID());
		country.setName(RandomStringUtils.randomAlphanumeric(10));
		country.setAbbreviation(RandomStringUtils.randomAlphanumeric(5));
		country.setCountryCode(RandomStringUtils.randomNumeric(3));
		country.setCreateDate(new Date());
		country.setUpdateDate(country.getCreateDate());
		return country;
	}

	@Test
	public void testBean2Entity() {
		AddressBookBean bean = newAddressBookBean();
		AddressBookEntity entity = (AddressBookEntity) new DefaultConverter()
				.bean2Entity(AddressBookEntity.class, bean);
		assertNotNull(entity);
		assertTrue(entity.getCountry() instanceof CountryEntity);
		assertNull(entity.getCity());
	}

	private static AddressBookBean newAddressBookBean() {
	
		AddressBookBean addr = new AddressBookBean();
		addr.setID(SecurityUtils.UUID());
		addr.setCountry(newCountryBean());
		addr.setState(null);
		addr.setCity(null);
		addr.setZipCode("200030");
		addr.setStreetName1("田林路");
		addr.setStreetName2("140号");
		addr.setString("不知");
		addr.setFullName("田林路140号");
		addr.setPhoneNumber("123456");
		addr.setOwnerId(SecurityUtils.UUID());
		addr.setCreateDate(new Date());
		addr.setUpdateDate(addr.getCreateDate());
		return addr;
	}

	private static CountryBean newCountryBean() {
		CountryBean country = new CountryBean();
		country.setID(SecurityUtils.UUID());
		country.setName(RandomStringUtils.randomAlphanumeric(10));
		country.setAbbreviation(RandomStringUtils.randomAlphanumeric(5));
		country.setCountryCode(RandomStringUtils.randomNumeric(3));
		country.setCreateDate(new Date());
		country.setUpdateDate(country.getCreateDate());
		return country;
	}

}
