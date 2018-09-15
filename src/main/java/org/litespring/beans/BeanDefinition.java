package org.litespring.beans;

import java.util.List;



public interface BeanDefinition {
	public static final String SCOPE_SINGLETON = "singleton";
	public static final String SCOPE_PROTOTYPE = "prototype";
	public static final String SCOPE_DEFAULT = "";
	
	public boolean isSingleton();
	public boolean isPrototype();
	String getScope();
	void setScope(String scope);
	
	public String getBeanClassName();
	
	public List<PropertyValue> getPropertyValues();
	public ConstructorArgument getConstructorArgument();
	public String getID();
	public boolean hasConstructorArgumentValues();
	
	public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException;
	public Class<?> getBeanClass() throws IllegalStateException ;
	public boolean hasBeanClass();
	public boolean isSynthetic();
}
