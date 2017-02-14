package fr.isima.ejb.service;

import fr.isima.ejb.annotation.Transactional;
import fr.isima.ejb.transaction.TransactionType;

public interface ITransacService {

	@Transactional(value=TransactionType.REQUIRES)
	public void transactionalMethod(int i) throws Exception;
	@Transactional(value=TransactionType.REQUIRES)
	public void transactionalMethod2();
	@Transactional(value=TransactionType.REQUIRES_NEW)
	public void transactionalMethod3();
	@Transactional(value=TransactionType.REQUIRES)
	public void transactionalMethod4() throws Exception;

	int getValue();

}