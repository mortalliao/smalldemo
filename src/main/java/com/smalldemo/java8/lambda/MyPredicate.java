package com.smalldemo.java8.lambda;

/**
 * @author Jim
 */
@FunctionalInterface
public interface MyPredicate<T> {

    public boolean test(T t);
}
