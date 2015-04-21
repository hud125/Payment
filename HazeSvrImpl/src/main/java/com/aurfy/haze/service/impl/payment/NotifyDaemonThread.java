package com.aurfy.haze.service.impl.payment;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.aurfy.haze.core.model.infra.DeliveryStatusEnum;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.dao.infra.NotificationMapper;
import com.aurfy.haze.dao.spring.SpringContext;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.infra.NotificationBean;
import com.aurfy.haze.service.conf.ServiceImplConstant.AOP_NAME;
import com.aurfy.haze.service.exceptions.RuntimeServiceException;
import com.aurfy.haze.service.exceptions.ServiceException;
import com.aurfy.haze.service.impl.helper.BeanBuilder;
import com.aurfy.haze.service.impl.infra.NotificationThread;
import com.aurfy.haze.service.impl.thread.LoopingThread;
import com.aurfy.haze.service.impl.thread.ThreadPoolProvider;
import com.aurfy.haze.service.impl.tools.DefaultHttpClientBuilder;
import com.aurfy.haze.service.impl.tools.HttpClientBuilder;

/**
 * merchant pay result notification thread.
 * 
 * @author hermano
 *
 */
public class NotifyDaemonThread extends LoopingThread {

	private static final Logger logger = LoggerFactory.getLogger(NotifyDaemonThread.class);
	private static NotifyDaemonThread instance;
	private static String threadPoolName = "merNotify";

	private ThreadPoolTaskExecutor threadPool;
	private HttpClientBuilder httpClientBuilder;
	private CloseableHttpClient httpClient;
	private NotificationMapper notificationMapper;
	private CRUDService crudService;

	static {
		instance = new NotifyDaemonThread();
	}

	public static NotifyDaemonThread getInstance() {
		return instance;
	}

	private NotifyDaemonThread() {
		super(false);
		this.threadPool = ThreadPoolProvider.getThreadPool(threadPoolName);
		this.httpClientBuilder = new DefaultHttpClientBuilder();
		this.httpClient = this.httpClientBuilder.createHttpClient();

		// note: spring beans might be empty due to lazy delay or thread priority
		// hence explicit call to setNotificationMapper() and setCrudService() might be needed
		try {
			this.notificationMapper = (NotificationMapper) SpringContext.getBean(AOP_MAPPER.NOTIFICATION_MAPPER);
		} catch (BeansException e) {
			// ignore
		}
		try {
			this.crudService = (CRUDService) SpringContext.getBean(AOP_NAME.CRUD_SERVICE);
		} catch (BeansException e) {
			// ignore
		}
	}

	// for run in junit environment when spring context is not ready
	public void setNotificationMapper(NotificationMapper notificationMapper) {
		this.notificationMapper = notificationMapper;
	}

	// for run in junit environment when spring context is not ready
	public void setCrudService(CRUDService crudService) {
		this.crudService = crudService;
	}

	@Override
	public void iterate() throws Exception {

		List<NotificationBean> beans = BeanBuilder.toNotificatioinBean(notificationMapper.selectNotifiableItems());

		if (CollectionUtils.isNotEmpty(beans)) {

			logger.info("notifying {} items", beans.size());

			for (NotificationBean bean : beans) {
				try {
					lockStatus(bean);
				} catch (Exception e) {
					continue;
				}
				threadPool.execute(new NotificationThread(crudService, bean, this.httpClient));
			}
		} else {
			logger.info("empty notification queue, skip.");
		}
	}

	/**
	 * update the notification status to PROCESSING to avoid duplicate process
	 */
	private void lockStatus(NotificationBean bean) throws RuntimeServiceException, ServiceException {
		bean.setDeliveryStatus(DeliveryStatusEnum.PROCESSING);
		bean.setHttpStatus(null);
		bean.setUpdateDate(new Date());
		try {
			this.crudService.update(bean);
		} catch (RuntimeServiceException | ServiceException e) {
			logger.error("error locking notification item status", e);
			throw e;
		}
	}

	@Override
	public long getSleepDuration() {
		return 200L;
	}

	@Override
	public synchronized void shutdown() {

		// must stop looping first
		super.shutdown();

		// wait for thread to finish
		try {
			this.threadPool.shutdown();
		} catch (Throwable t) {
			// ignore
		}

		this.httpClientBuilder.closeHttpConnManager(false);

		try {
			this.httpClient.close();
		} catch (IOException e) {
			// ignore
			logger.warn("error closing http client.");
		}
	}

}
