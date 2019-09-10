package com.xinchen.spring.targetsource;

import com.xinchen.spring.targetsource.detadata.BusinessService;
import org.springframework.aop.target.AbstractPoolingTargetSource;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.aop.target.PoolingConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * Spring为Commons Pool 2.2提供支持,它提供了一个相当有效的池实现{@link CommonsPool2TargetSource}
 *
 * 可以实例化 {@link AbstractPoolingTargetSource} 以支持其他池的API
 *
 * 详细配置可参考{@link swapper/spring-pool-swapper.xml}
 *
 * <pre>
 *     Commons Pool 1.5+ is also supported but is deprecated as of Spring Framework 4.2.
 * </pre>
 *
 * @author xinchen
 * @version 1.0
 * @date 10/09/2019 10:48
 */
public class PoolingTargetSourceDemo {

    static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("swapper/spring-pool-swapper.xml");



        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable task = () -> ((BusinessService) context.getBean("businessObject")).hello();

        for (int i = 0; i < 100000; i++) {
            executor.submit(task);
        }

        executor.shutdown();


        // 等待5S
        latch.await(5, TimeUnit.SECONDS);

        // 这里因为代理类有顾问poolConfigAdvisor调用AbstractPoolingTargetSource中的getPoolingConfigMixin()方法
        // new DefaultIntroductionAdvisor(dii, PoolingConfig.class)
        // 因此可以强制转为PoolingConfig
        PoolingConfig poolingConfig  = (PoolingConfig) context.getBean("businessObject");
        System.out.println("池中活跃数: "+poolingConfig.getActiveCount());
        System.out.println("池中空闲数: "+poolingConfig.getIdleCount());
        System.out.println("池中最大数: "+poolingConfig.getMaxSize());
        // 池中活跃数: 1
        // 池中空闲数: 7
        // 池中最大数: 25

    }
}
