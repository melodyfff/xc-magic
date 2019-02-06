package com.xinchen.spring.beans.customizing;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * 自定义Bean并管理生命周期
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/2/6 18:59
 */
@Component("lifeCycleDemoByAnnotation")
public class LifecycleDemo implements InitializingBean, DisposableBean {

    private LifecycleDemo() {
        System.out.println("init Constructor method...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init from InitializingBean...");
    }

    @PostConstruct
    public void init(){
        System.out.println("init init() method...");
    }

    @PreDestroy
    public void myDestroy(){
        System.out.println("destroy from myDestroy()...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy from DisposableBean...");
    }
}
