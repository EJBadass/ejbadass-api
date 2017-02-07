package fr.isima.ejb.service;

import java.lang.reflect.Method;

import fr.isima.ejb.annotation.Inject;

public class LogInterceptor implements IInterceptor {

	@Inject
	Logger logger;
	
	@Override
	public void before(Object object, Method method, Object... params) {
		logger.log(method.toString());
	}

	@Override
	public void after(Object object, Method method, Object... params) {
		// TODO Auto-generated method stub
	}

}
