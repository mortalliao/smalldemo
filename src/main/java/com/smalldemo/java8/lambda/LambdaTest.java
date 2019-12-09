package com.smalldemo.java8.lambda;

import org.junit.Test;

import java.util.*;

/**
 * @author Jim
 */
public class LambdaTest {

    /**
     * 原来的匿名内部类
     */
    @Test
    public void test1() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    /**
     * Lambda表达式
     */
    @Test
    public void test2() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 9999.99),
            new Employee("李四", 38, 5555.55),
            new Employee("王五", 50, 6666.66),
            new Employee("赵六", 16, 3333.33),
            new Employee("田七", 8, 7777.77)
    );

    /**
     * 获取当前公司中员工年龄大于35的员工信息
     */
    public List<Employee> filterEmployees(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }
        return emps;
    }

    /**
     * 工资大于5000的员工信息
     */
    public List<Employee> filterEmployees2(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();

        for (Employee emp : list) {
            if (emp.getSalary() >= 5000) {
                emps.add(emp);
            }
        }

        return emps;
    }

    @Test
    public void test3() {
        List<Employee> list = filterEmployees(employees);
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> myPredicate) {
        List<Employee> emps = new ArrayList<>();

        for (Employee employee : list) {
            if (myPredicate.test(employee)) {
                emps.add(employee);
            }
        }

        return emps;
    }

    @Test
    public void test4() {
        // 优化方式一: 策略设计模式
        List<Employee> list = filterEmployee(this.employees, new FilterEmployeeByAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }

        System.out.println("------------------------");

        List<Employee> list1 = filterEmployee(this.employees, new FilterEmployeeBySalary());
        for (Employee employee : list1) {
            System.out.println(employee);
        }
    }

    @Test
    public void test5() {
        // 优化方式二: 匿名内部类
        List<Employee> list = filterEmployee(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 5000;
            }
        });

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    @Test
    public void test6() {
        // 优化方式三: Lambda表达式
        List<Employee> list = filterEmployee(this.employees, (employee) -> employee.getSalary() <= 5000);
        list.forEach(System.out::println);
    }

    @Test
    public void test7() {
        // 优化方式四: Stream API
        employees.stream()
                .filter(employee -> employee.getSalary() >= 5000)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("-----------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }

}
