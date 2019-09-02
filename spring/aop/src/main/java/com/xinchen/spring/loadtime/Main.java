package com.xinchen.spring.loadtime;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 *
 * VM : -javaagent:spring-instrument.jar -javaagent:aspectjweaver-1.9.4.jar
 *
 * 参考: jvm agent动态加载 : https://www.cnblogs.com/CLAYJJ/p/7992064.html
 *
 * @author xinchen
 * @version 1.0
 * @date 29/08/2019 15:35
 */
public class Main {

    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(LoadTimeWeaverConfig.class);

        StubEntitlementCalculationService bean = ctx.getBean(StubEntitlementCalculationService.class);

        bean.calculateEntitlement();

        latch.await(10, TimeUnit.SECONDS);

        //StopWatch 'ProfilingAspect': running time (millis) = 2001
        //-----------------------------------------
        //ms     %     Task name
        //-----------------------------------------
        //02001  100%  calculateEntitlement
    }
}
