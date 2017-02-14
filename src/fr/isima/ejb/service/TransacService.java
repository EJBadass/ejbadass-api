package fr.isima.ejb.service;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.annotation.Transactional;
import fr.isima.ejb.transaction.TransactionType;

public class TransacService implements ITransacService {
	@Inject
	IEntityManager em;
	
	@Inject
	ITransacService service2;

	/* (non-Javadoc)
	 * @see fr.isima.ejb.service.ITransacService#transactionalMethod(int)
	 */
	@Override
	@Transactional(value=TransactionType.REQUIRES)
	public void transactionalMethod(int i) {
		
	}
	
	/* (non-Javadoc)
	 * @see fr.isima.ejb.service.ITransacService#transactionalMethod2()
	 */
	@Override
	@Transactional(value=TransactionType.REQUIRES)
	public void transactionalMethod2() {
		service2.transactionalMethod3();
	}
	
	/* (non-Javadoc)
	 * @see fr.isima.ejb.service.ITransacService#transactionalMethod3()
	 */
	@Override
	@Transactional(value=TransactionType.REQUIRES_NEW)
	public void transactionalMethod3() {
		
	}
	
	/* (non-Javadoc)
	 * @see fr.isima.ejb.service.ITransacService#transactionalMethod4()
	 */
	@Override
	@Transactional(value=TransactionType.REQUIRES)
	public void transactionalMethod4() {
		service2.transactionalMethod(1);
	}

	/* (non-Javadoc)
	 * @see fr.isima.ejb.service.ITransacService#getValue()
	 */
	@Override
	public int getValue() {
		return ((Entity)em.find(Entity.class,"bob")).getValue();
	}
}
