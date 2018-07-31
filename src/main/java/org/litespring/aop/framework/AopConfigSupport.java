package org.litespring.aop.framework;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;
import org.litespring.util.Assert;




/**
 * Base class for AOP proxy configuration managers.
 * These are not themselves AOP proxies, but subclasses of this class are
 * normally factories from which AOP proxy instances are obtained directly.
 *
 * <p>This class frees subclasses of the housekeeping of Advices
 * and Advisors, but doesn't actually implement proxy creation
 * methods, which are provided by subclasses.
 *
 * <p>This class is serializable; subclasses need not be.
 * This class is used to hold snapshots of proxies.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see org.springframework.AopProxyFactory.framework.AopProxy
 */
@SuppressWarnings("unchecked")
public class AopConfigSupport implements AopConfig {


	private boolean proxyTargetClass = false;

	
	private Object targetObject = null;

	private List<Advice> advices = new ArrayList<Advice>();

	private List<Class> interfaces = new ArrayList<Class>();

	
	public AopConfigSupport() {
		
	}

	
	public void setTargetObject(Object targetObject){
		this.targetObject = targetObject;
	}
	
	public Object getTargetObject(){
		return this.targetObject;
	}
	public Class<?> getTargetClass() {
		return this.targetObject.getClass();
	}



	public void addInterface(Class<?> intf) {
		Assert.notNull(intf, "Interface must not be null");
		if (!intf.isInterface()) {
			throw new IllegalArgumentException("[" + intf.getName() + "] is not an interface");
		}
		if (!this.interfaces.contains(intf)) {
			this.interfaces.add(intf);
			
		}
	}

	/**
	 * Remove a proxied interface.
	 * <p>Does nothing if the given interface isn't proxied.
	 * @param intf the interface to remove from the proxy
	 * @return {@code true} if the interface was removed; {@code false}
	 * if the interface was not found and hence could not be removed
	 */
	/*public boolean removeInterface(Class<?> intf) {
		return this.interfaces.remove(intf);
	}*/

	public Class<?>[] getProxiedInterfaces() {
		return this.interfaces.toArray(new Class[this.interfaces.size()]);
	}

	public boolean isInterfaceProxied(Class<?> intf) {
		for (Class proxyIntf : this.interfaces) {
			if (intf.isAssignableFrom(proxyIntf)) {
				return true;
			}
		}
		return false;
	}	

	public void addAdvice(Advice advice)  {
		this.advices.add(advice);
	}


	public boolean isProxyTargetClass() {
		
		return proxyTargetClass;
	}

	public void setProxyTargetClass(boolean proxyTargetClass) {
		this.proxyTargetClass = proxyTargetClass;
	}

	public List<Advice> getAdvices() {
		
		return this.advices;
	}

	public List<Advice> getAdvices(Method method) {
		List<Advice> result = new ArrayList<Advice>();
		for(Advice advice : this.getAdvices()){
			Pointcut pc = advice.getPointcut();
			if(pc.getMethodMatcher().matches(method)){
				result.add(advice);
			}
		}
		return result;
	}
}
