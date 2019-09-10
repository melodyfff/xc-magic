package com.xinchen.spring.targetsource;

import com.xinchen.spring.targetsource.detadata.BusinessService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.AbstractPoolingTargetSource;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.aop.target.PoolingConfig;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

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
 *
 * <pre>
 *     Commons Pool 1.5+ is also supported but is deprecated as of Spring Framework 4.2.
 * </pre>
 *
 * @author xinchen
 * @version 1.0
 * @date 10/09/2019 10:48
 */
@Configuration
public class PoolingTargetSourceJavaConfigDemo {

    private static CountDownLatch latch = new CountDownLatch(1);


    /**
     * 初始目标对象(scope=prototype)
     * @return BusinessService
     */
    @Bean
    @Scope("prototype")
    public BusinessService businessObjectTarget(){
        return new BusinessService();
    }

    /**
     * CommonsPool2TargetSource池化对象
     *
     * 池化对象已经池的相关配置
     *
     * 默认采用common-pool2 ,也可通过实现{@link AbstractPoolingTargetSource}来实现其他的池
     *
     * @return CommonsPool2TargetSource
     */
    @Bean
    public CommonsPool2TargetSource poolTargetSource(){
        final CommonsPool2TargetSource commonsPool2TargetSource = new CommonsPool2TargetSource();
        commonsPool2TargetSource.setTargetBeanName("businessObjectTarget");
        // 这里如果最大空闲数设置为0,表现为,每次都是最新的对象
        commonsPool2TargetSource.setMaxIdle(0);
        commonsPool2TargetSource.setMaxSize(25);
        return commonsPool2TargetSource;
    }

    /**
     * 代理类
     * @return ProxyFactoryBean
     */
    @Bean
    public ProxyFactoryBean businessObject(){
        final ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        //开启cglib
        proxyFactoryBean.setProxyTargetClass(true);
        proxyFactoryBean.setTargetSource(poolTargetSource());
        // 此次advisor是否生效,可看是否抛出cannot be cast to org.springframework.aop.target.PoolingConfig
        // 因为加上advisor后代理类会去实现PoolingConfig接口,所以可以强制转换
        proxyFactoryBean.setInterceptorNames("poolConfigAdvisor");
        return proxyFactoryBean;
    }


    /**
     * advisor ,通过滴调用{@link AbstractPoolingTargetSource#getPoolingConfigMixin()}获取
     *
     * 采用{@link MethodInvokingFactoryBean}调用方法获取结果
     *
     * @return MethodInvokingFactoryBean
     */
    @Bean
    public MethodInvokingFactoryBean poolConfigAdvisor(){
        final MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setTargetObject(poolTargetSource());
        methodInvokingFactoryBean.setTargetMethod("getPoolingConfigMixin");
        return methodInvokingFactoryBean;
    }








    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(PoolingTargetSourceJavaConfigDemo.class);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        Runnable task = () -> ((BusinessService) context.getBean("businessObject")).hello();

        try {
            for (int i = 0; i < 1000; i++) {
                executor.submit(task);
            }
        }finally {
            executor.shutdown();
        }

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
