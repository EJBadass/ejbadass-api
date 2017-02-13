package fr.isima.ejb.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.service.IService;

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
		//TODO assertTrue(service instanceof GoodService);
		//TODO assertTrue(service.getJokerService() instanceof JokerService);
		assertEquals("Joker", service.getJokerService().giveMeAHand());
	}

}
