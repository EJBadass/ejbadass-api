package fr.isima.ejb.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.isima.ejb.annotation.Behaviour;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Behaviour(interceptor=LogInterceptor.class)
public @interface Log {

}
