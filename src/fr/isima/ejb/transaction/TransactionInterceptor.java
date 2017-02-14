package fr.isima.ejb.transaction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.IInterceptor;

public class TransactionInterceptor implements IInterceptor {
	
	@Inject
	ITransaction transaction;
	@Inject
	ITransactionManager transactionManager;
	
	private IInterceptor next;

	@Override
	public Object proceed(Object object, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object result = null;
		
		transaction.begin();
		
		try {
			result = next().proceed(object, method, args);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}
		
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
