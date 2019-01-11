package com.xinchen.spring.beans;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/1/5 0:55
 */
public class APP {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:service.xml");
        final Object hello = context.getBean("hello");
        final Object coffee = context.getBean("coffee");
        System.out.println(hello);
        System.out.println(coffee);

        final Object coffeeService = context.getBean("coffeeService");
        System.out.println(coffeeService);

        final Object coffeeService2 = context.getBean("coffeeService2");
        System.out.println(coffeeService2);
    }
}
