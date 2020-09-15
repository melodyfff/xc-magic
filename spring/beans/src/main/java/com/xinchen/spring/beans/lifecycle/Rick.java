package com.xinchen.spring.beans.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/24 23:30
 */
class Rick implements InitializingBean, DisposableBean {

    Rick(){
        System.out.println("Rick is in [Rick] - Rick()");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("Rick is in [@PostConstruct] - postConstruct()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Rick is in [InitializingBean] - afterPropertiesSet()");
    }

    void customInit(){
        System.out.println("Rick is in [Rick] - customInit()");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("Rick is in [@PreDestroy] - preDestroy()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Rick is in [DisposableBean] - destroy()");
    }

    void customDestroy(){
        System.out.println("Rick is in [Rick] - customDestroy()");
    }
}
