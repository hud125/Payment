package com.aurfy.haze.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.aurfy.haze.entity.Entity;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MapperEntity {

	/**
	 * entity class for mapper.
	 * 
	 * @return
	 */
	public Class<? extends Entity> value();

	/**
	 * whether a CRUDBean with the same POJO name is required.
	 * 
	 * @return
	 */
	public boolean CRUDBeanRequired();
}
