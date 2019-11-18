package com.smalldemo.reflect;

/**
 * @author Jim
 */
public class ReflectTest {

    private String name;
    private Integer age;

    public ReflectTest() {

    }

    private ReflectTest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String callTest(String str) {
        System.out.println("Reflect Test calling ...");
        return "success";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    class InnerClass {

    }
}

class TestInterface1 {

}

class TestInterface2 {

}

class Father {

}

class Son extends Father {

}
