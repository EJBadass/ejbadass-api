package fr.isima.ejbadass.injection;

import fr.isima.ejbadass.annotation.Singleton;
import fr.isima.ejbadass.exception.MultipleImplementationFoundException;
import fr.isima.ejbadass.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejbadass.exception.NoImplementationFoundException;

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
