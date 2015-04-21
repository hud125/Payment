package com.aurfy.haze.service.impl.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aurfy.haze.dao.spring.PersistConfig;
import com.aurfy.haze.service.impl.BaseHazeService;

/**
 * 
 * the annotation <code>@Configuration</code> does not have <code>@Inherited</code>,<br/>
 * so you must add it in this class again.
 * 
 * @author rocket
 *
 */
@Configuration
@ComponentScan(basePackageClasses = { BaseHazeService.class })
@EnableTransactionManagement
public class ServiceTestConfig extends PersistConfig {

}
