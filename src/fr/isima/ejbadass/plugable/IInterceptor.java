package fr.isima.ejbadass.plugable;

import java.lang.reflect.Method;

public interface IInterceptor {
	public Object proceed(Object object, Method method, Object[] args) throws Exception;
	public IInterceptor next();
	public void setNext(IInterceptor next);
}
