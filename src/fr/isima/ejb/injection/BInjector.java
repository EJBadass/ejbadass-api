package fr.isima.ejb.injection;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.annotation.Prefered;
import fr.isima.ejb.exception.MultipleImplementationFoundException;
import fr.isima.ejb.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejb.exception.NoImplementationFoundException;

public class BInjector {

	public static void makeAllInjection(Object o) throws NoImplementationFoundException, MultipleImplementationFoundException, MultiplePreferedImplementationFoundException {
		Class<? extends Object> contextClass = o.getClass();
		Field[] fields = contextClass.getDeclaredFields();
		System.out.println("DEBUG - Passage dans makeAlInjection");
		for (Field field : fields) {
			System.out.println(field.getClass() + " " + field.getName());
			if (field.isAnnotationPresent(Inject.class)) {
				try {
					Object value;
					value = inject(field);
					field.setAccessible(true);
					field.set(o, value);
				} catch (InstantiationException e) {
					e.printStackTrace();
					throw new NoImplementationFoundException();
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
	
	private static Object inject(Field f) throws InstantiationException, IllegalAccessException, NoImplementationFoundException, MultipleImplementationFoundException, MultiplePreferedImplementationFoundException {
		Object res = null;
		Reflections reflections = new Reflections("fr.isima");
		
		if(f.getType().isInterface()) {
			Set<?> possibilities = reflections.getSubTypesOf(f.getType());
			int nbPreferred = 0;
			Iterator<?> iterator = possibilities.iterator();
			Class<?> firstPrefered = null;
			while(iterator.hasNext()) {
				Class<?> curClass = ((Class<?>)iterator.next());
				if(curClass.isAnnotationPresent(Prefered.class)) {
					nbPreferred++;
					if(nbPreferred==1) {
						firstPrefered = curClass;
					}
				}
			}
			if(nbPreferred==1) {
				res = firstPrefered.newInstance();
				makeAllInjection(res);
			} else if(nbPreferred==0) {
				if(possibilities.size()==1) {
					res = possibilities.iterator().next();
					makeAllInjection(res);
				} else if(possibilities.size()==0) {
					throw new NoImplementationFoundException();
				} else {
					throw new MultipleImplementationFoundException();
				}
			} else {
				throw new MultiplePreferedImplementationFoundException();
			}
		} else {
			res = f.getType().newInstance();			
		}

		
		return res;
	}
	
}
