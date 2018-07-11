package org.litespring.test.v4;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.type.AnnotationMetadata;
import org.litespring.core.type.classreading.MetadataReader;
import org.litespring.core.type.classreading.SimpleMetadataReader;
import org.litespring.stereotype.Component;

public class MetadataReaderTest {
	@Test
	public void testGetMetadata() throws IOException{
		ClassPathResource resource = new ClassPathResource("org/litespring/service/v4/PetStoreService.class");
		
		MetadataReader reader = new SimpleMetadataReader(resource);
		//注意：不需要单独使用ClassMetadata
		//ClassMetadata clzMetadata = reader.getClassMetadata();
		AnnotationMetadata amd = reader.getAnnotationMetadata();
		
		String annotation = Component.class.getName();
		
		Assert.assertTrue(amd.hasAnnotation(annotation));		
		AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);		
		Assert.assertEquals("petStore", attributes.get("value"));
		
		//注：下面对class metadata的测试并不充分
		Assert.assertFalse(amd.isAbstract());		
		Assert.assertFalse(amd.isFinal());
		Assert.assertEquals("org.litespring.service.v4.PetStoreService", amd.getClassName());
		
	}
}
