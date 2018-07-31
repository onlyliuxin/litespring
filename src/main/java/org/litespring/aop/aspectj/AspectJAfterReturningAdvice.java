package org.litespring.aop.aspectj;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;

public class AspectJAfterReturningAdvice extends AbstractAspectJAdvice{
	
	public AspectJAfterReturningAdvice(Method adviceMethod,AspectJExpressionPointcut pointcut,Object adviceObject){
		super(adviceMethod,pointcut,adviceObject);
	}
	
	public Object invoke(MethodInvocation mi) throws Throwable {
		Object o = mi.proceed();
		//例如：调用TransactionManager的commit方法
		this.invokeAdviceMethod();
		return o;
	}

}