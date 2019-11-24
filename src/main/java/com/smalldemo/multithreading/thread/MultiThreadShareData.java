package com.smalldemo.multithreading.thread;

/**
 * @author Jim
 *
 * <pre>
 *
 *  多个线程访问共享对象和数据的方式
 *
 *     如果每个线程执行的代码相同, 可以使用同一个Runable对象, 这个Runable对象中有那个共享数据
 *
 *     案例: 只卖票
 *
 * </pre>
 *
 *
 */
public class MultiThreadShareData {

    public static void main(String[] args) {
        ShareData1 data1 = new ShareData1();

        new Thread(data1).start();
        new Thread(data1).start();
    }

}

class ShareData1 implements Runnable {

    private int count = 100;

    @Override
    public void run() {
        while (true) {
            count--;
        }
    }

    private int j = 0;

    public synchronized void increment() {
        j++;
    }

    public synchronized void decrement() {
        j--;
    }

}
