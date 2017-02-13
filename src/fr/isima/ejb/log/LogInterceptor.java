package fr.isima.ejb.log;

import java.lang.reflect.Method;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.annotation.Log;
import fr.isima.ejb.injection.IInterceptor;

public class LogInterceptor implements IInterceptor {

	@Inject
	Logger logger;
	
	@Override
	public void before(Object object, Method method, Object[] args) {
		logger.log(method.toString());
	}

	@Override
	public void after(Object object, Method method, Object[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(method.isAnnotationPresent(Log.class)) {
            before(proxy, method, args);
            System.out.println("Invocation of " + method.getName());
            after(proxy, method, args);
        }
		return proxy;
	}

}
