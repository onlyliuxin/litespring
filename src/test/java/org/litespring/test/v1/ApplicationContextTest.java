package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.litespring.FileSystemXmlApplicationContextRule;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.context.support.FileSystemXmlApplicationContext;
import org.litespring.service.v1.PetStoreService;

public class ApplicationContextTest {

	@Rule
	public FileSystemXmlApplicationContextRule context = new FileSystemXmlApplicationContextRule("petstore-v1.xml");

	@Test
	public void testGetBean() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
		PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
		Assert.assertNotNull(petStore);
	}
    @Test 
	public void testGetBeanFromFileSystemContext(){
		ApplicationContext ctx = new FileSystemXmlApplicationContext(context.getPath());
		PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");
		Assert.assertNotNull(petStore);
	}
}
