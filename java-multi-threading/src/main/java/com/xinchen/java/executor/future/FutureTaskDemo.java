package com.xinchen.java.executor.future;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * FutureTask 的简单实用例子
 *
 * <p>
 * 两种方式提交FutureTask类型的任务
 * ①. ExecutorService创建线程池，通过submit提交
 * ②. new Thread(),直接提交
 * 注： 如果两种方式同时执行同一个 futureTask ，只会有一个成功抓取任务（和顺序有关）
 * </p>
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/8 22:57
 */
public class FutureTaskDemo {

    public static void main(String[] args){

        // TASK ： 随机返回一个值
        Callable<Integer> task = () -> {
            System.out.println(Thread.currentThread().getName() + " is running...");

            // 此处模拟处理过程需要的时间，
            // 1. 方便查看 get(long timeout, TimeUnit unit) 超时返回
            // 2. shutdownNow()抛出的 InterruptedException 异常
            Thread.sleep(2000L);

            // 返回一个随机数
            return new Random().nextInt();
        };


        // 方式一： 创建线程池，提交任务
        final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("FutureTaskDemo-pool-%d").build();
        ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);


        FutureTask<Integer> futureTask = new FutureTask<>(task);
        FutureTask<Integer> futureTask2 = new FutureTask<>(task);

        executorService.submit(futureTask);

        // 如果直接使用 shutdownNow()，不管线程任务是否执行完毕，都会直接停止，可能会抛出InterruptedException异常
        executorService.shutdown();


        // 方式二： 创建线程，提交任务
        final Thread thread = new Thread(futureTask2);
        thread.setName("Demo-thread-1");
        thread.start();


        try {
            // 异步，模拟可做其他事情
            Thread.sleep(2000L);

            // 如果还未计算完成，设置2秒后为超时时间，直接抛出异常 TimeoutException
            System.out.println("futureTask result is :" + futureTask.get(1, TimeUnit.SECONDS));
            System.out.println("futureTask2 result is :" + futureTask2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException ex) {
            ex.printStackTrace();
        }
    }

}
