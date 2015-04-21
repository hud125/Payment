package com.aurfy.haze.web.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aurfy.haze.dao.HazeMapper;
import com.aurfy.haze.dao.spring.PersistConfig;
import com.aurfy.haze.service.impl.BaseHazeService;

@Configuration
@ComponentScan(basePackageClasses = { BaseHazeService.class })
@MapperScan(basePackageClasses = { HazeMapper.class })
@EnableTransactionManagement
public class HazeConfig extends PersistConfig {

	private static final Logger logger = LoggerFactory.getLogger(HazeConfig.class);

}
