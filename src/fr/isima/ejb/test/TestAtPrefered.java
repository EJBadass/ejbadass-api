package fr.isima.ejb.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.service.IService;

public class TestAtPrefered {

	@Inject
	IService service;
	
	@Test
	public void testAtPreferedDoNotThrowException() throws Exception {
		BInjector.makeAllInjection(this);
		assertNotNull(service);
		//TODO assertTrue(service instanceof GoodService);
	}

}
