package org.litespring.core.annotation;


import static java.lang.String.format;

import java.util.LinkedHashMap;
import java.util.Map;

import org.litespring.util.Assert;


@SuppressWarnings("serial")
public class AnnotationAttributes extends LinkedHashMap<String, Object> {

	/**
	 * Create a new, empty {@link AnnotationAttributes} instance.
	 */
	public AnnotationAttributes() {
	}

	/**
	 * Create a new, empty {@link AnnotationAttributes} instance with the given initial
	 * capacity to optimize performance.
	 * @param initialCapacity initial size of the underlying map
	 */
	public AnnotationAttributes(int initialCapacity) {
		super(initialCapacity);
	}


	public AnnotationAttributes(Map<String, Object> map) {
		super(map);
	}

	
	public String getString(String attributeName) {
		return doGet(attributeName, String.class);
	}

	public String[] getStringArray(String attributeName) {
		return doGet(attributeName, String[].class);
	}

	public boolean getBoolean(String attributeName) {
		return doGet(attributeName, Boolean.class);
	}

	@SuppressWarnings("unchecked")
	public <N extends Number> N getNumber(String attributeName) {
		return (N) doGet(attributeName, Integer.class);
	}

	@SuppressWarnings("unchecked")
	public <E extends Enum<?>> E getEnum(String attributeName) {
		return (E) doGet(attributeName, Enum.class);
	}

	@SuppressWarnings("unchecked")
	public <T> Class<? extends T> getClass(String attributeName) {
		return doGet(attributeName, Class.class);
	}

	public Class<?>[] getClassArray(String attributeName) {
		return doGet(attributeName, Class[].class);
	}


	@SuppressWarnings("unchecked")
	private <T> T doGet(String attributeName, Class<T> expectedType) {
		
		Object value = this.get(attributeName);
		Assert.notNull(value, format("Attribute '%s' not found", attributeName));
		return (T) value;
	}

	
}
