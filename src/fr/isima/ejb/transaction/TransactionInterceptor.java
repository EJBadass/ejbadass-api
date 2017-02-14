package fr.isima.ejb.transaction;

import java.lang.reflect.Method;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.IInterceptor;

public class TransactionInterceptor implements IInterceptor {
	
	@Inject
	ITransaction transaction;

	@Override
	public void before(Object object, Method method, Object[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void after(Object object, Method method, Object[] args) {
		// TODO Auto-generated method stub

	}

}
