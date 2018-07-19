package org.litespring.beans.factory.annotation;

import java.lang.reflect.Field;

import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.AutowireCapableBeanFactory;
import org.litespring.beans.factory.config.DependencyDescriptor;
import org.litespring.util.ReflectionUtils;

public class AutowiredFieldElement extends InjectionElement {
	boolean required;
	
	public AutowiredFieldElement(Field f,boolean required,AutowireCapableBeanFactory factory) {
		super(f,factory);
		this.required = required;
	}
	
	public Field getField(){
		return (Field)this.member;
	}
	@Override
	public void inject(Object target) {
		
		Field field = this.getField();
		try {
			
			DependencyDescriptor desc = new DependencyDescriptor(field, this.required);
								
			Object value = factory.resolveDependency(desc);
			
			if (value != null) {
				
				ReflectionUtils.makeAccessible(field);
				field.set(target, value);
			}
		}
		catch (Throwable ex) {
			throw new BeanCreationException("Could not autowire field: " + field, ex);
		}
	}

}
