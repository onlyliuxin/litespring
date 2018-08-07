package org.litespring.aop.aspectj;

import java.lang.reflect.Method;

import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;
import org.litespring.aop.config.AspectInstanceFactory;

public abstract class AbstractAspectJAdvice implements Advice{
	
	
	protected Method adviceMethod;	
	protected AspectJExpressionPointcut pointcut;
	protected AspectInstanceFactory adviceObjectFactory;
	
	
	
	public AbstractAspectJAdvice(Method adviceMethod,
			AspectJExpressionPointcut pointcut,
			AspectInstanceFactory adviceObjectFactory){
		
		this.adviceMethod = adviceMethod;
		this.pointcut = pointcut;
		this.adviceObjectFactory = adviceObjectFactory;
	}
	
	
	public void invokeAdviceMethod() throws  Throwable{
	
		adviceMethod.invoke(adviceObjectFactory.getAspectInstance());
	}
	public Pointcut getPointcut(){
		return this.pointcut;
	}
	public Method getAdviceMethod() {
		return adviceMethod;
	}
	public Object getAdviceInstance() throws Exception {
		return adviceObjectFactory.getAspectInstance();
	}
}