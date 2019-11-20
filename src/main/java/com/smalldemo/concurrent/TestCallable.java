package com.smalldemo.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Jim
 * <pre>
 * 1. 创建执行线程的方式三: 实现Callable接口, 相较于实现Runable接口的方式, 方法可以有返回值, 并且可以抛出异常
 *
 * 2. 执行Callable方式, 需要FutureTask实现类的支持, 用于接收运算结果
 *      FutureTask 是 Future接口的实现类
 *
 * </pre>
 */
public class TestCallable {

    public static void main(String[] args) {
        ThreadCallableDemo threadCallableDemo = new ThreadCallableDemo();

        // 1. 执行Callable方式, 需要 FutureTask 实现类的支持, 用于接收运算结果
        FutureTask<Integer> futureTask = new FutureTask<>(threadCallableDemo);

        new Thread(futureTask).start();

        // 2. 接收线程运算后的结果
        try {
            // FutureTask 可用于 闭锁
            Integer sum = futureTask.get();
            System.out.println(sum);
            System.out.println("------------------");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}

class ThreadCallableDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;

        for (int i = 0; i <= 1000000; i++) {
            sum += i;
        }

        return sum;
    }
}
