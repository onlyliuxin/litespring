package org.litespring.aop.aspectj;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.config.AspectInstanceFactory;

public class AspectJAfterThrowingAdvice extends AbstractAspectJAdvice  {

	
	
	public AspectJAfterThrowingAdvice(Method adviceMethod, AspectJExpressionPointcut pointcut, AspectInstanceFactory adviceObjectFactory) {

		super(adviceMethod, pointcut, adviceObjectFactory);
	}


	public Object invoke(MethodInvocation mi) throws Throwable {
		try {
			return mi.proceed();
		}
		catch (Throwable t) {			
			invokeAdviceMethod();			
			throw t;
		}
	}

}
