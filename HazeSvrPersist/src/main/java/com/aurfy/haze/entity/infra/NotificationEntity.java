package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.Notification;
import com.aurfy.haze.entity.Entity;

@Alias("NotificationEntity")
public class NotificationEntity extends Notification implements Entity{

}
