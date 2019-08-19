package com.xinchen.spring.beans.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xinchen
 * @version 1.0
 * @date 19/08/2019 16:05
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpelConfig.class);
        final FieldValueTestBean bean = context.getBean(FieldValueTestBean.class);
        System.out.println(bean.getDefaultLocal());
    }
}
