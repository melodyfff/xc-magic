package com.xinchen.spring.module.web.core;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/4/9 23:21
 */
public class Magic  {
    private GenericApplicationContext applicationContext;

    public Magic(ApplicationContext applicationContext) {
        this.applicationContext = (GenericApplicationContext) applicationContext;
    }

    public void register(Class<?> clazz){
        final ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        try {
            beanFactory.registerSingleton(clazz.getSimpleName(),clazz.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
