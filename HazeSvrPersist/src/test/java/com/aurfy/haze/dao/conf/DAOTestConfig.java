package com.aurfy.haze.dao.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aurfy.haze.dao.HazeMapper;
import com.aurfy.haze.dao.spring.PersistConfig;

@Configuration
@MapperScan(basePackageClasses = { HazeMapper.class })
@EnableTransactionManagement
// @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DAOTestConfig extends PersistConfig {

}
