package com.aurfy.haze.service.impl.converter;

import com.aurfy.haze.entity.Entity;
import com.aurfy.haze.service.bean.Bean;
import com.aurfy.haze.service.impl.helper.MapperRegistry;

public class ConverterHelper {

	private static final String DEFAULT_CONVERTER_PKG_NAME = ConverterHelper.class.getPackage().getName();
	private static final String DEFAULT_CONVERTER_SUFFIX = "Converter";

	private ConverterHelper() {
	}

	public static Converter getConverter(String pojoName) {
		final String convertClass = DEFAULT_CONVERTER_PKG_NAME + "." + pojoName + DEFAULT_CONVERTER_SUFFIX;
		try {
			return (Converter) Class.forName(convertClass).newInstance();
		} catch (Exception e) {
			return new DefaultConverter();
		}
	}

	@SuppressWarnings("unchecked")
	public static Converter getConverter(Class<?> clazz) {
		if (Entity.class.isAssignableFrom(clazz)) {
			return getConverter(MapperRegistry.entity2PojoName((Class<? extends Entity>) clazz));
		} else if (Bean.class.isAssignableFrom(clazz)) {
			return getConverter(MapperRegistry.bean2PojoName((Class<? extends Bean>) clazz));
		} else {
			throw new RuntimeException("unsupported entity or bean class: " + clazz.getSimpleName());
		}
	}

}
