package fr.isima.ejb.injection;

import java.util.Iterator;
import java.util.Set;

import org.reflections.Reflections;

import fr.isima.ejb.annotation.Prefered;
import fr.isima.ejb.exception.MultipleImplementationFoundException;
import fr.isima.ejb.exception.MultiplePreferedImplementationFoundException;
import fr.isima.ejb.exception.NoImplementationFoundException;

public class BClassFinder {
	
	public static Class<?> find(Class<?> f) throws InstantiationException, IllegalAccessException, NoImplementationFoundException, MultipleImplementationFoundException, MultiplePreferedImplementationFoundException {
		Class<?> res = null;
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
				res = firstPrefered;
			} else if(nbPreferred==0) {
				if(possibilities.size()==1) {
					res = ((Class<?>)possibilities.iterator().next());
				} else if(possibilities.size()==0) {
					throw new NoImplementationFoundException();
				} else {
					throw new MultipleImplementationFoundException();
				}
			} else {
				throw new MultiplePreferedImplementationFoundException();
			}
		} else {
			res = f;			
		}
		
		return res;
	}
	
}
