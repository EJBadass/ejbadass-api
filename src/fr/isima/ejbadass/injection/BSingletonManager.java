package fr.isima.ejbadass.injection;

import java.util.HashMap;

public class BSingletonManager {

	private static HashMap<Class<?>, Object> singletons = new HashMap<>();
	
	public static Object get(Class<?> cls) throws Exception {
		
		if (!singletons.containsKey(cls)) {
			Object instance = cls.newInstance();
			BInjector.makeAllInjection(instance);
			singletons.put(cls, instance);
		}
		
		return singletons.get(cls);
	}

}
