package com.smalldemo.concurrent;

/**
 * @author Jim
 */
public class TestThreadPool {

    public static void main(String[] args) {
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();

        new Thread(threadPoolDemo).start();
        new Thread(threadPoolDemo).start();
        
    }
}

class ThreadPoolDemo implements Runnable {

    private int i = 0;

    @Override
    public void run() {
        while (i <= 100) {
            System.out.println(Thread.currentThread().getName() + ":" + i++);
        }
    }
}
