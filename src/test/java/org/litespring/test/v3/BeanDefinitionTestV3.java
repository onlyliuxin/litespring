package org.litespring.test.v3;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.ConstructorArgument.ValueHolder;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;

public class BeanDefinitionTestV3 {

	@Test
	public void testConstructorArgument() {
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("petstore-v3.xml");
		reader.loadBeanDefinitions(resource);

		BeanDefinition bd = factory.getBeanDefinition("petStore");
		Assert.assertEquals("org.litespring.service.v3.PetStoreService", bd.getBeanClassName());
		
		ConstructorArgument args = bd.getConstructorArgument();
		List<ValueHolder> valueHolders = args.getArgumentValues();
		
		Assert.assertEquals(3, valueHolders.size());
		
		RuntimeBeanReference ref1 = (RuntimeBeanReference)valueHolders.get(0).getValue();
		Assert.assertEquals("accountDao", ref1.getBeanName());
		RuntimeBeanReference ref2 = (RuntimeBeanReference)valueHolders.get(1).getValue();
		Assert.assertEquals("itemDao", ref2.getBeanName());
		
		TypedStringValue strValue = (TypedStringValue)valueHolders.get(2).getValue();
		Assert.assertEquals( "1", strValue.getValue());
	}

}
