package fr.isima.ejb.injection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import fr.isima.ejb.annotation.Log;

public class BInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Class<?> iface = method.getDeclaringClass();
		//Object i = BInjector.createInstance(iface);
		
//		if(method.isAnnotationPresent(Log.class)) {
//            before(i, method, args);
//            System.out.println("Invocation of " + method.getName());
		//Object result = method.invoke(i, args);
//            after(i, method, args);
//        }
		return null;
	}

}
