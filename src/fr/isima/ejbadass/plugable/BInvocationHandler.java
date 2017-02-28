package fr.isima.ejbadass.plugable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import fr.isima.ejbadass.injection.BClassFinder;
import fr.isima.ejbadass.injection.BObjectFactory;

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
				HashSet<Class<?>> interceptorClasses = BBehaviourManager.check(cls, method); 	// check behaviour and get interceptors
				Set<IInterceptor> interceptors = new HashSet<>();	// creation of interceptors
				
				for (Class<?> intClass : interceptorClasses)
					interceptors.add((IInterceptor) BObjectFactory.createInstance(intClass));
				
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
