package fr.isima.ejbadass.injection;

import java.lang.reflect.Proxy;

import fr.isima.ejbadass.exception.MultipleImplementationFoundException;
import fr.isima.ejbadass.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejbadass.exception.NoImplementationFoundException;
import fr.isima.ejbadass.plugable.BInvocationHandler;

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
