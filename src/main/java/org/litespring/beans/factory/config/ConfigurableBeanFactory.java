package org.litespring.beans.factory.config;

import java.util.List;

public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {	
	void setBeanClassLoader(ClassLoader beanClassLoader);
	ClassLoader getBeanClassLoader();	
	void addBeanPostProcessor(BeanPostProcessor postProcessor);
	List<BeanPostProcessor> getBeanPostProcessors();
}
