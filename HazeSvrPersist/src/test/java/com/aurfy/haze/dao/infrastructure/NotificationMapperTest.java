package com.aurfy.haze.dao.infrastructure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.dao.infra.NotificationMapper;
import com.aurfy.haze.entity.infra.NotificationEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class NotificationMapperTest extends PersistUnitTest {

	@Autowired
	private NotificationMapper notificationMapper;

	public NotificationEntity getNotificationEntity() {
		NotificationEntity notificationEntity = new NotificationEntity();
		notificationEntity.setID(SecurityUtils.UUID());
		notificationEntity.setTargetURL("http://test-pgw.grapay.net:8005/HazePGW/notificationController/receive/");
		notificationEntity.setHttpMethod("Post");
		notificationEntity.setJsonMsg("{user:'User'}");
		notificationEntity.setMaxCounter(5);
		notificationEntity.setRetryCounter(0);
		notificationEntity.setDeliveryStatus(null);
		notificationEntity.setHttpStatus(null);
		notificationEntity.setCreateDate(new Date());
		notificationEntity.setUpdateDate(new Date());
		return notificationEntity;
	}

	@Test
	@Transactional
	public void testNotificationByDeliveryStatus() {
		NotificationEntity notificationEntity = getNotificationEntity();
		notificationEntity.setHttpStatus(null);
		int index = notificationMapper.insert(notificationEntity);
		assertTrue(index == 1);
		int size = notificationMapper.selectNotifiableItems().size();
		assertTrue(size > 0);
	}
	
	@Test
	@Transactional
	public void testCreate() {
		NotificationEntity notificationEntity = getNotificationEntity();
		int index = notificationMapper.insert(notificationEntity);
		assertTrue(index == 1);
	}

	@Test
	@Transactional
	public void testRetrieve() {
		NotificationEntity notificationEntity = getNotificationEntity();
		int index = notificationMapper.insert(notificationEntity);
		assertTrue(index == 1);
		NotificationEntity notificationEntity2 = notificationMapper.selectOne(notificationEntity.getID());
		assertTrue(notificationEntity.equals(notificationEntity2));
	}

	@Test
	@Transactional
	public void testRetrieveAll() {
		int oldSize = notificationMapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for (int i = 0; i < randomSize; i++) {
			notificationMapper.insert(getNotificationEntity());
		}
		assertTrue(oldSize + randomSize == notificationMapper.selectAll().size());
	}

	@Test
	@Transactional
	public void testCountBy() {
		NotificationEntity notificationEntity = getNotificationEntity();
		int index = notificationMapper.insert(notificationEntity);
		assertTrue(index == 1);
		int randomSize = RandomUtils.nextInt(1, 10);
		for (int i = 0; i < randomSize; i++) {
			notificationMapper.insert(getNotificationEntity());
		}
		assertNotNull(notificationMapper.countBy(notificationEntity));
	}

	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = notificationMapper.selectAll().size();
		int randomSize = RandomUtils.nextInt(1, 10);
		for (int i = 0; i < randomSize; i++) {
			notificationMapper.insert(getNotificationEntity());
		}
		assertTrue(oldSize + randomSize == notificationMapper.countAll());
	}

	@Test
	@Transactional
	public void testUpdate() {
		NotificationEntity notificationEntity = getNotificationEntity();
		int index = notificationMapper.insert(notificationEntity);
		assertTrue(index == 1);
		String targetURL = "http://test-pgw.grapay.net:8005/HazePGW/notificationController/receive/";
		notificationEntity.setTargetURL(targetURL);
		index = notificationMapper.update(notificationEntity);
		assertTrue(index == 1);
		notificationEntity = notificationMapper.selectOne(notificationEntity.getID());
		assertEquals(notificationEntity.getTargetURL(), targetURL);
	}

	@Test
	@Transactional
	public void testRemove() {
		NotificationEntity notificationEntity = getNotificationEntity();
		int index = notificationMapper.insert(notificationEntity);
		assertTrue(index == 1);
		index = notificationMapper.delete(notificationEntity.getID());
		assertTrue(index == 1);
	}

}
