package fr.isima.ejb.injection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class BInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Class<?> iface = method.getDeclaringClass();	// get interface
		Class<?> cls = BClassFinder.find(iface);		// get corresponding class
		Object result = null;							// result of method
		
		if(cls!=null) {
			Object obj = BObjectFactory.createInstance(cls);	// create object
			if(obj!=null) {
				
				// check behaviour and get interceptors
				HashSet<Class<?>> interceptorClasses = BBehaviourManager.check(cls, method); 
				// creation of interceptors
				Set<IInterceptor> interceptors = new HashSet<>();
				for (Class<?> intClass : interceptorClasses) {
					interceptors.add((IInterceptor) BObjectFactory.createInstance(intClass));
				}
				
				// before
				for (IInterceptor iInterceptor : interceptors) {
					iInterceptor.before(obj, method, args);
				}
				// method execution
				result = method.invoke(obj, args);
				// after
				for (IInterceptor iInterceptor : interceptors) {
					iInterceptor.before(obj, method, args);
				}

			}
		}
		return result;
	}

}
