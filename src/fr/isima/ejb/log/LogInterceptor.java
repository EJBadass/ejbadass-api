package fr.isima.ejb.log;

import java.lang.reflect.Method;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.IInterceptor;

public class LogInterceptor implements IInterceptor {

	@Inject
	ILogger logger;
	
	@Override
	public void before(Object object, Method method, Object[] args) {
		logger.log(method.toString());
	}

	@Override
	public void after(Object object, Method method, Object[] args) {
		logger.log(method.toString());
	}

}
