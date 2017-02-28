package fr.isima.ejbadass.plugable;

import java.lang.reflect.Method;

public class BExecutionInterceptor implements IInterceptor {
	@Override
	public Object proceed(Object object, Method method, Object[] args) throws Exception {
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
