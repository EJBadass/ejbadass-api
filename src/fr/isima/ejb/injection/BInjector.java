package fr.isima.ejb.injection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;

import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.annotation.Prefered;
import fr.isima.ejb.annotation.Singleton;
import fr.isima.ejb.exception.MultipleImplementationFoundException;
import fr.isima.ejb.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejb.exception.NoImplementationFoundException;
import fr.isima.ejb.log.IInterceptor;

public class BInjector {

	private static HashMap<Class<?>, Object> singletons;
	private static HashMap<Class<? extends IInterceptor>, Object> interceptors;
	
	static {
		singletons = new HashMap<>();
		interceptors = new HashMap<>();
	}

	public static void makeAllInjection(Object o) throws NoImplementationFoundException, MultipleImplementationFoundException, MultiplePreferedImplementationFoundException {
		Class<? extends Object> contextClass = o.getClass();
		Field[] fields = contextClass.getDeclaredFields();
		System.out.println("DEBUG - Passage dans makeAlInjection");
		for (Field field : fields) {
			System.out.println(field.getClass() + " " + field.getType() + " " + field.getName());
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
				res = instantiate(firstPrefered);
			} else if(nbPreferred==0) {
				if(possibilities.size()==1) {
					res = instantiate(((Class<?>)possibilities.iterator().next()));
				} else if(possibilities.size()==0) {
					throw new NoImplementationFoundException();
				} else {
					throw new MultipleImplementationFoundException();
				}
			} else {
				throw new MultiplePreferedImplementationFoundException();
			}
		} else {
			res = instantiate(f.getType());			
		}
		
		return res;
	}
	
	private static Object instantiate(Class<?> cls) throws InstantiationException, IllegalAccessException, NoImplementationFoundException, MultipleImplementationFoundException, MultiplePreferedImplementationFoundException {
		Object instance = null;
		
		if(cls.isAnnotationPresent(Singleton.class)) {
			instance = getSingleton(cls);
		} else {
			instance = cls.newInstance();
			makeAllInjection(instance);
		}
		
		return instance;
	}
	
	private static Object getSingleton(Class<?> cls) throws InstantiationException, IllegalAccessException, NoImplementationFoundException, MultipleImplementationFoundException, MultiplePreferedImplementationFoundException {
		if (!singletons.containsKey(cls)) {
			Object instance = cls.newInstance();
			singletons.put(cls, instance);
			makeAllInjection(instance);
		}
		return singletons.get(cls);
	}
	
}
