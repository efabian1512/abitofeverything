package com.naifer.wigsshop.wigsshopping.generator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import org.hibernate.annotations.IdGeneratorType;

@IdGeneratorType(OrderIdGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD})
public @interface OrderId {

}
