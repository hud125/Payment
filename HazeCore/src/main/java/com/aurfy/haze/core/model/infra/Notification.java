package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.model.TextualIDModel;
import com.aurfy.haze.core.model.http.HttpStatus;

public class Notification extends TextualIDModel {

	private String targetURL;
	private String httpMethod;
	private String jsonMsg;
	private Integer maxCounter;
	private Integer retryCounter;
	private DeliveryStatusEnum deliveryStatus;
	private HttpStatus httpStatus;

	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getJsonMsg() {
		return jsonMsg;
	}

	public void setJsonMsg(String jsonMsg) {
		this.jsonMsg = jsonMsg;
	}

	public Integer getMaxCounter() {
		return maxCounter;
	}

	public void setMaxCounter(Integer maxCounter) {
		this.maxCounter = maxCounter;
	}

	public Integer getRetryCounter() {
		return retryCounter;
	}

	public void setRetryCounter(Integer retryCounter) {
		this.retryCounter = retryCounter;
	}

	public DeliveryStatusEnum getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(DeliveryStatusEnum deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((deliveryStatus == null) ? 0 : deliveryStatus.hashCode());
		result = prime * result + ((httpMethod == null) ? 0 : httpMethod.hashCode());
		result = prime * result + ((httpStatus == null) ? 0 : httpStatus.hashCode());
		result = prime * result + ((jsonMsg == null) ? 0 : jsonMsg.hashCode());
		result = prime * result + ((maxCounter == null) ? 0 : maxCounter.hashCode());
		result = prime * result + ((retryCounter == null) ? 0 : retryCounter.hashCode());
		result = prime * result + ((targetURL == null) ? 0 : targetURL.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		if (deliveryStatus != other.deliveryStatus)
			return false;
		if (httpMethod == null) {
			if (other.httpMethod != null)
				return false;
		} else if (!httpMethod.equals(other.httpMethod))
			return false;
		if (httpStatus == null) {
			if (other.httpStatus != null)
				return false;
		} else if (!httpStatus.equals(other.httpStatus))
			return false;
		if (jsonMsg == null) {
			if (other.jsonMsg != null)
				return false;
		} else if (!jsonMsg.equals(other.jsonMsg))
			return false;
		if (maxCounter == null) {
			if (other.maxCounter != null)
				return false;
		} else if (!maxCounter.equals(other.maxCounter))
			return false;
		if (retryCounter == null) {
			if (other.retryCounter != null)
				return false;
		} else if (!retryCounter.equals(other.retryCounter))
			return false;
		if (targetURL == null) {
			if (other.targetURL != null)
				return false;
		} else if (!targetURL.equals(other.targetURL))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notification [targetURL=");
		builder.append(targetURL);
		builder.append(", httpMethod=");
		builder.append(httpMethod);
		builder.append(", jsonMsg=");
		builder.append(jsonMsg);
		builder.append(", maxCounter=");
		builder.append(maxCounter);
		builder.append(", retryCounter=");
		builder.append(retryCounter);
		builder.append(", deliveryStatus=");
		builder.append(deliveryStatus);
		builder.append(", httpStatus=");
		builder.append(httpStatus);
		builder.append(", getID()=");
		builder.append(getID());
		builder.append(", getCreateDate()=");
		builder.append(getCreateDate());
		builder.append(", getUpdateDate()=");
		builder.append(getUpdateDate());
		builder.append("]");
		return builder.toString();
	}

}
