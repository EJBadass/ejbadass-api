package fr.isima.ejb.injection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BExecutionInterceptor implements IInterceptor {

	@Override
	public Object proceed(Object object, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return method.invoke(object, args);
	}

	@Override
	public IInterceptor next() {
		return null;
	}

	@Override
	public void setNext(IInterceptor next) {
		
	}

}
