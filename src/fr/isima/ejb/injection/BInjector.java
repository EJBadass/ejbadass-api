package fr.isima.ejb.injection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;

import fr.isima.ejb.annotation.Behaviour;
import fr.isima.ejb.annotation.Inject;
import fr.isima.ejb.annotation.Prefered;
import fr.isima.ejb.annotation.Singleton;
import fr.isima.ejb.exception.MultipleImplementationFoundException;
import fr.isima.ejb.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejb.exception.NoImplementationFoundException;
import fr.isima.ejb.log.IInterceptor;

public class BInjector {

	private static HashMap<Class<?>, Object> singletons;
	private static BInvocationHandler invocationHandler;
	
	static {
		singletons = new HashMap<>();
		invocationHandler = new BInvocationHandler();
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
					value = applyBehaviour(field.getType());
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
	
	private static Object inject(Class<?> f) throws InstantiationException, IllegalAccessException, NoImplementationFoundException, MultipleImplementationFoundException, MultiplePreferedImplementationFoundException {
		Object res = null;
		Reflections reflections = new Reflections("fr.isima");
		
		if(f.isInterface()) {
			Set<?> possibilities = reflections.getSubTypesOf(f);
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
			res = instantiate(f);			
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
	
	private static Object applyBehaviour(Class<?> cls) {
		Object instance = null;
		Class<?>[] classes = null;

		for(Method m : cls.getMethods()) {
			for (Annotation a : m.getAnnotations()) {
				Behaviour[] ans = a.annotationType().getAnnotationsByType(Behaviour.class);
				classes = new Class<?>[ans.length];
				int i = 0;
				for(Behaviour b : ans) {
					classes[i++] = b.interceptor();
				}
			}
		}
		
		if(classes!=null) {
			for (Class<?> class1 : classes) {
				System.out.println("DEBUG " + class1.getTypeName());
			}
		}
		
		instance = Proxy.newProxyInstance(
				cls.getClassLoader(),
				new Class<?>[] {cls},
				invocationHandler
				);
		
		return instance;
	}
	
}
