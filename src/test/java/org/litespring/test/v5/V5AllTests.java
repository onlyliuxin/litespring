package org.litespring.test.v5;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ApplicationContextTest5.class, CglibAopProxyTest.class, CGLibTest.class,
		MethodLocatingFactoryTest.class, PointcutTest.class, ReflectiveMethodInvocationTest.class })
public class V5AllTests {

}
