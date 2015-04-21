package com.aurfy.haze.service.impl.helper;

import com.aurfy.haze.dao.CRUDMapper;
import com.aurfy.haze.entity.Entity;
import com.aurfy.haze.service.bean.Bean;

public final class RegistryEntry {

	private String pojoName;
	private Class<? extends Bean> beanClass;
	private Class<? extends Entity> entityClass;
	private Class<? extends CRUDMapper> mapperClass;
	private String mapperName;

	public RegistryEntry() {
	}

	public RegistryEntry(String pojoName, Class<? extends Bean> beanClass, Class<? extends Entity> entityClass,
			Class<? extends CRUDMapper> mapperClass, String mapperName) {
		super();
		this.pojoName = pojoName;
		this.beanClass = beanClass;
		this.entityClass = entityClass;
		this.mapperClass = mapperClass;
		this.mapperName = mapperName;
	}

	public String getPojoName() {
		return pojoName;
	}

	public void setPojoName(String pojoName) {
		this.pojoName = pojoName;
	}

	public Class<? extends Bean> getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class<? extends Bean> beanClass) {
		this.beanClass = beanClass;
	}

	public Class<? extends Entity> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<? extends Entity> entityClass) {
		this.entityClass = entityClass;
	}

	public Class<? extends CRUDMapper> getMapperClass() {
		return mapperClass;
	}

	public void setMapperClass(Class<? extends CRUDMapper> mapperClass) {
		this.mapperClass = mapperClass;
	}

	public String getMapperName() {
		return mapperName;
	}

	public void setMapperName(String mapperName) {
		this.mapperName = mapperName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beanClass == null) ? 0 : beanClass.hashCode());
		result = prime * result + ((entityClass == null) ? 0 : entityClass.hashCode());
		result = prime * result + ((mapperClass == null) ? 0 : mapperClass.hashCode());
		result = prime * result + ((mapperName == null) ? 0 : mapperName.hashCode());
		result = prime * result + ((pojoName == null) ? 0 : pojoName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistryEntry other = (RegistryEntry) obj;
		if (beanClass == null) {
			if (other.beanClass != null)
				return false;
		} else if (!beanClass.equals(other.beanClass))
			return false;
		if (entityClass == null) {
			if (other.entityClass != null)
				return false;
		} else if (!entityClass.equals(other.entityClass))
			return false;
		if (mapperClass == null) {
			if (other.mapperClass != null)
				return false;
		} else if (!mapperClass.equals(other.mapperClass))
			return false;
		if (mapperName == null) {
			if (other.mapperName != null)
				return false;
		} else if (!mapperName.equals(other.mapperName))
			return false;
		if (pojoName == null) {
			if (other.pojoName != null)
				return false;
		} else if (!pojoName.equals(other.pojoName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistryEntry [pojoName=");
		builder.append(pojoName);
		builder.append(", beanClass=");
		builder.append(beanClass);
		builder.append(", entityClass=");
		builder.append(entityClass);
		builder.append(", mapperClass=");
		builder.append(mapperClass);
		builder.append(", mapperName=");
		builder.append(mapperName);
		builder.append("]");
		return builder.toString();
	}

}
