package fr.isima.ejb.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.injection.BInvocationHandler;
import fr.isima.ejb.service.GoodService;
import fr.isima.ejb.service.IService;
import fr.isima.ejb.service.IService2;
import fr.isima.ejb.service.JokerService;

public class TestCascade {
	
	@Inject
	public IService service;
	
	@Before
	public void prepare() throws Exception {
		BInjector.makeAllInjection(this);
	}

	@Test
	public void testSecondLevelInjection() {
		assertNotNull(service);
		assertNull(((BInvocationHandler)Proxy.getInvocationHandler(service)).getInstance());
		IService2 joker = service.getJokerService();
		assertTrue(((BInvocationHandler)Proxy.getInvocationHandler(service)).getInstance() instanceof GoodService);
		assertEquals("Joker", service.getJokerService().giveMeAHand());
		assertTrue(((BInvocationHandler)Proxy.getInvocationHandler(joker)).getInstance() instanceof JokerService);
	}

}
