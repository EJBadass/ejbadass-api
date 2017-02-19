package fr.isima.ejb.log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.IInterceptor;

public class LogInterceptor implements IInterceptor {

	@Inject
	ILogger logger;
	
	private IInterceptor next;

	@Override
	public Object proceed(Object object, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		logger.log(method.getName());
		Object result = next().proceed(object, method, args);
		logger.log(method.getName());
		return result;
	}

	@Override
	public IInterceptor next() {
		return this.next;
	}

	@Override
	public void setNext(IInterceptor next) {
		this.next = next;
	}

}
