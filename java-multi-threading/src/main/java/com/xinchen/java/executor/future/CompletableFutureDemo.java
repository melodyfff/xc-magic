package com.xinchen.java.executor.future;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;

/**
 * CompletableFuture 简单例子
 *
 * <p>
 * CompletableFuture的静态方法主要有
 * ①. runAsync(Runnable runnable)                           使用ForkJoinPool.commonPool()作为它的线程池执行异步代码
 * ②. runAsync(Runnable runnable, Executor executor)        使用指定的thread pool执行异步代码
 * ③. supplyAsync(Supplier<U> supplier)                     使用ForkJoinPool.commonPool()作为它的线程池执行异步代码，异步操作有返回值
 * ④. supplyAsync(Supplier<U> supplier, Executor executor)  使用指定的thread pool执行异步代码，异步操作有返回值
 * ⑤. completedFuture(U value)                              Returns a new CompletableFuture that is already completed with the given value.
 * <p>
 * <p>
 * runAsync 和 supplyAsync 方法的区别是runAsync返回的CompletableFuture是没有返回值的
 * <p>
 * 比较常用的是CompletableFuture.completedFuture()
 *
 * </p>
 *
 * <p>
 * complete(T t)                            完成异步执行，并返回future的结果,调用complete(T t)会立即执行。如果future已经执行完毕能够返回结果，此时再调用complete(T t)则会无效
 * completeExceptionally(Throwable ex)      异步执行不正常的结束，抛出异常
 * </p>
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/8 23:54
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建线程池
        final ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("CompletableFutureDemo-pool-%d").build();
        ExecutorService executorService = Executors.newCachedThreadPool(factory);


        // 无返回任务
        Runnable nonReturnTask = () -> System.out.println(Thread.currentThread().getName() + " : CompletableFuture.runAsync() non return...");

        // 1. runAsync(Runnable runnable)
        CompletableFuture<Void> nonReturn = CompletableFuture.runAsync(nonReturnTask);
        nonReturn.get();

        // 2. runAsync(Runnable runnable, Executor executor)
        CompletableFuture<Void> nonReturnExe = CompletableFuture.runAsync(nonReturnTask, executorService);
        nonReturnExe.get();


        System.out.println("========================");


        // 返回字符串任务
        Supplier<String> returnTask = () -> Thread.currentThread().getName() + ": CompletableFuture.supplyAsync() return...";

        // 1. supplyAsync(Supplier<U> supplier)
        final CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(returnTask);
        System.out.println(stringCompletableFuture.get());

        // 2. supplyAsync(Supplier<U> supplier, Executor executor)
        final CompletableFuture<String> stringCompletableFuture1 = CompletableFuture.supplyAsync(returnTask, executorService);
        System.out.println(stringCompletableFuture1.get());


        executorService.shutdown();


        System.out.println("========================");

        // 返回已经处理完的CompletableFuture
        final CompletableFuture<String> stringCompletableFuture2 = CompletableFuture.completedFuture(Thread.currentThread().getName()+" : CompletableFuture.completedFuture()");
        // 使用complete(U u)添加默认值，判断是否已经执行完毕
        System.out.println(stringCompletableFuture.complete("test"));
        System.out.println(stringCompletableFuture2.get());
    }
}
