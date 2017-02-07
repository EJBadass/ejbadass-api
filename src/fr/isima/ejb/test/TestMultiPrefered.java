package fr.isima.ejb.test;

import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.service.IMultiPreferedService;

public class TestMultiPrefered {

	@Inject
	IMultiPreferedService service;

	@Test(expected= MultiplePreferedImplementationFoundException.class)
	public void testAtPreferedDoNotKnowWhichIsYourPrefered() throws Exception {
		BInjector.makeAllInjection(this);
	}

}
