package com.smalldemo.concurrent;

/**
 * @author Jim
 * <pre>
 * 内存可见性问题是: 当多个线程操作共享数据时, 彼此不可见
 *
 *
 *
 * 1. volatile 关键字: 当多个线程进行操作共享数据时, 可以保证内存中的数据可见
 * 相交与synchronized是一种较为轻量级的同步策略
 *
 * 注意:
 * 1. volatile不具备"互斥性"
 * 2. volatile不能保证变量的"原子性"
 * </pre>
 */
public class TestVolatile {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(threadDemo).start();

        while (true) {
            if (threadDemo.isFlag()) {
                System.out.println("----------------------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable {

    //    private boolean flag = false;
    private volatile boolean flag = false;

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;

        System.out.println("flag=" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}