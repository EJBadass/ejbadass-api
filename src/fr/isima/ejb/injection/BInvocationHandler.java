package fr.isima.ejb.injection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class BInvocationHandler implements InvocationHandler {
	
	private Object instance = null;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Class<?> iface = method.getDeclaringClass();	// get interface
		Class<?> cls = BClassFinder.find(iface);		// get corresponding class
		Object result = null;							// result of method
		
		if(cls!=null) {
			if(getInstance()==null)
				setInstance(BObjectFactory.createInstance(cls));	// create object
			if(getInstance()!=null) {
				
				// check behaviour and get interceptors
				HashSet<Class<?>> interceptorClasses = BBehaviourManager.check(cls, method); 
				// creation of interceptors
				Set<IInterceptor> interceptors = new HashSet<>();
				for (Class<?> intClass : interceptorClasses) {
					interceptors.add((IInterceptor) BObjectFactory.createInstance(intClass));
				}
				
				BInterceptorChain respChain = (BInterceptorChain) BObjectFactory.createInstance(BInterceptorChain.class);
				respChain.buildChain(interceptors);
				
				result = respChain.proceed(getInstance(), method, args);
			}
		}
		return result;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

}
