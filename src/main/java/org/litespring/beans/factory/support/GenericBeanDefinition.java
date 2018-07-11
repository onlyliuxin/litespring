package org.litespring.beans.factory.support;

import java.util.ArrayList;
import java.util.List;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;

public class GenericBeanDefinition implements BeanDefinition {
	private String id;
	private String beanClassName;
	private boolean singleton = true;
	private boolean prototype = false;
	private String scope = SCOPE_DEFAULT;
	
	List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
	private ConstructorArgument constructorArgument = new ConstructorArgument();
	
	public GenericBeanDefinition(String id, String beanClassName) {
		
		this.id = id;
		this.beanClassName = beanClassName;
	}
	public GenericBeanDefinition() {
		
	}
	public String getBeanClassName() {
		
		return this.beanClassName;
	}
	public void setBeanClassName(String className){
		this.beanClassName = className;
	}
	
	public boolean isSingleton() {
		return this.singleton;
	}
	public boolean isPrototype() {
		return this.prototype;
	}
	public String getScope() {
		return this.scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
		this.prototype = SCOPE_PROTOTYPE.equals(scope);
		
	}
	public List<PropertyValue> getPropertyValues(){
		return this.propertyValues;
	}
	public ConstructorArgument getConstructorArgument() {
		return this.constructorArgument;
	}
	public String getID() {
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	public boolean hasConstructorArgumentValues() {
		return !this.constructorArgument.isEmpty();
	}
}
