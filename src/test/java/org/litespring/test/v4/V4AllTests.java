package org.litespring.test.v4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ApplicationContextTest4.class, AutowiredAnnotationProcessorTest.class,
		ClassPathBeanDefinitionScannerTest.class, ClassReaderTest.class, DependencyDescriptorTest.class,
		InjectionMetadataTest.class, MetadataReaderTest.class, PackageResourceLoaderTest.class,
		XmlBeanDefinitionReaderTest.class })
public class V4AllTests {

}
