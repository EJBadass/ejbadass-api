package fr.isima.ejbadass.plugable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;

import fr.isima.ejbadass.annotation.Behaviour;

public class BBehaviourManager {

	public static HashSet<Class<?>> check(Class<?> cls, Method method) {
		HashSet<Class<?>> classes = new HashSet<>();
		for (Annotation a : cls.getDeclaredAnnotations()) {
			Behaviour[] ans = a.annotationType().getDeclaredAnnotationsByType(Behaviour.class);
			for(Behaviour b : ans) {
				classes.add(b.interceptor());
			}
		}
		for (Annotation a : method.getAnnotations()) {
			Behaviour[] ans = a.annotationType().getDeclaredAnnotationsByType(Behaviour.class);
			for(Behaviour b : ans) {
				classes.add(b.interceptor());
			}
		}
		return classes;		
	}
	
}
