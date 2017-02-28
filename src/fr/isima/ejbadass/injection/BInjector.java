package fr.isima.ejbadass.injection;

import java.lang.reflect.Field;

import fr.isima.ejbadass.annotation.Inject;

public class BInjector {
	
	public static void makeAllInjection(Object o) throws Exception {
		
		Class<? extends Object> contextClass = o.getClass();
		Field[] fields = contextClass.getDeclaredFields();
		
		for (Field field : fields)
			if (field.isAnnotationPresent(Inject.class)) {
				Object value = BProxyFactory.get(field.getType());
				field.setAccessible(true);
				field.set(o, value);
			}
		
	}
	
}
