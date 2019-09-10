package com.xinchen.spring.targetsource;

import com.xinchen.spring.targetsource.detadata.BusinessService;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * ThreadLocal如果需要为每个传入请求创建一个对象（每个线程），目标源很有用
 * 这意味着如果使用线程池,线程是同一线程时,此对象是完全相同的对象(伪单例子)直到线程周期结束
 *
 * 如果使用{@link Executors#newCachedThreadPool()}则每次都会创建新的
 *
 * <pre>
 * 官方建议:
 *     ThreadLocal在多线程和多类加载器环境中错误地使用实例时，实例会出现严重问题（可能导致内存泄漏）。你应该总是考虑将threadlocal包装在其他类中，而不是直接使用它ThreadLocal自己（在包装类中除外）。
 *     此外，您应该始终记住正确设置和取消设置（后者只需要调用 ThreadLocal.set(null)）线程本地的资源。
 *     在任何情况下都应该进行取消，因为不取消它可能会导致有问题的行为。Spring的 ThreadLocal支持为您完成此操作，应始终考虑使用 ThreadLocal没有其他正确处理代码的实例。
 * </pre>
 *
 * @author xinchen
 * @version 1.0
 * @date 10/09/2019 13:05
 */
public class ThreadLocalTargetSourceDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("swapper/spring-threadlocal-swapper.xml");

        // 操作targetSource
        ThreadLocalTargetSource targetSource = (ThreadLocalTargetSource)context.getBean("threadLocalTargetSource");


        Runnable task =() -> {
            BusinessService businessObject = (BusinessService) context.getBean("businessObject");
            businessObject.hello();
            // 销毁 targetSource.destroy();
        };

        // 特地设置一定数量线程,观察线程如果生命周期未结束,此时该对象依旧是最开始初始化的对象
        final ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            for (int i = 0; i < 9999; i++) {
                executorService.submit(task);
            }
        }finally {
            executorService.shutdown();
        }
    }
}
