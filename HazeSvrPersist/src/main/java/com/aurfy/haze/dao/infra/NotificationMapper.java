package com.aurfy.haze.dao.infra;

import java.util.List;

import org.springframework.stereotype.Component;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.dao.MapperEntity;
import com.aurfy.haze.dao.conf.MapperConstant.AOP_MAPPER;
import com.aurfy.haze.entity.infra.NotificationEntity;

@Component(AOP_MAPPER.NOTIFICATION_MAPPER)
@MapperEntity(value = NotificationEntity.class, CRUDBeanRequired = true)
public interface NotificationMapper extends CRUDMapper {
	/**
	 * select all notifiable items, which equals to: delivery_status is not SUCCEED, and retry_counter < max_counter.
	 * 
	 * @return
	 */
	public List<NotificationEntity> selectNotifiableItems();

}
