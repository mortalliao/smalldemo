package com.smalldemo.multithreading.thread;

import java.util.Random;

/**
 * @author Jim
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadData = new ThreadLocal<>();
    private static ThreadLocal<MyThreadScopeData> myThreadScopeDataThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data : " + data);
                    threadData.set(data);

//                    MyThreadScopeData myData = new MyThreadScopeData();
//                    myData.setName("name" + data);
//                    myData.setAge(data);
//                    myThreadScopeDataThreadLocal.set(myData);

                    MyThreadScopeData.getThreadInstance().setName("name" + data);
                    MyThreadScopeData.getThreadInstance().setAge(data);

                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public void get() {
            int data = threadData.get();
            System.out.println("A from" + Thread.currentThread().getName() + " get data : " + data);

//            MyThreadScopeData myData = myThreadScopeDataThreadLocal.get();
//            System.out.println("A from " + Thread.currentThread().getName() + " getMyData: " + myData.getName() + "," + myData.getAge());

            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("A from " + Thread.currentThread().getName() + " getMyData: " + myData.getName() + "," + myData.getAge());

        }
    }

    static class B {
        public void get() {
            int data = threadData.get();
            System.out.println("B from" + Thread.currentThread().getName() + " get data : " + data);

            MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
            System.out.println("B from " + Thread.currentThread().getName() + " getMyData: " + myData.getName() + "," + myData.getAge());
        }
    }
}

class MyThreadScopeData {

    private MyThreadScopeData() {
    }

    public static /*synchronized*/ MyThreadScopeData getThreadInstance() {
        MyThreadScopeData instance = map.get();
        if (instance == null) {
            instance = new MyThreadScopeData();
            map.set(instance);
        }
        return instance;
    }

    //    private static MyThreadScopeData instance = new MyThreadScopeData();
    private static MyThreadScopeData instance = null;

    private static ThreadLocal<MyThreadScopeData> map = new ThreadLocal<>();

    private String name;
    private Integer age;

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
}
