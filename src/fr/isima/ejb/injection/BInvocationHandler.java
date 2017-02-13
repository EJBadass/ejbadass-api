package fr.isima.ejb.injection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Class<?> iface = method.getDeclaringClass();	// get interface
		Class<?> cls = BClassFinder.find(iface);		// get corresponding class
		Object result = null;							// result of method
		
		
		if(cls!=null) {
			Object obj = BObjectFactory.createInstance(cls);
			if(obj!=null) {
//				if(method.isAnnotationPresent(Log.class)) {
//	            	before(obj, method, args);
//	            	System.out.println("Invocation of " + method.getName());
					result = method.invoke(obj, args);
//	            	after(obj, method, args);
//	        	}
			}
		}
		return result;
	}

}
