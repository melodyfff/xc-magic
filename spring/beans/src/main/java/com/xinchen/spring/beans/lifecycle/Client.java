package com.xinchen.spring.beans.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/6/24 23:35
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RickConfig.class);
        ((AnnotationConfigApplicationContext) applicationContext).close();
    }
}
