package fr.isima.ejb.service;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.annotation.Transactional;
import fr.isima.ejb.transaction.TransactionType;

public class TransacService {
	@Inject
	EntityManager em;
	
	@Inject
	TransacService service2;

	@Transactional(value=TransactionType.REQUIRES)
	public void transactionalMethod(int i) {
		
	}
	
	@Transactional(value=TransactionType.REQUIRES)
	public void transactionalMethod2() {
		service2.transactionalMethod3();
	}
	
	@Transactional(value=TransactionType.REQUIRES_NEW)
	public void transactionalMethod3() {
		
	}
	
	@Transactional(value=TransactionType.REQUIRES)
	public void transactionalMethod4() {
		service2.transactionalMethod(1);
	}

	public int getValue() {
		return ((Entity)em.find(Entity.class,"bob")).getValue();
	}
}
