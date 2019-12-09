package com.smalldemo.java8.lambda.test;

/**
 * @author Jim
 */
@FunctionalInterface
public interface MyFunction2<T, R> {

    public R getValue(T t1, T t2);
}
