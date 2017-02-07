package fr.isima.ejb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.isima.ejb.log.LogInterceptor;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Behaviour(interceptor=LogInterceptor.class)
public @interface Log {

}
