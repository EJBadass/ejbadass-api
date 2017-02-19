package fr.isima.ejbadass.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import fr.isima.ejbadass.plugable.IInterceptor;

@Retention(RetentionPolicy.RUNTIME)
public @interface Behaviour {
	Class<? extends IInterceptor> interceptor();
}