package com.aurfy.haze.dao.configuration.channel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.configuration.channel.ChannelParameterEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class ChannelParameterMapperTest extends PersistUnitTest {

	@Autowired
	private ChannelParameterMapper mapper;

	public static ChannelParameterEntity newChannelParameterEntity() {
		ChannelParameterEntity channelParameter = new ChannelParameterEntity();

		channelParameter.setID(SecurityUtils.UUID());
		channelParameter.setConfigKey("Junit_" + RandomStringUtils.randomAlphanumeric(10));
		channelParameter.setChannelId("qqqqqqq");
		channelParameter.setConfigValue("bbbbb");
		return channelParameter;
	}

	public static ChannelParameterEntity newChannelParameterEntity(int i, String channelId) {
		ChannelParameterEntity channelParameter = new ChannelParameterEntity();

		channelParameter.setID(SecurityUtils.UUID());
		channelParameter.setConfigKey("aaaa" + i);
		channelParameter.setChannelId(channelId);
		channelParameter.setConfigValue("bbbbb");
		return channelParameter;
	}

	public ChannelParameterEntity internalCreateEntity() {
		ChannelParameterEntity channelParameter = newChannelParameterEntity();
		mapper.insert(channelParameter);
		return channelParameter;
	}

	@Test
	@Transactional
	public void testInsert() {
		internalCreateEntity();
	}

	@Test
	@Transactional
	public void testSelectOne() {
		ChannelParameterEntity channelParameter = internalCreateEntity();
		ChannelParameterEntity newAcc = mapper.selectOne(channelParameter.getID());
		assertTrue(channelParameter.equals(newAcc));
	}

	@Test
	@Transactional
	public void testCountAll() {
		int oldSize = mapper.countAll();
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();
		}

		int size = mapper.countAll();
		assertTrue(10 == (size - oldSize));
	}

	@Test
	@Transactional
	public void testSelectAll() {
		int oldSize = mapper.countAll();
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();

		}
		List<ChannelParameterEntity> list = mapper.selectAll();

		assertNotNull(list);
		assertTrue((list.size() - oldSize) == 10);
	}

	@Test
	@Transactional
	public void testCountBy() {
		ChannelParameterEntity filterEntity = new ChannelParameterEntity();
		int oldSize = mapper.countBy(filterEntity);
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();
		}

		int size = mapper.countBy(filterEntity);
		assertTrue(10 == (size - oldSize));
	}

	@Test
	@Transactional
	public void testSelectBy() {
		for (int i = 0; i < 10; i++) {
			internalCreateEntity();

		}
		ChannelParameterEntity filterEntity = new ChannelParameterEntity();
		List<ChannelParameterEntity> list = mapper.selectBy(filterEntity, 0, 10);

		assertNotNull(list);
		assertTrue(list.size() == 10);
	}

	@Test
	@Transactional
	public void testUpdate() {
		ChannelParameterEntity channelParameter = internalCreateEntity();

		channelParameter.setConfigKey("aaaa");
		channelParameter.setChannelId("qqqqqqq");
		channelParameter.setConfigValue("bbbbb");

		int count = mapper.update(channelParameter);
		assertTrue(count == 1);
		ChannelParameterEntity newAcq = mapper.selectOne(channelParameter.getID());
		assertTrue(channelParameter.equals(newAcq));
	}

	@Test
	@Transactional
	public void testDelete() {
		ChannelParameterEntity channelParameter = internalCreateEntity();

		int count = mapper.delete(channelParameter.getID());
		assertTrue(count == 1);
		ChannelParameterEntity channelParameter2 = mapper.selectOne(channelParameter.getID());
		assertTrue(channelParameter2 == null);
	}

	@Test
	@Transactional
	public void testDeleteByChannelId() {
		ChannelParameterEntity channelParameter = internalCreateEntity();

		int count = mapper.deleteByChannelId(channelParameter.getChannelId());
		assertTrue(count == 1);
		ChannelParameterEntity channelParameter2 = mapper.selectOne(channelParameter.getID());
		assertTrue(channelParameter2 == null);
	}
}