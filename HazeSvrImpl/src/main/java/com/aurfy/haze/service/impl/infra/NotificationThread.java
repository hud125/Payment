package com.aurfy.haze.service.impl.infra;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.core.model.http.HttpStatus;
import com.aurfy.haze.core.model.infra.DeliveryStatusEnum;
import com.aurfy.haze.core.model.system.ActionResultEnum;
import com.aurfy.haze.core.model.system.AuditActionEnum;
import com.aurfy.haze.core.model.system.SystemModuleEnum;
import com.aurfy.haze.service.api.CRUDService;
import com.aurfy.haze.service.bean.infra.AuditLogBean;
import com.aurfy.haze.service.bean.infra.NotificationBean;
import com.aurfy.haze.service.exceptions.ParameterValidationException;
import com.aurfy.haze.utils.IOUtils;
import com.aurfy.haze.utils.RegexUtils;

public class NotificationThread extends Thread {

	private static final Logger logger = LoggerFactory.getLogger(NotificationThread.class);

	private CRUDService crudService;
	private NotificationBean item;
	private CloseableHttpClient httpClient;
	private HttpContext context;

	public NotificationThread(CRUDService crudService, NotificationBean item, CloseableHttpClient httpClient) {
		super();
		this.crudService = crudService;
		this.item = item;
		this.httpClient = httpClient;
		this.context = HttpClientContext.create();
	}

	@Override
	public void run() {
		logger.debug("running notification item (id={})...", item.getID());
		try {
			checkStatus();
		} catch (ParameterValidationException e) {
			return;
		}
		try {
			sendPost();
		} finally {
			updateResult();
		}
		logSendFailure();
	}

	private void checkStatus() throws ParameterValidationException {
		if (!RegexUtils.isValidUrl(this.item.getTargetURL())) {
			logValidationError("targetURL", "invalid targetURL");
			throw new ParameterValidationException("targetURL");
		}
		if (!"POST".equalsIgnoreCase(this.item.getHttpMethod())) {
			logValidationError("httpMethod", "not supported method: " + this.item.getHttpMethod());
			throw new ParameterValidationException("httpMethod");
		}
		if (StringUtils.isBlank(this.item.getJsonMsg())) {
			logValidationError("jsonMsg", "empty json message");
			throw new ParameterValidationException("jsonMsg");
		}
		if (this.item.getRetryCounter() > this.item.getMaxCounter()) {
			logValidationError("retryCounter", "exceed max retry count");
			throw new ParameterValidationException("retryCounter");
		}
		if (!DeliveryStatusEnum.PROCESSING.equals(this.item.getDeliveryStatus())) {
			logValidationError("deliveryStatus", "must be processable notification");
			throw new ParameterValidationException("deliveryStatus");
		}
	}

	private void logValidationError(String parameter, String msg) {
		logError(AuditActionEnum.VALIDATION, parameter, msg);
	}

	private void logError(AuditActionEnum action, String parameter, String msg) {
		AuditLogBean log = new AuditLogBean();
		log.setModule(SystemModuleEnum.NOTIFICATION);
		log.setAction(action);
		log.setResult(ActionResultEnum.FAILURE);
		log.setParam1(msg);
		log.setParam2(parameter);

		try {
			crudService.create(log);
		} catch (Exception e) {
			// ignore
			logger.error("error creating AuditLog", e);
		}
	}

	private void logSendFailure() {
		if (this.item.getRetryCounter() >= this.item.getMaxCounter()) {
			logError(AuditActionEnum.SEND, null, "send merchant notification failed.");
		}
	}

	private void updateResult() {
		try {
			crudService.update(this.item);
		} catch (Exception e) {
			logger.error("error update NotificationBean");
		}
	}

	/**
	 * post形式发送
	 * 
	 * @param item
	 * @return
	 */
	private void sendPost() {
		HttpPost post = new HttpPost(item.getTargetURL());
		// 创建参数队列
		List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
		// FIXME: split json parameter
		formparams.add(new BasicNameValuePair("jsonMsg", item.getJsonMsg()));

		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
			post.setEntity(entity);
			CloseableHttpResponse response = null;

			try {
				response = httpClient.execute(post, this.context);
				int statuscode = response.getStatusLine().getStatusCode();

				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(response.getEntity());

				item.setHttpStatus(HttpStatus.parseByCode(statuscode + ""));
				if (HttpStatus.OK.equals(item.getHttpStatus())) {
					item.setDeliveryStatus(DeliveryStatusEnum.SUCCEED);
				} else {
					item.setDeliveryStatus(DeliveryStatusEnum.FAILED);
				}
			} finally {
				IOUtils.close(response);
			}
		} catch (Exception e) {
			item.setDeliveryStatus(DeliveryStatusEnum.FAILED);
			logger.error("error send post request", e);
		} finally {
			item.setRetryCounter(item.getRetryCounter() + 1);
			item.setUpdateDate(new Date());
		}
	}

}
