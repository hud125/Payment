package com.aurfy.haze.service.impl.infra;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.core.model.infra.mer.MerchantStatusEnum;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.api.infrastructure.MerchantService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.infra.AddressBookBean;
import com.aurfy.haze.service.bean.infra.mer.MerchantBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;

/**
 * 测试类应该有bug，需要重新测试
 *
 */
public class MerchantServiceTest extends ServiceUnitTest {

	@Autowired
	private MerchantService merService;
	
	@Autowired
	private CRUDService crudService;

	public MerchantBean newMerchant() {
		MerchantBean mer = new MerchantBean();
		mer.setID(SecurityUtils.UUID());
		mer.setName("test");
		mer.setAbbreviation("CN");
		mer.setMerchantCode("000A");
		mer.setMerchantType("1");// glbpay中：正品1，仿品2，虚拟物品3
		mer.setFederalID("1");
		mer.setTaxID("1");
		mer.setTransactionURLs("1");
		mer.setIndustry("1");
		mer.setMcc("11");
		mer.setContactPerson("张三");
		mer.setContactPhone("15618387186");
		mer.setContactEmail("test@126.com");
		// 等addressBook的service层写好了再改过来
		AddressBookBean addrBookBean = new AddressBookBean();
		addrBookBean.setID(SecurityUtils.UUID());
		mer.setAddressBook(addrBookBean);
		mer.setEffectiveDate(new Date());
		mer.setTerminateDate(new Date());
		mer.setTimeZone(1.5f);
		mer.setTimeZoneName("CN");
		mer.setComments("no");
		mer.setOwnerId("1");
		mer.setCreateDate(new Date());
		mer.setUpdateDate(mer.getCreateDate());
		mer.setStatus(MerchantStatusEnum.ACTIVE);
		return mer;
	}

	public MerchantBean newMerchants(String id, String code) {
		MerchantBean mer = new MerchantBean();
		mer.setID(id);
		mer.setName("测试");
		mer.setAbbreviation("CN");
		mer.setMerchantCode("000A");
		mer.setMerchantType("1");// glbpay中：正品1，仿品2，虚拟物品3
		mer.setFederalID("1");
		mer.setTaxID("1");
		mer.setTransactionURLs("1");
		mer.setIndustry("1");
		mer.setMcc("11");
		mer.setContactPerson("张三");
		mer.setContactPhone("15618387186");
		mer.setContactEmail("test@126.com");
		mer.setEffectiveDate(new Date());
		mer.setTerminateDate(new Date());
		mer.setTimeZone(1.5f);
		mer.setTimeZoneName("CN");
		mer.setComments("no");
		mer.setOwnerId("1");
		mer.setCreateDate(new Date());
		mer.setUpdateDate(mer.getCreateDate());
		mer.setStatus(MerchantStatusEnum.ACTIVE);
		mer.setMerchantCode("21111111");
		AddressBookBean address = new AddressBookBean();
		address.setOwnerId("e15820e1-a99b-43e6-a5d1-9ad8ff95d2db");
		mer.setAddressBook(address);
		return mer;
	}

	public MerchantBean getNewMerchant() throws ServiceException {
		MerchantBean mer = newMerchant();
		String oldId = mer.getID();
		crudService.create(mer);
		String newId = mer.getID();
		assert (oldId.equals(newId));
		return mer;
	}

	@Test
	@Transactional
	public void testCreateMerchant() throws ServiceException {
		getNewMerchant();
	}
	
//	@Test
//	@Transactional
//	public void testRetrieveAll() throws RuntimeServiceException, ServiceException{
//		List<CRUDBean> mers = crudService.retrieveAll(MerchantBean.class);
//	}

}
