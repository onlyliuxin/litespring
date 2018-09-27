package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.BeanDefinitionValueResolver;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.dao.v2.AccountDao;

public class BeanDefinitionValueResolverTest {

	@Test
	public void testResolveRuntimeBeanReference() {
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);		
		reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
		
		BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
		
		RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
		Object value = resolver.resolveValueIfNecessary(reference);
		
		Assert.assertNotNull(value);		
		Assert.assertTrue(value instanceof AccountDao);				
	}
	@Test
	public void testResolveTypedStringValue() {
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);		
		reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
		
		BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
		
		TypedStringValue stringValue = new TypedStringValue("test");
		Object value = resolver.resolveValueIfNecessary(stringValue);		
		Assert.assertNotNull(value);		
		Assert.assertEquals("test", value);
		
	}
}
