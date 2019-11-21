package com.smalldemo.concurrent;

/**
 * @author Jim
 * <p>
 * 1. 两个普通同步方法, 两个线程, 标准打印, 打印? //one two
 * 2. 新增Thread.sleep() 给getOne(), 打印? //one two
 * 3. 新增普通方法 getThree(), 打印? // three one two
 *
 *
 */
public class TestThread8Monitor {

    public static void main(String[] args) {
        Number number = new Number();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getTwo();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                number.getThree();
            }
        }).start();
    }
}

class Number {

    public synchronized void getOne() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("one");
    }

    public synchronized void getTwo() {
        System.out.println("two");
    }

    public void getThree() {
        System.out.println("three");
    }
}
