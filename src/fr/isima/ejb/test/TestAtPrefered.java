package fr.isima.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.service.GoodService;
import fr.isima.ejb.service.IService;

public class TestAtPrefered {

	@Inject
	IService service;
	
	@Test
	public void testAtPreferedDoNotThrowException() throws Exception {
		BInjector.makeAllInjection(this);
		assertNotNull(service);
		assertTrue(service instanceof GoodService);
	}

}
