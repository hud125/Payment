package com.aurfy.haze.core.model.infra;

import com.aurfy.haze.core.model.TextualIDModel;

import java.util.Date;

public class ScheduleBatch extends TextualIDModel {

	private String jobId;
	private Date scheduleDate;
	private String logs;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("scheduleBatch [jobId=");
		builder.append(jobId);
		builder.append(", scheduleDate=");
		builder.append(scheduleDate);
		builder.append(", logs=");
		builder.append(logs);
		builder.append(", getID()=");
		builder.append(getID());
		builder.append(", getCreateDate()=");
		builder.append(getCreateDate());
		builder.append(", getUpdateDate()=");
		builder.append(getUpdateDate());
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result + ((logs == null) ? 0 : logs.hashCode());
		result = prime * result + ((scheduleDate == null) ? 0 : scheduleDate.hashCode());
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
		ScheduleBatch other = (ScheduleBatch) obj;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (logs == null) {
			if (other.logs != null)
				return false;
		} else if (!logs.equals(other.logs))
			return false;
		if (scheduleDate == null) {
			if (other.scheduleDate != null)
				return false;
		} else if (!scheduleDate.equals(other.scheduleDate))
			return false;
		return true;
	}

}
