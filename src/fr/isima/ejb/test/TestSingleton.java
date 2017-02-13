package fr.isima.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.service.IService;
import fr.isima.ejb.service.IService2;

public class TestSingleton {
	
	@Inject
	public IService goodServiceSingleton1;
	@Inject
	public IService goodServiceSingleton2;
	@Inject
	public IService2 jokerService1;
	@Inject
	public IService2 jokerService2;
	
	@Before
	public void prepare() throws Exception {
		BInjector.makeAllInjection(this);
	}
	
	@Test
	public void testAtSingleton() {
		assertNotNull(goodServiceSingleton1);
		assertNotNull(goodServiceSingleton2);
		//TODO assertTrue(goodServiceSingleton1 == goodServiceSingleton2);
	}
	
	@Test
	public void testNotSingleton() {
		assertNotNull(goodServiceSingleton1);
		assertNotNull(goodServiceSingleton2);
		assertTrue(jokerService1 != jokerService2);
	}

}
