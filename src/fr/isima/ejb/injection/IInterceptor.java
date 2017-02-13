package fr.isima.ejb.injection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public interface IInterceptor extends InvocationHandler {
	public void before(Object object, Method method, Object[] args);
	public void after(Object object, Method method, Object[] args);
}
