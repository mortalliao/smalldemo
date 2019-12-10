package com.smalldemo.java8.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Jim
 *
 * <pre>
 * Java8 内置的四大核心函数式接口
 *
 *
 * Consumer<T> : 消费型接口
 *      void accept(T t);
 *
 *
 * Supplier<T> : 供给型接口
 *      T get();
 *
 *
 * Function<T, R> : 函数型接口
 *      R apply(T t);
 *
 *
 * Predicate<T> : 断言型接口
 *      boolean test(T t);
 *
 *
 *
 * BiFunction<T, U, R>
 *     参数 T, U  返回 R
 *
 *
 * UnaryOperator<T>
 *     参数 T     返回 T
 *
 * BinaryOperator<T>
 *     参数 T, T  返回 T
 *
 * BiConsumer<T, U>
 *     参数 T, U  返回 void
 *
 * ToIntFunction<T>
 * ToLongFunction<T>
 * ToDoubleFunction<T>
 *
 * IntFunction<R>
 * LongFunction<R>
 * DoubleFunction<R>
 *
 * </pre>
 */
public class LambdaTest {

    /**
     * Consumer<T> 消费型接口
     */
    @Test
    public void test1() {
        happy(10000, m -> System.out.println("消费" + m + "元"));
    }

    public void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    /**
     * Supplier<T> 供给型接口
     */
    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));

        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    public List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Integer n = supplier.get();
            list.add(n);
        }

        return list;
    }

    /**
     * Function<T, R> 函数型接口
     */
    @Test
    public void test3() {
        String newStr = strHandler(" \t\t\t Hello World!   ", str -> str.trim());
        System.out.println(newStr);

        String subStr = strHandler("   Hello World!   ", str -> str.substring(2, 5));
        System.out.println(subStr);
    }

    public String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    /**
     * Predicate<T> 断言型接口
     */
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "world", "Lambda", "www");
        List<String> strList = filterStr(list, str -> str.length() > 3);

        for (String str : strList) {
            System.out.println(strList);
        }
    }

    public List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strList = new ArrayList<>();

        for (String str : list) {
            // 将满足条件的字符串, 放入集合中
            if (predicate.test(str)) {
                strList.add(str);
            }
        }

        return strList;
    }
}
