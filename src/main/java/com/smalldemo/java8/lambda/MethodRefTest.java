package com.smalldemo.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <pre>
 * 方法引用: 若Lambda 体中的内容有方法已经实现了, 我们可以使用"方法引用"
 *      (可以理解为方法引用是Lambda表达式的另外一种表现形式)
 *
 * 主要有三种语法格式
 *
 *  对象::实例方法
 *
 *  类::静态方法
 *
 *  类::实例方法名
 *
 *  1. Lambda体中调用方法的参数列表与返回值类型, 要与函数式接口中的抽象方法的函数列表和返回值类型保持一致
 *
 *  2. 若Lambda参数列表中的第一参数是 实例方法的调用者, 而第二个参数时实例方法的参数时, 可以使用ClassName::method
 * </pre>
 */
public class MethodRefTest {

    /**
     * 对象::实例方法
     */
    @Test
    public void test1() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("abcdefg");
    }

    @Test
    public void test2() {
        Employee employee = new Employee();
        Supplier<Integer> supplier = employee::getAge;
        supplier.get();
    }

    /**
     * 类::静态方法
     */
    @Test
    public void test3() {
        Comparator<Integer> compare = Integer::compare;
    }

    /**
     * 类::实例方法名
     */
    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        BiPredicate<String, String> biPredicate = String::equals;
    }

    /**
     * 构造器引用
     */
    @Test
    public void tes5() {
        Supplier<Employee> sup = () -> new Employee();

        //构造器引用方式
        Supplier<Employee> sup2 = Employee::new;
        Employee employee = sup2.get();
        System.out.println(employee);
    }

    @Test
    public void test6() {
        Function<Integer, Employee> fun = (x) -> new Employee(x);

        Function<Integer, Employee> fun2 = Employee::new;
        Employee emp = fun2.apply(101);
        System.out.println(emp);
    }

    /**
     * 数组引用
     */
    @Test
    public void test7() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        String[] strs = fun.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> fun2 = String[]::new;
        String[] strs2 = fun2.apply(20);
        System.out.println(strs2.length);
    }
}
