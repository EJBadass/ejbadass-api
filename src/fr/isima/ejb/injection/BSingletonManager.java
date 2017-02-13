package fr.isima.ejb.injection;

import java.util.HashMap;

import fr.isima.ejb.exception.MultipleImplementationFoundException;
import fr.isima.ejb.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejb.exception.NoImplementationFoundException;

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
