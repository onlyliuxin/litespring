package org.litespring.context.support;

import java.util.List;

import org.litespring.aop.aspectj.AspectJAutoProxyCreator;
import org.litespring.beans.factory.NoSuchBeanDefinitionException;
import org.litespring.beans.factory.annotation.AutowiredAnnotationProcessor;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext {

	private DefaultBeanFactory factory = null;
	private ClassLoader beanClassLoader;
	
	public AbstractApplicationContext(String configFile){
		factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);	
		Resource resource = this.getResourceByPath(configFile);
		reader.loadBeanDefinitions(resource);
		factory.setBeanClassLoader(this.getBeanClassLoader());
		registerBeanPostProcessors(factory);
	}
	
	public Object getBean(String beanID) {
		
		return factory.getBean(beanID);
	}
	
	protected abstract Resource getResourceByPath(String path);
	
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
	}

    public ClassLoader getBeanClassLoader() {
		return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
	}
    protected void registerBeanPostProcessors(ConfigurableBeanFactory beanFactory) {
    	{
			AutowiredAnnotationProcessor postProcessor = new AutowiredAnnotationProcessor();
			postProcessor.setBeanFactory(beanFactory);
			beanFactory.addBeanPostProcessor(postProcessor);
    	}
    	{
    		AspectJAutoProxyCreator postProcessor = new AspectJAutoProxyCreator();
    		postProcessor.setBeanFactory(beanFactory);
    		beanFactory.addBeanPostProcessor(postProcessor);
    	}
		
	}
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException{
    	return this.factory.getType(name);
    }
    public List<Object> getBeansByType(Class<?> type){
    	return this.factory.getBeansByType(type);
    }

}
