package com.aurfy.haze.web.spring;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.aurfy.haze.service.impl.payment.NotifyDaemonThread;

public class HazeServletContextListener implements ServletContextListener {

	public HazeServletContextListener() {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// ApplicationContext beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(sce
		// .getServletContext());
		NotifyDaemonThread.getInstance().start();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		NotifyDaemonThread.getInstance().shutdown();
	}

}
