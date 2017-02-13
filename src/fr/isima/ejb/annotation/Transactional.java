package fr.isima.ejb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import fr.isima.ejb.transaction.TransactionInterceptor;
import fr.isima.ejb.transaction.TransactionType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Behaviour(interceptor=TransactionInterceptor.class)
public @interface Transactional {

	TransactionType value();

}
