package fr.isima.ejb.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.service.BadService;
import fr.isima.ejb.service.GoodService;
import fr.isima.ejb.service.IService;

public class TestInjection {
	
	@Inject
	public IService service;
	@Inject
	public GoodService goodServiceSingleton1;
	@Inject
	public GoodService goodServiceSingleton2;
	
	@Before
	public void prepare() throws Exception {
		BInjector.makeAllInjection(this);
	}

	@Test
	public void testFirstLevelInjection() {
		assertNotNull(service);
		assertTrue(service instanceof GoodService);
		assertEquals("GOOD", service.giveMeAHand());
	}

	@Test
	public void testSecondLevelInjection() {
		assertNotNull(service);
		assertTrue(service instanceof GoodService);
		assertTrue(service.getJokerService() instanceof BadService);
		assertEquals("BAD", service.getJokerService().giveMeAHand());
	}
	
	@Test
	public void testAtSingleton() {
		assertNotNull(goodServiceSingleton1);
		assertNotNull(goodServiceSingleton2);
		assertTrue(goodServiceSingleton1 == goodServiceSingleton2);
	}

}
