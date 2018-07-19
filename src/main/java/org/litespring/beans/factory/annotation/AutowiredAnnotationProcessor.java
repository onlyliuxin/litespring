package org.litespring.beans.factory.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import org.litespring.beans.factory.config.AutowireCapableBeanFactory;
import org.litespring.core.annotation.AnnotationUtils;
import org.litespring.util.ReflectionUtils;


public class AutowiredAnnotationProcessor  {
	
	private AutowireCapableBeanFactory beanFactory;
	private String requiredParameterName = "required";
	private boolean requiredParameterValue = true;
	
	private final Set<Class<? extends Annotation>> autowiredAnnotationTypes =
			new LinkedHashSet<Class<? extends Annotation>>();
	
	public AutowiredAnnotationProcessor(){
		this.autowiredAnnotationTypes.add(Autowired.class);
	}
	
	public InjectionMetadata buildAutowiringMetadata(Class<?> clazz) {
		
		LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();
		Class<?> targetClass = clazz;

		do {
			LinkedList<InjectionElement> currElements = new LinkedList<InjectionElement>();
			for (Field field : targetClass.getDeclaredFields()) {
				Annotation ann = findAutowiredAnnotation(field);
				if (ann != null) {
					if (Modifier.isStatic(field.getModifiers())) {
						
						continue;
					}
					boolean required = determineRequiredStatus(ann);
					currElements.add(new AutowiredFieldElement(field, required,beanFactory));
				}
			}
			for (Method method : targetClass.getDeclaredMethods()) {
				//TODO 处理方法注入
			}
			elements.addAll(0, currElements);
			targetClass = targetClass.getSuperclass();
		}
		while (targetClass != null && targetClass != Object.class);

		return new InjectionMetadata(clazz, elements);
	}
	
	protected boolean determineRequiredStatus(Annotation ann) {
		try {
			Method method = ReflectionUtils.findMethod(ann.annotationType(), this.requiredParameterName);
			if (method == null) {
				// Annotations like @Inject and @Value don't have a method (attribute) named "required"
				// -> default to required status
				return true;
			}
			return (this.requiredParameterValue == (Boolean) ReflectionUtils.invokeMethod(method, ann));
		}
		catch (Exception ex) {
			// An exception was thrown during reflective invocation of the required attribute
			// -> default to required status
			return true;
		}
	}
	
	private Annotation findAutowiredAnnotation(AccessibleObject ao) {
		for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
			Annotation ann = AnnotationUtils.getAnnotation(ao, type);
			if (ann != null) {
				return ann;
			}
		}
		return null;
	}
	public void setBeanFactory(AutowireCapableBeanFactory beanFactory){
		this.beanFactory = beanFactory;
	}
}
