package fr.isima.ejb.test;

import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.exception.MultipleImplementationFoundException;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.stub.interfaces.IMultiService;

public class TestMultiClassLoading {
	
	@Inject
	IMultiService service;
	
	@Test(expected=MultipleImplementationFoundException.class)
	public void testClassLoader() throws Exception {
		BInjector.makeAllInjection(this);
	}

}
