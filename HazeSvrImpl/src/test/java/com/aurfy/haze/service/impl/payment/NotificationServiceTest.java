package com.aurfy.haze.service.impl.payment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aurfy.haze.core.model.http.HttpStatus;
import com.aurfy.haze.core.model.infra.DeliveryStatusEnum;
import com.aurfy.haze.dao.infra.NotificationMapper;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.infra.NotificationBean;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.ServiceUnitTest;
import com.aurfy.haze.utils.DateUtils;

public class NotificationServiceTest extends ServiceUnitTest {

	private static final long waitTimeout = 20000L;
	private static final Date DEFAULT_DATE = DateUtils.parseDateConcrete("2015-01-01 00:00:00.000");

	@Autowired
	private CRUDService crudService;

	@Autowired
	private NotificationMapper notificationMapper;

	@BeforeClass
	public static void setUpBeforeClass() {
		NotifyDaemonThread.getInstance().start();
	}

	@AfterClass
	public static void tearDownAfterClass() {
		NotifyDaemonThread.getInstance().shutdown();
	}

	@Before
	public void setUp() {
		NotifyDaemonThread.getInstance().setCrudService(crudService);
		NotifyDaemonThread.getInstance().setNotificationMapper(notificationMapper);
	}

	/**
	 * must not mark this test as @Transactional, since notification thread can not read data in this thread
	 */
	@Test
	public void testSendNotification() {
		
		// 1. create sample data
		List<NotificationBean> notifyBeans = createNotifyBeans();
		try {
			int size = notifyBeans.size();
			for (int i = 0; i < size; i++) {
				try {
					crudService.create(notifyBeans.get(i));
				} catch (Exception e) {
					crudService.update(notifyBeans.get(i));
				}
			}

			// 2. wait for a while for the NotifyDaemonThread to scan
			long startTime = System.currentTimeMillis();
			long endTime;
			do {
				endTime = System.currentTimeMillis();
				System.err.println("junit thread sleeping");
				Thread.sleep(1000);
			} while (NotifyDaemonThread.getInstance().isRunning() && (endTime - startTime) <= waitTimeout);

			// 3. check result
			NotificationBean result;

			result = (NotificationBean) crudService.retrieve(NotificationBean.class, "JUnit-notify-1");
			assertEquals(DeliveryStatusEnum.SUCCEED, result.getDeliveryStatus());
			assertEquals(HttpStatus.OK, result.getHttpStatus());
			assertTrue(result.getUpdateDate().after(DEFAULT_DATE));

			result = (NotificationBean) crudService.retrieve(NotificationBean.class, "JUnit-notify-2");
			assertEquals(DeliveryStatusEnum.SUCCEED, result.getDeliveryStatus());
			assertEquals(HttpStatus.OK, result.getHttpStatus());
			assertTrue(result.getUpdateDate().after(DEFAULT_DATE));

			result = (NotificationBean) crudService.retrieve(NotificationBean.class, "JUnit-not-notify-3");
			assertEquals(DeliveryStatusEnum.PROCESSING, result.getDeliveryStatus());
			assertEquals(null, result.getHttpStatus());
			assertEquals(DEFAULT_DATE, result.getUpdateDate());

			result = (NotificationBean) crudService.retrieve(NotificationBean.class, "JUnit-not-notify-4");
			assertEquals(DeliveryStatusEnum.SUCCEED, result.getDeliveryStatus());
			assertEquals(HttpStatus.OK, result.getHttpStatus());
			assertEquals(DEFAULT_DATE, result.getUpdateDate());

			result = (NotificationBean) crudService.retrieve(NotificationBean.class, "JUnit-not-notify-5");
			assertEquals(DeliveryStatusEnum.FAILED, result.getDeliveryStatus());
			assertEquals(HttpStatus.NOT_FOUND, result.getHttpStatus());
			assertEquals(DEFAULT_DATE, result.getUpdateDate());

		} catch (RuntimeServiceException | ServiceException | InterruptedException e) {
			fail(e.getMessage());
		}
	}

	private List<NotificationBean> createNotifyBeans() {
		List<NotificationBean> lists = new ArrayList<NotificationBean>();
		lists.add(createNotifyBean("JUnit-notify-1", 0, null, null));// notify
		lists.add(createNotifyBean("JUnit-notify-2", 1, DeliveryStatusEnum.FAILED, HttpStatus.BAD_REQUEST));// notify
		lists.add(createNotifyBean("JUnit-not-notify-3", 2, DeliveryStatusEnum.PROCESSING, null));// not notify
		lists.add(createNotifyBean("JUnit-not-notify-4", 3, DeliveryStatusEnum.SUCCEED, HttpStatus.OK));// not notify
		lists.add(createNotifyBean("JUnit-not-notify-5", 5, DeliveryStatusEnum.FAILED, HttpStatus.NOT_FOUND));// not
		return lists;
	}

	public NotificationBean createNotifyBean(String id, int retryCount, DeliveryStatusEnum deliveryStatus,
			HttpStatus httpStatus) {
		NotificationBean bean = new NotificationBean();
		bean.setID(id);
		bean.setTargetURL("http://test-pgw.grapay.net:8005/HazePGW/notificationController/receive/");
		bean.setHttpMethod("POST");
		bean.setJsonMsg("{user:'User'}");
		bean.setMaxCounter(5);
		bean.setRetryCounter(retryCount);
		bean.setDeliveryStatus(deliveryStatus);
		bean.setHttpStatus(httpStatus);
		bean.setCreateDate(DEFAULT_DATE);
		bean.setUpdateDate(DEFAULT_DATE);
		return bean;
	}
}
