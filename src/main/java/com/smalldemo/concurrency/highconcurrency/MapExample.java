package com.smalldemo.concurrency.highconcurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Jim
 */
@Slf4j
public class MapExample {

    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(clientTotal);
        for (int index = 0; index < clientTotal; index++) {
            final int threadNum = index;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    func(threadNum);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception:", e);
                }
            });
        }
        executorService.shutdown();
        log.info("size:{}", map.size());
    }

    public static void func(int threadNum) {
        map.put(threadNum, threadNum);
    }
}
