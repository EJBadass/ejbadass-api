package fr.isima.ejbadass.injection;

import fr.isima.ejbadass.annotation.Singleton;

public class BObjectFactory {
	
	public static Object createInstance(Class<?> cls) throws Exception {
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
