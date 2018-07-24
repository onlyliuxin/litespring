package org.litespring.beans.factory;

import org.litespring.beans.BeansException;
import org.litespring.util.StringUtils;



public class NoSuchBeanDefinitionException extends BeansException {

	/** Name of the missing bean */
	private String beanName;

	/** Required type of the missing bean */
	private Class<?> beanType;


	/**
	 * Create a new {@code NoSuchBeanDefinitionException}.
	 * @param name the name of the missing bean
	 */
	public NoSuchBeanDefinitionException(String name) {
		super("No bean named '" + name + "' is defined");
		this.beanName = name;
	}

	/**
	 * Create a new {@code NoSuchBeanDefinitionException}.
	 * @param name the name of the missing bean
	 * @param message detailed message describing the problem
	 */
	public NoSuchBeanDefinitionException(String name, String message) {
		super("No bean named '" + name + "' is defined: " + message);
		this.beanName = name;
	}

	/**
	 * Create a new {@code NoSuchBeanDefinitionException}.
	 * @param type required type of the missing bean
	 */
	public NoSuchBeanDefinitionException(Class<?> type) {
		super("No qualifying bean of type [" + type.getName() + "] is defined");
		this.beanType = type;
	}

	/**
	 * Create a new {@code NoSuchBeanDefinitionException}.
	 * @param type required type of the missing bean
	 * @param message detailed message describing the problem
	 */
	public NoSuchBeanDefinitionException(Class<?> type, String message) {
		super("No qualifying bean of type [" + type.getName() + "] is defined: " + message);
		this.beanType = type;
	}

	/**
	 * Create a new {@code NoSuchBeanDefinitionException}.
	 * @param type required type of the missing bean
	 * @param dependencyDescription a description of the originating dependency
	 * @param message detailed message describing the problem
	 */
	public NoSuchBeanDefinitionException(Class<?> type, String dependencyDescription, String message) {
		super("No qualifying bean of type [" + type.getName() + "] found for dependency" +
				(StringUtils.hasLength(dependencyDescription) ? " [" + dependencyDescription + "]" : "") +
				": " + message);
		this.beanType = type;
	}


	/**
	 * Return the name of the missing bean, if it was a lookup <em>by name</em> that failed.
	 */
	public String getBeanName() {
		return this.beanName;
	}

	/**
	 * Return the required type of the missing bean, if it was a lookup <em>by type</em> that failed.
	 */
	public Class<?> getBeanType() {
		return this.beanType;
	}

	/**
	 * Return the number of beans found when only one matching bean was expected.
	 * For a regular NoSuchBeanDefinitionException, this will always be 0.
	 * @see NoUniqueBeanDefinitionException
	 */
	public int getNumberOfBeansFound() {
		return 0;
	}
}