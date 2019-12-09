package com.smalldemo.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Jim
 * <pre>
 *
 * 1.
 * 左侧: Lambda 表达式的参数列表
 * 右侧: Lambda 表达式中所需执行的功能
 *
 * 无参, 无返回值
 *  () -> ...
 *
 * 一个参数, 无返回值
 *   (x) -> ...
 *
 * 两个参数以上, 有返回值
 *
 *
 * 2. Lambda 表达式需要"函数式接口"的支持
 * 函数式接口: 接口中只有一个抽象方法的接口, 称为函数式接口, 可以使用注解 @FunctionalInterface 修饰
 *              可以检查是否为函数式接口
 *
 * </pre>
 */
public class LambdaTest2 {

    @Test
    public void test1() {
        Runnable runnable = () -> System.out.println("Hello World");
        new Thread(runnable).start();
    }

    @Test
    public void test2() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("Hello World");
    }

    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test4() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

    @Test
    public void test5() {
        String[] strs;
//        strs = {"aaa", "bbb", "ccc"};

        List<String> list = new ArrayList<>();
    }

    /**
     * 对一个数进行运算
     */
    public void test6() {
        Integer operation = operation(100, (x) -> x * x);
        System.out.println(operation);

        System.out.println(operation(200, x -> x + 200));
    }

    public Integer operation(Integer num, MyFun mf) {
        return mf.getValue(num);
    }
}
