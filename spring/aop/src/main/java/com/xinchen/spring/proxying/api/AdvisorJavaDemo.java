package com.xinchen.spring.proxying.api;

import com.xinchen.spring.proxying.api.advice.DebugInterceptor;
import com.xinchen.spring.proxying.api.advisor.LockMixinAdvisor;
import com.xinchen.spring.proxying.api.advisor.Lockable;
import com.xinchen.spring.proxying.api.metadata.Person;
import com.xinchen.spring.proxying.api.metadata.PersonImpl;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;

import java.util.Arrays;

/**
 *
 * 基于编程方式的demo
 *
 * {@link com.xinchen.spring.proxying.api.advisor.LockMixin} 功能增强,不侵入代码增加加锁功能,可强制转换动态代理类调用lock()
 *
 * 代理对象可以转换为{@link Advised}进行相关操作,无论它实现哪个接口,任何aop代理都可以转换为此接口
 *
 * @author xinchen
 * @version 1.0
 * @date 09/09/2019 12:16
 */
public class AdvisorJavaDemo {
    public static void main(String[] args) {
        // 初始化代理对象
        ProxyFactory factory = new ProxyFactory(new PersonImpl());

        // 添加环绕增强
        factory.addAdvisor(new LockMixinAdvisor());
        factory.addAdvice(new DebugInterceptor());

        Person person = (Person) factory.getProxy();

        // 这里强转换为Advised进行操作
        System.out.println(Arrays.toString(((Advised) person).getAdvisors()));


        person.action();

        // 这里强转为Lockable不会报错,原理是在生产代理的时候，代理对象动态的实现了Lockable 接口
        // 参考: https://www.cnblogs.com/chihirotan/p/7365890.html
        ((Lockable) person).lock();

        person.setName("locked");
    }
}
