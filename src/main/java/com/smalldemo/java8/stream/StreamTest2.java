package com.smalldemo.java8.stream;

import com.smalldemo.java8.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Jim
 */
public class StreamTest2 {

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.55),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    /**
     * 筛选与切片
     * <p>
     * filter   - 排除元素
     * limit    - 元素不超过给定数量
     * skip(n)  - 跳过元素, 返回一个扔掉了前n个元素的流, 若流中元素不足n个, 则返回一个空流
     * distinct - 筛选, 通过流所生成元素的hashCode()和equal()去除重复元素
     */
    @Test
    public void test1() {
        // 中间操作
        Stream<Employee> stream = employees.stream().filter((e) ->
        {
            System.out.println("Stream API的中间操作");
            return e.getAge() > 35;
        });

        // 终止操作: 一次性执行全部内容
        stream.forEach(System.out::println);
    }

    @Test
    public void test2() {
        employees.stream()
                .filter((e) -> {
                    System.out.println("短路");
                    return e.getSalary() > 5000;
                })
                .limit(2)
                .forEach(System.out::println);
    }


    @Test
    public void test3() {
        employees.stream()
                .filter((e) -> {
                    System.out.println("短路");
                    return e.getSalary() > 5000;
                })
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void test4() {
        employees.stream()
                .filter((e) -> {
                    System.out.println("短路");
                    return e.getSalary() > 5000;
                })
                .skip(2)
                .distinct()
                .forEach(System.out::println);
    }
}
