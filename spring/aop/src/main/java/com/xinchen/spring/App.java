package com.xinchen.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.start();

        latch.await(60, TimeUnit.SECONDS);

    }
}
