package org.litespring.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {

	@Test
	public void testGetBean() {
		
		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		
		assertEquals("org.litespring.service.v1.PetStoreService",bd.getBeanClassName());
		
		PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
		
		assertNotNull(petStore);
	}
	
	@Test
	public void testInvalidBean(){

		BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");
		try{
			factory.getBean("invalidBean");
		}catch(BeanCreationException e){
			return;
		}
		Assert.fail("expect BeanCreationException ");
	}
	@Test
	public void testInvalidXML(){
		
		try{
			new DefaultBeanFactory("xxxx.xml");
		}catch(BeanDefinitionStoreException e){
			return;
		}
		Assert.fail("expect BeanDefinitionStoreException ");
	}

}
