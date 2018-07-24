package org.litespring.beans.factory;

public interface BeanFactory {

	Object getBean(String beanID);
	
	Class<?> getType(String name) throws NoSuchBeanDefinitionException;

}
