package fr.isima.ejbadass.injection;

import java.util.HashMap;

import fr.isima.ejbadass.exception.MultipleImplementationFoundException;
import fr.isima.ejbadass.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejbadass.exception.NoImplementationFoundException;

public class BSingletonManager {

	private static HashMap<Class<?>, Object> singletons;
	
	static {
		singletons = new HashMap<>();
	}
	
	public static Object get(Class<?> cls) throws InstantiationException, IllegalAccessException, NoImplementationFoundException, MultipleImplementationFoundException, MultiplePreferedImplementationFoundException {
		if (!singletons.containsKey(cls)) {
			Object instance = cls.newInstance();
			BInjector.makeAllInjection(instance);
			singletons.put(cls, instance);
		}
		return singletons.get(cls);
	}

}
