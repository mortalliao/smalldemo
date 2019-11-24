package com.smalldemo.multithreading.thread;

/**
 * @author Jim
 *
 * <pre>
 *
 *  多个线程访问共享对象和数据的方式
 *
 *     如果每个线程执行的代码不同, 需要使用不同Runable对象
 *
 *     1. 将共享对象封装在一个对象中, 然后将这个对象逐一传递给各个Runable对象.
 *        每个线程对共享数据的操作方法也分配到那个对象身上去完成, 这样容易实现针对该数据进行的各个操作的互斥和通信
 *
 *     2. 将这些Runable对象作为某一个类中的内部类, 共享数据作为这个外部类中的成员变量, 每个线程对共享数据的操作方法也分配给外部类,
 *        以便实现对共享数据进行的各个操作的互斥和通信, 作为内部类的各个Runable对象调用外部类的这些方法
 *
 *     总之, 要同步互斥的几段代码最好是分别放在几个独立的方法中, 这些方法再放在同一个类中, 这样比较容易实现它们之间的同步互斥和通信
 *
 * </pre>
 */
public class MultiThreadShareData2 {

    public static void main(String[] args) {
        final ShareData2 data2 = new ShareData2();

        new Thread(() -> {
            data2.decrement();
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                data2.increment();
            }
        }).start();

        final ShareData1 data1 = new ShareData1();
        new Thread(new MyRunable1(data1)).start();
        new Thread(new MyRunable2(data1)).start();

    }

}

class ShareData2 {

    private int count = 100;

    private int j = 0;

    public synchronized void increment() {
        j++;
    }

    public synchronized void decrement() {
        j--;
    }

}

class MyRunable1 implements Runnable {
    private ShareData1 data1;

    public MyRunable1(ShareData1 data1) {
        this.data1 = data1;
    }

    @Override
    public void run() {
        data1.decrement();
    }
}

class MyRunable2 implements Runnable {
    private ShareData1 data1;

    public MyRunable2(ShareData1 data1) {
        this.data1 = data1;
    }

    @Override
    public void run() {
        data1.increment();
    }
}