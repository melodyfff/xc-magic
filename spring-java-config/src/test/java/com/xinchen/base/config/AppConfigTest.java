package com.xinchen.base.config;

import com.xinchen.base.config.auto.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/8/3 22:02
 */
public class AppConfigTest {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        final Hello bean = context.getBean(Hello.class);
        bean.say();
    }
}