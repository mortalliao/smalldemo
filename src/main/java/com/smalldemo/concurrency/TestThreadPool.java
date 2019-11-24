package com.smalldemo.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Jim
 *
 * <pre>
 *
 *
 * 1. 线程池: 提供了一个线程队列, 队列中保存着所有等待状态的线程, 避免了创建与销毁额外开销, 提高了响应的速度
 *
 * 2. 线程池的体系结构:
 * java.util.concurrent.Executor : 负责线程的使用与调度的接口
 *          |-- ** ExexutorService 子接口: 线程池的主要接口
 *              |-- ThreadPoolExecutor 实现类
 *              |-- ScheduledExecutorService 子接口: 负责线程的调度
 *                  |-- ScheduledThreadExecutor : 继承ThreadPoolExecutor, 实现ScheduledExecutorService
 *
 * 3. 工具类: Executors
 * ExecutorService newFixedThreadPool() : 创建固定大小的线程池
 * ExecutorService newSingleThreadExecutor() : 创建单个线程池, 线程池中只有一个线程
 * ExecutorService newCachedThreadPool() : 缓存线程池, 线程池的数量不固定, 可以根据需求自动的变更数量
 *
 * ScheduledExecutorService newScheduledThreadPool() : 创建固定大小的线程, 可以延迟或定时的执行任务
 *
 * </pre>
 */
public class TestThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(5);

        List<Future<Integer>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<Integer> future = pool.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (int i = 0; i < 100; i++) {
                        sum += i;
                    }

                    return sum;
                }
            });

            list.add(future);
        }

        pool.shutdown();

        for (Future<Integer> future : list) {
            System.out.println(future.get());
        }

       /*
        // 1. 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);

        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo();

        // 2. 为线程池
        for (int i = 0; i < 10; i++) {
            pool.submit(threadPoolDemo);
        }

        // 3. 关闭线程池
        pool.shutdown();
        */

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
