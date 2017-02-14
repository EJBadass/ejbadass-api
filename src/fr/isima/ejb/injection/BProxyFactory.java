package fr.isima.ejb.injection;

import java.lang.reflect.Proxy;

import fr.isima.ejb.exception.MultipleImplementationFoundException;
import fr.isima.ejb.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejb.exception.NoImplementationFoundException;

public class BProxyFactory {

	public static Object get(Class<?> iface) throws InstantiationException, IllegalAccessException, NoImplementationFoundException, MultipleImplementationFoundException, MultiplePreferedImplementationFoundException {
		Object instance = null;		
		if(BClassFinder.find(iface)!=null) {
			instance = Proxy.newProxyInstance(
				iface.getClassLoader(),
				new Class<?>[] {iface},
				new BInvocationHandler()
			);}
		return instance;
	}

}
