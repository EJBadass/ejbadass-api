package fr.isima.ejb.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.injection.BInvocationHandler;
import fr.isima.ejb.service.GoodService;
import fr.isima.ejb.service.IService;

public class TestInjection {
	
	@Inject
	public IService service;
	
	@Before
	public void prepare() throws Exception {
		BInjector.makeAllInjection(this);
	}

	@Test
	public void testFirstLevelInjection() {
		assertNotNull(service);
		assertTrue(Proxy.isProxyClass(service.getClass()));
		assertTrue(service instanceof Proxy);
		assertTrue(((BInvocationHandler)Proxy.getInvocationHandler(service)).getInstance()==null);
		assertEquals("GOOD", service.giveMeAHand());
		assertTrue(((BInvocationHandler)Proxy.getInvocationHandler(service)).getInstance() instanceof GoodService);
	}

}
