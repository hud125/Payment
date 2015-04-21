package com.aurfy.haze.service.impl.thread;

import static org.junit.Assert.fail;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.aurfy.haze.service.conf.OverwritableConfigReader;

public class ThreadPoolProviderTest {

	@Test
	public void testGetWithDefaultName() {
		try {
			ThreadPoolProvider.getThreadPool(OverwritableConfigReader.DEFAULT_KEY_PREFIX);
			fail("should throw exception with default name");
		} catch (RuntimeException e) {
			// correct
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testBankSenderPool() {
		testThreadPool("bankSender", false);
	}

	@Test
	public void testMerNotifyPool() {
		testThreadPool("merNotify", true);
	}

	private void testThreadPool(String name, boolean manualWait) {
		try {
			ThreadPoolTaskExecutor pool = ThreadPoolProvider.getThreadPool(name);

			// three threads
			for (int i = 0; i < 3; ++i) {
				pool.execute(new Runnable() {

					private int count = 0;

					@Override
					public void run() {
						for (int j = 0; j < 5; j++) {
							System.out.println(Thread.currentThread().getName() + ":" + (count++));
							try {
								Thread.sleep(RandomUtils.nextLong(500, 3000));
							} catch (InterruptedException e) {
								// ignore
							}
						}
						System.out.println(Thread.currentThread().getName() + " completed");
					}
				});
			}

			// if the pool is not set as waitForJobsToCompleteOnShutdown=true, use the following code to wait
			if (manualWait) {
				int threadCount = 0;
				do {
					threadCount = pool.getActiveCount();
					System.out.print("activeThreadCount:" + threadCount);
					if (threadCount > 0) {
						System.out.println(", still waiting...");
					} else {
						System.out.println(", job done. shutting down...");
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// ignore
					}
				} while (threadCount > 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			ThreadPoolProvider.shutdownAll();
		}
	}
}
