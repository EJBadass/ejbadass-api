package fr.isima.ejbadass.plugable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;

import fr.isima.ejbadass.annotation.Behaviour;

public class BBehaviourManager {

	public static HashSet<Class<?>> check(Class<?> cls, Method method) throws Exception {
		
		HashSet<Class<?>> classes = new HashSet<>();
		
		for (Annotation a : cls.getDeclaredAnnotations())
			for(Behaviour b : a.annotationType().getAnnotationsByType(Behaviour.class))
				classes.add(b.interceptor());
		
		for (Annotation a : cls.getMethod(method.getName(), method.getParameterTypes()).getDeclaredAnnotations())
			for(Behaviour b : a.annotationType().getDeclaredAnnotationsByType(Behaviour.class))
				classes.add(b.interceptor());
		
		return classes;	
		
	}
	
}
