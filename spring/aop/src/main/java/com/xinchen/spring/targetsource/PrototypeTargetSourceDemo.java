package com.xinchen.spring.targetsource;

import com.xinchen.spring.targetsource.detadata.BusinessService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * Prototype Target Sources Demo
 *
 * 在这种情况下，将在每次方法调用时创建目标的新实例,和将池的空闲值设置为0一样
 *
 * <pre>
 * 官方建议:
 *
 *    虽然在现代JVM中创建新对象的成本并不高，但连接新对象（满足其IoC依赖性）的成本可能更高
 *
 *    因此，如果没有充分的理由，就不应该使用这种方法
 * </pre>
 *
 *
 *
 * @author xinchen
 * @version 1.0
 * @date 10/09/2019 12:51
 */
public class PrototypeTargetSourceDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("swapper/spring-prototype-swapper.xml");

        final ExecutorService executorService = Executors.newCachedThreadPool();

        Runnable task = ()-> ((BusinessService)context.getBean("businessObject")).hello();

        try {
            for (int i = 0; i <10000 ; i++) {
                executorService.submit(task);
            }
        } finally {
            executorService.shutdown();
        }
    }
}
