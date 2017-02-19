package fr.isima.ejb.transaction;

import fr.isima.ejb.injection.IInterceptor;

public interface ITransaction {
	void begin();
	void commit();
	void rollback();
	void awake();
	void sleep();
	void setCaller(IInterceptor t);
	boolean isCaller(IInterceptor t);
}
