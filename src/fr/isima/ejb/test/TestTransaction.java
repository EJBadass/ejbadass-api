package fr.isima.ejb.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.service.ITransacService;
import fr.isima.ejb.transaction.Transaction;

public class TestTransaction {
	
	@Inject
	ITransacService service;
	
	@Before
	public void prepare() throws Exception {
		BInjector.makeAllInjection(this);
	}
	
	// begin & commit
	@Test
	public void testBeginCommit() {
		long 	b = Transaction.numberOfBegin,
				r = Transaction.numberOfCommit,
				c = Transaction.numberOfRollback;
		assertTrue(service != null);
		service.transactionalMethod(1);
		assertTrue(Transaction.numberOfBegin==b+1);
		assertTrue(Transaction.numberOfRollback==r);
		assertTrue(Transaction.numberOfCommit==c+1);
	}
	
	// begin & rollback
	@Test
	public void testBeginRollback() {
		long 	b = Transaction.numberOfBegin,
				r = Transaction.numberOfCommit,
				c = Transaction.numberOfRollback;
		assertTrue(service != null);
		service.transactionalMethod(0);
		assertTrue(Transaction.numberOfBegin==b+1);
		assertTrue(Transaction.numberOfRollback==r+1);
		assertTrue(Transaction.numberOfCommit==c);
	}
	
	// tester service imbriqués et seconde transaction
	@Test
	public void testRequiresRequires() {
		long 	b = Transaction.numberOfBegin,
				r = Transaction.numberOfCommit,
				c = Transaction.numberOfRollback;
		assertTrue(service != null);
		service.transactionalMethod4();
		assertTrue(Transaction.numberOfBegin==b+1);
		assertTrue(Transaction.numberOfRollback==r);
		assertTrue(Transaction.numberOfCommit==c+1);
	}
	

	// begin & rollback
	@Test
	public void testRequiresRequiresNew() {
		long 	b = Transaction.numberOfBegin,
				r = Transaction.numberOfCommit,
				c = Transaction.numberOfRollback;
		assertTrue(service != null);
		service.transactionalMethod2();
		assertTrue(Transaction.numberOfBegin==b+2);
		assertTrue(Transaction.numberOfRollback==r);
		assertTrue(Transaction.numberOfCommit==c+2);
	}

}


/* projet test */
/* projet conteneur */
/* plugins (interceptor...) */