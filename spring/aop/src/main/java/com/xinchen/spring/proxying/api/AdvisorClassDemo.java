package com.xinchen.spring.proxying.api;

import com.xinchen.spring.proxying.api.advisor.Lockable;
import com.xinchen.spring.proxying.api.metadata.Person;
import org.springframework.aop.framework.Advised;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 *
 * 基于xml的demo,代理的是类,具体在spring-proxy-class.xml中查看
 *
 * 如果不开启cglib则会报错
 * <pre>
 * Exception in thread "main" java.lang.ClassCastException: com.sun.proxy.$Proxy2 cannot be cast to com.xinchen.spring.proxying.api.metadata.Person
 * 	at com.xinchen.spring.proxying.AdvisorClassDemo.main(AdvisorClassDemo.java:21)
 * </pre>
 *
 * {@link com.xinchen.spring.proxying.api.advisor.LockMixin} 功能增强,不侵入代码增加加锁功能,可强制转换动态代理类调用lock()
 *
 * 代理对象可以转换为{@link Advised}进行相关操作,无论它实现哪个接口,任何aop代理都可以转换为此接口
 *
 * @author xinchen
 * @version 1.0
 * @date 09/09/2019 12:16
 */
public class AdvisorClassDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:proxy/spring-proxy-class.xml");
        Person person = (Person) context.getBean("person");

        // 这里强转换为Advised进行操作
        System.out.println(Arrays.toString(((Advised) person).getAdvisors()));

        person.action();

        // 这里强转为Lockable不会报错,原理是在生产代理的时候，代理对象动态的实现了Lockable 接口
        // 参考: https://www.cnblogs.com/chihirotan/p/7365890.html
        ((Lockable)person).lock();

        // 由于被锁定,所以这里会抛出异常
        person.setName("locked");
    }
}
