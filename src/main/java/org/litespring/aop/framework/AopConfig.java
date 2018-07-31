package org.litespring.aop.framework;
/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.lang.reflect.Method;
import java.util.List;

import org.litespring.aop.Advice;
import org.litespring.service.v5.PetStoreService;




public interface AopConfig  {

	
	Class<?> getTargetClass();
	
	Object getTargetObject();

	boolean isProxyTargetClass();

	
	Class<?>[] getProxiedInterfaces();

	
	boolean isInterfaceProxied(Class<?> intf);

	
	List<Advice> getAdvices();

	
	void addAdvice(Advice advice) ;

	List<Advice> getAdvices(Method method/*,Class<?> targetClass*/);

	void setTargetObject(Object obj);


}
