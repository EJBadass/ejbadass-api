package fr.isima.ejb.injection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface IInterceptor {
	public Object proceed(Object object, Method method, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	public IInterceptor next();
	public void setNext(IInterceptor next);
}
