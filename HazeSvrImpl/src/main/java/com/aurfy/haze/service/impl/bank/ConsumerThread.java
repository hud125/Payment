package com.aurfy.haze.service.impl.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aurfy.haze.service.impl.bank.handler.BankHandler;

/**
 * thread to choose the sender to send to bank
 * 
 * @author zhangcheng
 *
 */
public class ConsumerThread extends Thread {

	private BankingTask task;

	private static final Logger logger = LoggerFactory.getLogger(ConsumerThread.class);

	public ConsumerThread(BankingTask task) {
		this.task = task;
	}

	@Override
	public void run() {
		BankHandler handler = task.getHandler();
		logger.debug("inovke the handler ");
		handler.process();
	}

	public BankingTask getTask() {
		return task;
	}

	public void setTask(BankingTask task) {
		this.task = task;
	}

}
