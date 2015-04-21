package com.aurfy.haze.service.impl.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.configuration.channel.AcquirerMapper;
import com.aurfy.haze.dao.configuration.channel.BankAccountMapper;
import com.aurfy.haze.dao.configuration.channel.BankMapper;
import com.aurfy.haze.dao.configuration.channel.ChannelMapper;
import com.aurfy.haze.dao.configuration.channel.ChannelProviderMapper;
import com.aurfy.haze.dao.infra.AuditLogMapper;
import com.aurfy.haze.dao.infra.UserMapper;
import com.aurfy.haze.entity.Entity;
import com.aurfy.haze.entity.configuration.channel.AcquirerEntity;
import com.aurfy.haze.entity.configuration.channel.BankAccountEntity;
import com.aurfy.haze.entity.configuration.channel.BankEntity;
import com.aurfy.haze.entity.configuration.channel.ChannelEntity;
import com.aurfy.haze.entity.configuration.channel.ChannelProviderEntity;
import com.aurfy.haze.entity.infra.AuditLogEntity;
import com.aurfy.haze.entity.infra.UserEntity;
import com.aurfy.haze.service.impl.ServiceUnitTest;

public class MapperRegistryTest extends ServiceUnitTest {

	@Autowired
	private MapperRegistry registry;

	@Test
	@Transactional
	public void testMapperHelper() {
		List<Class<? extends Entity>> input = new ArrayList<Class<? extends Entity>>();
		input.add(AcquirerEntity.class);
		input.add(BankAccountEntity.class);
		input.add(ChannelEntity.class);
		input.add(ChannelProviderEntity.class);
		input.add(UserEntity.class);
		input.add(BankEntity.class);
		input.add(AuditLogEntity.class);
		//
		List<Class<? extends CRUDMapper>> result = new ArrayList<Class<? extends CRUDMapper>>();
		result.add(AcquirerMapper.class);
		result.add(BankAccountMapper.class);
		result.add(ChannelMapper.class);
		result.add(ChannelProviderMapper.class);
		result.add(UserMapper.class);
		result.add(BankMapper.class);
		result.add(AuditLogMapper.class);

		//
		assertEquals(result.size(), input.size());

		for (int i = 0; i < input.size(); ++i) {
			Class<? extends Entity> clazz = input.get(i);
			CRUDMapper mapper = registry.get(clazz);
			assertNotNull(mapper);
			assertTrue(result.get(i).isAssignableFrom(mapper.getClass()));
		}
	}
}
