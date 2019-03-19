package com.xinchen.java.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/19 23:09
 */
public class FixedThreadExecutorExample extends Thread {

    private final Lock lock = new ReentrantLock();

    volatile AtomicInteger count = new AtomicInteger(0);


    @Override
    public void run() {
        lock.lock();
        // print count++
        System.out.println(String.format("%s [%d]: %d",Thread.currentThread().getName(),System.currentTimeMillis(),count.updateAndGet((v) -> v = v+1)));
        lock.unlock();
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(100);
        final FixedThreadExecutorExample fixedThreadExecutorExample = new FixedThreadExecutorExample();
        for (int i=0;i<1000;i++){
            service.execute(fixedThreadExecutorExample);
        }

        service.shutdown();

        Thread.sleep(10000);

        // reference : https://hacpai.com/article/1488023925829
        if (service.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.println("Check ExecutorService status");
            service.shutdownNow();
        }

        System.out.println("Main thread exit.");

    }
}
