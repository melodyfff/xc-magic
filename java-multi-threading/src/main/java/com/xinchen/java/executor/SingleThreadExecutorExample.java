package com.xinchen.java.executor;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/19 22:43
 */
public class SingleThreadExecutorExample extends Thread {

    private final Random random = ThreadLocalRandom.current();



    private final AtomicInteger count = new AtomicInteger(0);

    void randomWait() {
        try {
            sleep((long) (3000 * random.nextDouble()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        for (;;) {
            System.out.println(Thread.currentThread().getName() + ": " + count.getAndUpdate((v) -> v = v + 1));
            // 当计数器达到100时退出
            if (100 == count.get()) {
                break;
            }
        }

        System.out.println(Thread.currentThread().getName() + " : done.");
    }

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService service = Executors.newSingleThreadExecutor();

        service.execute(new SingleThreadExecutorExample());
        Thread.sleep(3000);

        System.out.println(">>>>>>>>>");
        service.execute(new SingleThreadExecutorExample());
        Thread.sleep(3000);

        System.out.println(">>>>>>>>>");
        service.submit(new SingleThreadExecutorExample());
        Thread.sleep(3000);

        // close SingleThreadExecutor
        service.shutdown();

        System.out.println("Main Thead exit.");
    }
}
