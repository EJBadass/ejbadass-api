package fr.isima.ejb.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import fr.isima.ejb.service.IInterceptor;

@Retention(RetentionPolicy.RUNTIME)
public @interface Behaviour {

	Class<? extends IInterceptor> interceptor();

}
