package fr.isima.ejbadass.plugable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class BInterceptorChain {

	private IInterceptor chain;
	
	public void buildChain(Set<IInterceptor> interceptors) {
		IInterceptor previous = null;
		for (IInterceptor iInterceptor : interceptors) {
			if(previous!=null)
				previous.setNext(iInterceptor);	// avoid the first
			else
				chain = iInterceptor;			// save the head
			
			previous = iInterceptor;
		}
		if(previous==null)
			chain = new BExecutionInterceptor();
		else
			previous.setNext(new BExecutionInterceptor()); 
	}

	public Object proceed(Object instance, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return chain.proceed(instance, method, args);
	}
	
}
