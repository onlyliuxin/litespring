package org.litespring.core.type.classreading;

import org.litespring.core.annotation.AnnotationAttributes;
import org.litespring.core.type.AnnotationMetadata;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Type;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 读取类的基本信息以及  类上的注解信息
 */
public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor implements  AnnotationMetadata {
	// 注解的类名集合
	private final Set<String> annotationSet = new LinkedHashSet<String>(4);
	// 注解的类名以及注解的属性map， AnnotationAttributes为注解属性的map
	private final Map<String, AnnotationAttributes> attributeMap = new LinkedHashMap<String, AnnotationAttributes>(4);
	
	public AnnotationMetadataReadingVisitor() {
		
	}

	/**
	 *
	 * @param desc 该字段为 类上的注解类名，格式为 L加类名路径( L表示object，在字节码中有说明)，例如 Lorg/litespring/stereotype/Component
	 * @param visible
     * @return
     */
	@Override
	public AnnotationVisitor visitAnnotation(final String desc, boolean visible) {
		// className :  org.litespring.stereotype.Component
		String className = Type.getType(desc).getClassName();
		this.annotationSet.add(className);
		// 解析注解的属性
		return new AnnotationAttributesReadingVisitor(className, this.attributeMap);
	}
	public Set<String> getAnnotationTypes() {
		return this.annotationSet;
	}

	public boolean hasAnnotation(String annotationType) {
		return this.annotationSet.contains(annotationType);
	}

	public AnnotationAttributes getAnnotationAttributes(String annotationType) {
		return this.attributeMap.get(annotationType);
	}

	
	
}
