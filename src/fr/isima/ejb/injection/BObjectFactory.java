package fr.isima.ejb.injection;

import fr.isima.ejb.annotation.Singleton;
import fr.isima.ejb.exception.MultipleImplementationFoundException;
import fr.isima.ejb.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejb.exception.NoImplementationFoundException;

public class BObjectFactory {
	
	public static Object createInstance(Class<?> cls) throws InstantiationException, IllegalAccessException, NoImplementationFoundException, MultipleImplementationFoundException, MultiplePreferedImplementationFoundException {
		Object instance = null;
		
		if(cls.isAnnotationPresent(Singleton.class)) {
			instance = BSingletonManager.get(cls);
		} else {
			instance = cls.newInstance();
			BInjector.makeAllInjection(instance);
		}
		
		return instance;
	}
	
}
