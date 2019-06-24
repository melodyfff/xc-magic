package com.xinchen.spring.beans.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/24 23:37
 */
public class RickBeanPostProcess implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Rick) {
            System.out.println("Rick is in [BeanPostProcessor] - postProcessBeforeInitialization()");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Rick) {
            System.out.println("Rick is in [BeanPostProcessor] - postProcessAfterInitialization()");
        }
        return bean;
    }
}
