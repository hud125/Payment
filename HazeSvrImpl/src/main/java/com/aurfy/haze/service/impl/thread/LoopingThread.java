package com.aurfy.haze.service.impl.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 循环守护线程
 * 
 * @author zhangcheng
 *
 */
public abstract class LoopingThread extends Thread {

	private static final Logger logger = LoggerFactory.getLogger(LoopingThread.class);

	private boolean running = true;

	public LoopingThread(boolean daemon) {
		setDaemon(daemon);
		setPriority(MAX_PRIORITY);
	}

	/**
	 * stop looping
	 */
	public synchronized void shutdown() {
		running = false;
	}

	/**
	 * run the tasks periodically, ignore any execution exception
	 */
	public void run() {
		try {
			while (isRunning()) {
				try {
					iterate();
				} catch (Exception x) {
					// ignore
					logger.error("error running LoopingThread", x);
				}
				if (getSleepDuration() > 0L) {
					try {
						sleep(getSleepDuration());
					} catch (InterruptedException e) {
						logger.warn("LoopingThread interrupted", e);
					}
				}
			}
		} finally {
			shutdown();
		}
	}

	public synchronized boolean isRunning() {
		return running;
	}

	/**
	 * get the default sleep duration in milliseconds.
	 * 
	 * @return
	 */
	public abstract long getSleepDuration();

	/**
	 * the actually work during each iteration, exceptions may be thrown
	 */
	protected abstract void iterate() throws Exception;

}