package fr.isima.ejb.stub.interfaces;

import fr.isima.ejb.transaction.TransactionType;
import fr.isima.ejb.transaction.Transactional;

public interface ITransacService {

	@Transactional(value=TransactionType.REQUIRED)
	public void transactionalMethod(int i) throws Exception;
	@Transactional(value=TransactionType.REQUIRED)
	public void transactionalMethod2();
	@Transactional(value=TransactionType.REQUIRES_NEW)
	public void transactionalMethod3();
	@Transactional(value=TransactionType.REQUIRED)
	public void transactionalMethod4() throws Exception;

}