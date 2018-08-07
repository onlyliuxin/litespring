package org.litespring.beans.factory.support;

import java.lang.reflect.Constructor;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;



public class ConstructorResolver {

	protected final Log logger = LogFactory.getLog(getClass());
	
	
	private final AbstractBeanFactory beanFactory;


	
	public ConstructorResolver(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	
	public Object autowireConstructor(final BeanDefinition bd) {
		
		Constructor<?> constructorToUse = null;		
		Object[] argsToUse = null;
	
		Class<?> beanClass = null;
		try {
			beanClass = this.beanFactory.getBeanClassLoader().loadClass(bd.getBeanClassName());
			
		} catch (ClassNotFoundException e) {
			throw new BeanCreationException( bd.getID(), "Instantiation of bean failed, can't resolve class", e);
		}	
		
		
		Constructor<?>[] candidates = beanClass.getConstructors();	
		
		
		BeanDefinitionValueResolver valueResolver =
				new BeanDefinitionValueResolver(this.beanFactory);
		
		ConstructorArgument cargs = bd.getConstructorArgument();
		SimpleTypeConverter typeConverter = new SimpleTypeConverter();
		
		for(int i=0; i<candidates.length;i++){
			
			Class<?> [] parameterTypes = candidates[i].getParameterTypes();
			if(parameterTypes.length != cargs.getArgumentCount()){
				continue;
			}			
			argsToUse = new Object[parameterTypes.length];
			
			boolean result = this.valuesMatchTypes(parameterTypes, 
					cargs.getArgumentValues(), 
					argsToUse, 
					valueResolver, 
					typeConverter);
			
			if(result){
				constructorToUse = candidates[i];
				break;
			}
			
		}
		
		
		//找不到一个合适的构造函数
		if(constructorToUse == null){
			throw new BeanCreationException( bd.getID(), "can't find a apporiate constructor");
		}
		
		
		try {
			return constructorToUse.newInstance(argsToUse);
		} catch (Exception e) {
			throw new BeanCreationException( bd.getID(), "can't find a create instance using "+constructorToUse);
		}		
		
		
	}
	
	private boolean valuesMatchTypes(Class<?> [] parameterTypes,
			List<ConstructorArgument.ValueHolder> valueHolders,
			Object[] argsToUse,
			BeanDefinitionValueResolver valueResolver,
			SimpleTypeConverter typeConverter ){
		
		
		for(int i=0;i<parameterTypes.length;i++){
			ConstructorArgument.ValueHolder valueHolder 
				= valueHolders.get(i);
			//获取参数的值，可能是TypedStringValue, 也可能是RuntimeBeanReference
			Object originalValue = valueHolder.getValue();
			
			try{
				//获得真正的值
				Object resolvedValue = valueResolver.resolveValueIfNecessary( originalValue);
				//如果参数类型是 int, 但是值是字符串,例如"3",还需要转型
				//如果转型失败，则抛出异常。说明这个构造函数不可用
				Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
				//转型成功，记录下来
				argsToUse[i] = convertedValue;
			}catch(Exception e){
				logger.error(e);
				return false;
			}				
		}
		return true;
	}
	

}
