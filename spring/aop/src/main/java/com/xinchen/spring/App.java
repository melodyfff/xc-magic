package com.xinchen.spring;

import com.xinchen.spring.service.AnnotationService;
import com.xinchen.spring.service.DemoService;
import com.xinchen.spring.service.ExceptionDemoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * Aspect Running Sequence:
 * Success      : @Around -> @Before -> 执行方法 ->  @Around -> @After -> @AfterReturning
 * fail(error)  : @Around -> @Before -> 方法报错 ->  @After  -> @AfterThrowing
 *
 * Hello world!
 */
class App {
    static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.start();


        DemoService demoService = context.getBean(DemoService.class);
        demoService.say();
        demoService.say("world!");
        demoService.say("Hello"," World.");


        AnnotationService annotationService = context.getBean(AnnotationService.class);
        annotationService.say();


        ExceptionDemoService exceptionDemoService = context.getBean(ExceptionDemoService.class);
        try {
            exceptionDemoService.throwError("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }


        latch.await(60, TimeUnit.SECONDS);

    }
}
