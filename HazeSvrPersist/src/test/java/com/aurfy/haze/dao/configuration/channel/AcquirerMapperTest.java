package com.aurfy.haze.dao.configuration.channel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.mer.MerchantMapper;
import com.aurfy.haze.entity.configuration.channel.AcquirerEntity;
import com.aurfy.haze.utils.SecurityUtils;

/**
 * 测试类应该有bug，需要重新测试
 *
 */
public class AcquirerMapperTest extends PersistUnitTest {

	@Autowired
	private AcquirerMapper mapper;

	@Autowired
	private BankMapper bankMapper;

	@Autowired
	private BankAccountMapper accMapper;

	@Autowired
	private MerchantMapper merMapper;

	public AcquirerEntity newAcquirerEntity() {
		AcquirerEntity acq = new AcquirerEntity();

		String random = "Junit_";
		acq.setID(SecurityUtils.UUID());
		acq.setAcquirerName(random + RandomStringUtils.randomAlphanumeric(10));
		acq.setComments(random + RandomStringUtils.randomAlphanumeric(10));
		acq.setOwnerId(SecurityUtils.UUID());
		acq.setCreateDate(new Date());
		acq.setUpdateDate(new Date());
		
		return acq;
	}

	public AcquirerEntity internalCreateEntity() {
		AcquirerEntity acq = newAcquirerEntity();
		mapper.insert(acq);
		return acq;
	}

	@Test
	@Transactional
	public void testInsert() {
		internalCreateEntity();
	}

	@Test
	@Transactional
	public void testSelectOne() {
		AcquirerEntity acq = internalCreateEntity();
		AcquirerEntity newAcc = mapper.selectOne(acq.getID());
		assertTrue(acq.equals(newAcc));
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
		List<AcquirerEntity> list = mapper.selectAll();

		assertNotNull(list);
		assertTrue((list.size() - oldSize) == 10);
	}

	@Test
	@Transactional
	public void testCountBy() {
		AcquirerEntity filterEntity = new AcquirerEntity();
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
		AcquirerEntity filterEntity = new AcquirerEntity();
		List<AcquirerEntity> list = mapper.selectBy(filterEntity, 0, 10);

		assertNotNull(list);
		assertTrue(list.size() == 10);
	}

	@Test
	@Transactional
	public void testUpdate() {
		AcquirerEntity acq = internalCreateEntity();

		String random = "Update_";
		acq.setAcquirerName(random + RandomStringUtils.randomAlphanumeric(10));
		acq.setComments(random + RandomStringUtils.randomAlphanumeric(10));
		acq.setOwnerId(random + RandomStringUtils.randomAlphanumeric(10));
		acq.setUpdateDate(new Date());
		
		int count = mapper.update(acq);
		assertTrue(count == 1);
		AcquirerEntity newAcq = mapper.selectOne(acq.getID());
		assertTrue(acq.equals(newAcq));
	}

	@Test
	@Transactional
	public void testDelete() {
		AcquirerEntity acq = internalCreateEntity();
		int count = mapper.delete(acq.getID());
		assertTrue(count == 1);
		AcquirerEntity acq2 = mapper.selectOne(acq.getID());
		assertTrue(acq2 == null);
	}

}
