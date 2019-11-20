package com.smalldemo.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Jim
 * <pre>
 * 1. i++ 的原子性问题: i++ 的操作实际上分为三个步骤"读- 改- 写"
 *              int i = 10;
 *              i = i++; //10
 *
 *              int temp = 1;
 *              i = i+1;
 *              i = temp;
 *
 * 2. 原子变量: jdk1.5 后 java.util.concurrent.atomic 包下提供了常用的原子变量
 *              1. volatile 保证内存可见性
 *              2. CAS(Compare-And-Swap)算法保证数据的原子性
 *                  CAS 算法是硬件对于并发操作共享数据的支持
 *                          CAS 包含了三个操作数:
 *                          内存值 V
 *                          预估值 A
 *                          更新值 B
 *                          当且仅当 V == A 时, V = B, 否则, 将不做任何操作
 * </pre>
 */
public class TestAtomicDemo {

    public static void main(String[] args) {
        AtomicDemo atomicDemo = new AtomicDemo();

        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }
    }
}

class AtomicDemo implements Runnable {

//    private int serialNumber = 0;

    private AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ":" + getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }
}