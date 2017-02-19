package fr.isima.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.injection.BInjector;
import fr.isima.ejb.injection.BInvocationHandler;
import fr.isima.ejb.stub.interfaces.IService;
import fr.isima.ejb.stub.interfaces.IService2;

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
		assertTrue(goodServiceSingleton1 instanceof Proxy);
		assertTrue(((BInvocationHandler)Proxy.getInvocationHandler(goodServiceSingleton1)).getInstance()==null);
		assertTrue(goodServiceSingleton2 instanceof Proxy);
		assertTrue(((BInvocationHandler)Proxy.getInvocationHandler(goodServiceSingleton2)).getInstance()==null);
		assertTrue(((BInvocationHandler)Proxy.getInvocationHandler(goodServiceSingleton1)).getInstance() 
					== ((BInvocationHandler)Proxy.getInvocationHandler(goodServiceSingleton2)).getInstance());
	}
	
	@Test
	public void testNotSingleton() {
		assertNotNull(goodServiceSingleton1);
		assertNotNull(goodServiceSingleton2);
		assertTrue(jokerService1 != jokerService2);
	}

}
