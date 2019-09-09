package com.xinchen.spring.targetsource;

import com.xinchen.spring.targetsource.detadata.PersonService;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xinchen
 * @version 1.0
 * @date 09/09/2019 17:32
 */
public class SwapperDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("swapper/spring-swapper.xml");
        HotSwappableTargetSource swapper  = (HotSwappableTargetSource) context.getBean("swapper");
        final Object swap = swapper.swap(new PersonService());
    }
}
