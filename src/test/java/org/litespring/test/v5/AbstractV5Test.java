package org.litespring.test.v5;

import java.lang.reflect.Method;

import org.litespring.aop.config.AspectInstanceFactory;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.tx.TransactionManager;

public class AbstractV5Test {
		
	protected BeanFactory getBeanFactory(String configFile){
		DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
		Resource resource = new ClassPathResource(configFile);
		reader.loadBeanDefinitions(resource);	
		return  defaultBeanFactory;		
	}
	
	protected  Method getAdviceMethod( String methodName) throws Exception{
		return TransactionManager.class.getMethod(methodName);		
	}
	
	protected  AspectInstanceFactory getAspectInstanceFactory(String targetBeanName){
		AspectInstanceFactory factory = new AspectInstanceFactory();
		factory.setAspectBeanName(targetBeanName);		
		return factory;
	}
	
	
}
