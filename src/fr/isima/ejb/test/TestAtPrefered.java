package fr.isima.ejb.test;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.injection.BInvocationHandler;
import fr.isima.ejb.service.GoodService;
import fr.isima.ejb.service.IService;

public class TestAtPrefered {

	@Inject
	IService service;
	
	@Test
	public void testAtPreferedDoNotThrowException() throws Exception {
		BInjector.makeAllInjection(this);
		assertNotNull(service);
		assertNull(((BInvocationHandler)Proxy.getInvocationHandler(service)).getInstance());
		service.giveMeAHand();
		assertTrue(((BInvocationHandler)Proxy.getInvocationHandler(service)).getInstance() instanceof GoodService);
	}

}
