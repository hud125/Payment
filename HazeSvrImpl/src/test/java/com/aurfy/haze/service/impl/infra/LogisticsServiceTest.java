package com.aurfy.haze.service.impl.infra;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.CRUDBean;
import com.aurfy.haze.service.bean.infra.LogisticsBean;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.SecurityUtils;

public class LogisticsServiceTest extends ServiceUnitTest {

	@Autowired
	private CRUDService crudService;
	
	private LogisticsBean getLogisticsBean() {
		LogisticsBean logisticsBean = new LogisticsBean();
		logisticsBean.setID(SecurityUtils.UUID());
		logisticsBean.setName("jUnitName_"+RandomStringUtils.random(1));
		logisticsBean.setAbbreviation("jUnitAbbr_"+RandomStringUtils.random(1));
		logisticsBean.setComments("jUnitComments_"+RandomStringUtils.random(1));
		logisticsBean.setInquiryHandler("jUnitIH_"+RandomStringUtils.random(1));
		logisticsBean.setUrl("jUnitURL_"+RandomStringUtils.random(1));
		logisticsBean.setCreateDate(new Date());
		logisticsBean.setUpdateDate(new Date());
		return logisticsBean;
	}
	private LogisticsBean create(LogisticsBean logisticsBean) {
		try {
			return (LogisticsBean) crudService.create(logisticsBean);
		} catch (ServiceException e) {
			fail(e.getMessage());
			return null;
		}
	}
	@Test
	@Transactional
	public void testCreate() {
		LogisticsBean logisticsBean = getLogisticsBean();
		logisticsBean.setID("");
		logisticsBean.setUpdateDate(null);
		logisticsBean.setCreateDate(null);
		logisticsBean = create(logisticsBean);
		Assert.assertNotNull(logisticsBean.getID());
		Assert.assertNotNull(logisticsBean.getUpdateDate());
		Assert.assertNotNull(logisticsBean.getCreateDate());
		logisticsBean = getLogisticsBean();
		Assert.assertNotNull(create(logisticsBean));
	}
	@Test
	@Transactional
	public void testDelete() {
		LogisticsBean logisticsBean = getLogisticsBean();
		logisticsBean = create(logisticsBean);
		Assert.assertNotNull(logisticsBean);
		try {
			Assert.assertTrue(crudService.delete(LogisticsBean.class, logisticsBean.getID()));
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}
	@Test
	@Transactional
	public void testRetrieveOne() {
		LogisticsBean logisticsBean = getLogisticsBean();
		logisticsBean = create(logisticsBean);
		Assert.assertNotNull(logisticsBean);
		try {
			Assert.assertNotNull(crudService.retrieve(LogisticsBean.class, logisticsBean.getID()));
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}
	@Test
	@Transactional
	public void testRetrieveWithPagination() {
		try {
			List<CRUDBean> oldList = crudService.retrieveAll(LogisticsBean.class);
			int oldSize = oldList.size();
			int randomSize = RandomUtils.nextInt(1, 10);
			for(int i = 0;i<randomSize;i++) {
				create(getLogisticsBean());
			}
			Assert.assertNotNull(crudService.retrieve(LogisticsBean.class, 1, 10 + oldSize));
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}
	@Test
	@Transactional
	public void testRetieveAll() {
		try {
			List<CRUDBean> oldList = crudService.retrieveAll(LogisticsBean.class);
			int oldSize = oldList.size();
			int randomSize = RandomUtils.nextInt(1, 10);
			for(int i = 0;i<randomSize;i++) {
				create(getLogisticsBean());
			}
			if(oldSize != 0) {
				assertEquals(randomSize + oldSize, crudService.retrieveAll(LogisticsBean.class));
			} else {
				assertEquals(randomSize, crudService.retrieveAll(LogisticsBean.class).size());
			}
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}
	@Test
	@Transactional
	public void testUpdate() {
		LogisticsBean logisticsBean = getLogisticsBean();
		logisticsBean = create(logisticsBean);
		Assert.assertNotNull(logisticsBean);
		String index = "jUnit_uzi";
		logisticsBean.setComments(index);
		try {
			assertNotNull(crudService.update(logisticsBean));
			LogisticsBean logisticsBean2 = (LogisticsBean) crudService.retrieve(LogisticsBean.class, logisticsBean.getID());
			assertEquals(index, logisticsBean2.getComments());
		} catch (ServiceException e) {
			fail(e.getMessage());
		}
	}
}
