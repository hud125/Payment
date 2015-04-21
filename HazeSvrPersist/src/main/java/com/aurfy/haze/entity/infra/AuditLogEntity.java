package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.AuditLog;
import com.aurfy.haze.entity.Entity;

@Alias("AuditLogEntity")
public class AuditLogEntity extends AuditLog implements Entity {

}
