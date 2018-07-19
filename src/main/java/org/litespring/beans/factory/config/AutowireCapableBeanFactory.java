package org.litespring.beans.factory.config;

import org.litespring.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {
	public Object resolveDependency(DependencyDescriptor descriptor);
}
