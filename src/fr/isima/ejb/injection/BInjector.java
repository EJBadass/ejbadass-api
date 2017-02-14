package fr.isima.ejb.injection;

import java.lang.reflect.Field;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.exception.MultipleImplementationFoundException;
import fr.isima.ejb.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejb.exception.NoImplementationFoundException;

public class BInjector {

	public static void makeAllInjection(Object o) throws NoImplementationFoundException, MultipleImplementationFoundException, MultiplePreferedImplementationFoundException, InstantiationException {
		Class<? extends Object> contextClass = o.getClass();
		Field[] fields = contextClass.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Inject.class)) {
				try {
					Object value;
					value = BProxyFactory.get(field.getType());
					field.setAccessible(true);
					field.set(o, value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					throw new NoImplementationFoundException();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					throw new NoImplementationFoundException();
				}
			}
		}
	}
}
