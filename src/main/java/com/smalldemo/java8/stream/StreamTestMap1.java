package com.smalldemo.java8.stream;

import com.smalldemo.java8.lambda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * <pre>
 *
 *     映射
 *     map - 接收Lambda, 将元素转换成其他形式或提取信息, 接收一个函数作为参数, 该函数会被应用到每个元素上, 并将其映射成一个新的元素
 *     flatMap - 接收一个函数作为参数, 将流中的每个值都换成另一个流, 然后把所有的流连接成一个流
 *
 * </pre>
 */
public class StreamTestMap1 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.55),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    @Test
    public void test1() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");

        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        System.out.println("------------------------");

        Stream<Stream<Character>> stream = list.stream()
                .map(StreamTestMap1::filterCharacter); // {{a,a,a}, {b,b,b}}

        stream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });

        System.out.println("------------------------");

        Stream<Character> sm = list.stream()
                .flatMap(StreamTestMap1::filterCharacter); // {a,a,a,b,b,b}

        sm.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }
}
