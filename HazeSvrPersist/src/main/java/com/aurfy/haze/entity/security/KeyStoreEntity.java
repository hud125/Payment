package com.aurfy.haze.entity.security;

import org.apache.ibatis.type.Alias;

import com.aurfy.haze.core.model.infra.KeyStore;
import com.aurfy.haze.entity.Entity;

@Alias("KeyStoreEntity")
public class KeyStoreEntity extends KeyStore implements Entity {

}
