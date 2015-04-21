package com.aurfy.haze.utils;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.aurfy.haze.annotation.ClassWithAnnotation;
import com.aurfy.haze.annotation.EnumWithAnnotation;
import com.aurfy.haze.annotation.sub.AbstractClass;
import com.aurfy.haze.annotation.sub.SubClassWithAnnotation;
import com.aurfy.haze.annotation.sub.SubEnumWithAnnotation;

public class ScannerUtilsTest {

	final String pkg = "com.aurfy.haze.annotation";

	@Test
	public void testScan4Annotation() {
		final Set<Class<?>> baseResult = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] {
				ClassWithAnnotation.class, EnumWithAnnotation.class, SubClassWithAnnotation.class,
				SubEnumWithAnnotation.class }));
		final Set<Class<?>> withAbstractResult = new HashSet<Class<?>>(baseResult);
		withAbstractResult.add(AbstractClass.class);

		Set<Class<?>> result = ScannerUtils.scan4Annotation(pkg, TestAnnotation.class, false);
		assertTrue(CollectionUtils.isEqualCollection(baseResult, result));

		result = ScannerUtils.scan4Annotation(pkg, TestAnnotation.class, true);
		assertTrue(CollectionUtils.isEqualCollection(withAbstractResult, result));
	}

	@Test
	public void testScan() {
		Set<Class<?>> result;

		result = ScannerUtils.scan(ClassWithAnnotation.class, pkg, TestAnnotation.class, true);
		assertTrue(CollectionUtils.isEqualCollection(
				new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SubClassWithAnnotation.class })), result));

		result = ScannerUtils.scan(ClassWithAnnotation.class, pkg, TestAnnotation.class, false);
		assertTrue(CollectionUtils.isEqualCollection(
				new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { SubClassWithAnnotation.class })), result));

		result = ScannerUtils.scan(null, pkg, TestAnnotation.class, true);
		assertTrue(CollectionUtils.isEqualCollection(
				new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { EnumWithAnnotation.class,
						SubEnumWithAnnotation.class, SubClassWithAnnotation.class, ClassWithAnnotation.class,
						AbstractClass.class })), result));
	}

}
