package fr.isima.ejb.transaction;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.IInterceptor;

public class TransactionInterceptor implements IInterceptor {

	@Inject
	ITransactionManager transactionManager;
	
	private IInterceptor next;

	@Override
	public Object proceed(Object object, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object result = null;
		boolean isRequired = ((Transactional)method.getDeclaredAnnotationsByType(Transactional.class)[0]).value() == TransactionType.REQUIRED;
		ITransaction transaction = transactionManager.getTransaction(this, isRequired);
		
		if(transaction.isCaller(this))
			transaction.begin();
		
		try {
			result = next().proceed(object, method, args);
			if(transaction.isCaller(this))
				transaction.commit();
		} catch (Exception e) {
			if(!transaction.isCaller(this))
				throw e;
			transaction.rollback();
		}
		
		if(transaction.isCaller(this))
			transactionManager.close();
		
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
