package org.litespring.aop;



public interface Pointcut {
	MethodMatcher getMethodMatcher();
	String getExpression();
}
