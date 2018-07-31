package org.litespring.aop.aspectj;

import java.lang.reflect.Method;

import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;

public abstract class AbstractAspectJAdvice implements Advice{
	
	
	protected Method adviceMethod;	
	protected AspectJExpressionPointcut pointcut;
	protected Object adviceObject;
	
	
	
	public AbstractAspectJAdvice(Method adviceMethod,
			AspectJExpressionPointcut pointcut,
			Object adviceObject){
		
		this.adviceMethod = adviceMethod;
		this.pointcut = pointcut;
		this.adviceObject = adviceObject;
	}
	
	
	public void invokeAdviceMethod() throws  Throwable{
	
		adviceMethod.invoke(adviceObject);
	}
	public Pointcut getPointcut(){
		return this.pointcut;
	}
	public Method getAdviceMethod() {
		return adviceMethod;
	}
}