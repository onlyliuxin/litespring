package org.litespring.test.v5;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.aop.aspectj.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectj.AspectJBeforeAdvice;
import org.litespring.aop.aspectj.AspectJExpressionPointcut;
import org.litespring.aop.config.AspectInstanceFactory;
import org.litespring.aop.framework.AopConfig;
import org.litespring.aop.framework.AopConfigSupport;
import org.litespring.aop.framework.CglibProxyFactory;
import org.litespring.beans.factory.BeanFactory;
import org.litespring.service.v5.PetStoreService;
import org.litespring.tx.TransactionManager;
import org.litespring.util.MessageTracker;



public class CglibAopProxyTest extends AbstractV5Test{
		
	private  AspectJBeforeAdvice beforeAdvice = null;
	private  AspectJAfterReturningAdvice afterAdvice = null;
	private  AspectJExpressionPointcut pc = null;
	private  BeanFactory beanFactory = null;
	private  AspectInstanceFactory aspectInstanceFactory = null;
	
	@Before
	public  void setUp() throws Exception{		
		
		MessageTracker.clearMsgs();
		
		String expression = "execution(* org.litespring.service.v5.*.placeOrder(..))";
		pc = new AspectJExpressionPointcut();
		pc.setExpression(expression);
		
		beanFactory = this.getBeanFactory("petstore-v5.xml");
		aspectInstanceFactory = this.getAspectInstanceFactory("tx");
		aspectInstanceFactory.setBeanFactory(beanFactory);
		
		beforeAdvice = new AspectJBeforeAdvice(
				getAdviceMethod("start"),
				pc,
				aspectInstanceFactory);
		
		afterAdvice = new AspectJAfterReturningAdvice(
				getAdviceMethod("commit"),
				pc,
				aspectInstanceFactory);		
		
	}
	
	@Test
	public void testGetProxy(){
		
		AopConfig config = new AopConfigSupport();
		
		config.addAdvice(beforeAdvice);
		config.addAdvice(afterAdvice);
		config.setTargetObject(new PetStoreService());
		
		
		CglibProxyFactory proxyFactory = new CglibProxyFactory(config);
		
		PetStoreService proxy = (PetStoreService)proxyFactory.getProxy();
		
		proxy.placeOrder();				
		
		
		List<String> msgs = MessageTracker.getMsgs();
		Assert.assertEquals(3, msgs.size());
		Assert.assertEquals("start tx", msgs.get(0));	
		Assert.assertEquals("place order", msgs.get(1));	
		Assert.assertEquals("commit tx", msgs.get(2));	
		
		proxy.toString();
	}
	
	
}
