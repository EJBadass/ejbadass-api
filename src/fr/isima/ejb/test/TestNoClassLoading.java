package fr.isima.ejb.test;

import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.exception.NoImplementationFoundException;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.stub.interfaces.INoService;

public class TestNoClassLoading {
	
	@Inject
	INoService service;
	
	@Test(expected=NoImplementationFoundException.class)
	public void testClassLoader() throws Exception {
		BInjector.makeAllInjection(this);
	}

}