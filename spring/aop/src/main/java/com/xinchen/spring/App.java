package com.xinchen.spring;

import com.xinchen.spring.service.DemoService;
import com.xinchen.spring.service.ExceptionDemoService;
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


        DemoService demoService = context.getBean(DemoService.class);
        demoService.say();


        ExceptionDemoService exceptionDemoService = context.getBean(ExceptionDemoService.class);

        try {
            exceptionDemoService.throwError("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }


        latch.await(60, TimeUnit.SECONDS);

    }
}
