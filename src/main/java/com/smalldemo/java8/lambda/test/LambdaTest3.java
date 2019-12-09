package com.smalldemo.java8.lambda.test;

import com.smalldemo.java8.lambda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Jim
 */
public class LambdaTest3 {

    List<Employee> emps = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.55),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    @Test
    public void test1() {
        Collections.sort(emps, (e1, e2) -> {
            if (e1.getAge().equals(e2.getAge())) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return -Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee emp : emps) {
            System.out.println(emp);
        }
    }

    /**
     * 需求: 用于处理字符串
     */
    public String strHandler(String str, MyFunction myFunction) {
        return myFunction.getValue(str);
    }

    @Test
    public void test2() {
        String trimStr = strHandler("  \t\t\t Hello world!    ", String::trim);
        System.out.println(trimStr);

        String upperCase = strHandler("abcdef", String::toUpperCase);
        System.out.println(upperCase);

        String substr = strHandler("hello world", str -> str.substring(2, 5));
        System.out.println(substr);
    }

    /**
     * 需求: 对于两个Long型数据进行处理
     */
    public void op(Long l1, Long l2, MyFunction2<Long, Long> mf) {
        System.out.println(mf.getValue(l1, l2));
    }

    @Test
    public void test3() {
        op(100L, 200L, (x, y) -> x + y);

        op(100L, 200L, (x, y) -> x * y);
    }
}
