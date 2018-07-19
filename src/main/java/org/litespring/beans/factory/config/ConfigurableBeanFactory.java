package org.litespring.beans.factory.config;

public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {	
	void setBeanClassLoader(ClassLoader beanClassLoader);
	ClassLoader getBeanClassLoader();	
}
