package com.aurfy.haze.dao.configuration.channel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.aurfy.haze.dao.PersistUnitTest;
import com.aurfy.haze.entity.configuration.channel.ChannelMappingEntity;
import com.aurfy.haze.utils.SecurityUtils;

public class ChannelMappingMapperTest extends PersistUnitTest {

	@Autowired
	private ChannelMappingMapper mapper;

	public ChannelMappingEntity newChannelMappingEntity() {
		ChannelMappingEntity cm = new ChannelMappingEntity();
		cm.setID(SecurityUtils.UUID());
		cm.setMerId(SecurityUtils.UUID());
		cm.setChannelId(SecurityUtils.UUID());
		return cm;
	}

	@Test
	@Transactional
	public void testSelectOne() {
		try {
			ChannelMappingEntity cm = newChannelMappingEntity();
			String oldId = cm.getID();
			int count = mapper.insert(cm);
			assertEquals(1, count);
			assertEquals(cm, mapper.selectOne(oldId));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testDeleteByChannel() {
		try {
			ChannelMappingEntity cm = newChannelMappingEntity();
			String oldId = cm.getID();
			String channelId = cm.getChannelId();
			int count = mapper.insert(cm);
			assertEquals(1, count);
			count = mapper.deleteByChannel(channelId);
			assertEquals(1, count);
			ChannelMappingEntity x = mapper.selectOne(oldId);
			assertEquals(null, x);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
