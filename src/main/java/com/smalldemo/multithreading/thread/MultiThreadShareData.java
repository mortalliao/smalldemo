package com.smalldemo.multithreading.thread;

/**
 * @author Jim
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
