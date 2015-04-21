package com.aurfy.haze.entity.infra;

import org.apache.ibatis.type.Alias;
import com.aurfy.haze.core.model.infra.PasswordRecovery;
import com.aurfy.haze.entity.Entity;

@Alias("PasswordRecoveryEntity")
public class PasswordRecoveryEntity extends PasswordRecovery implements Entity {

	
}
