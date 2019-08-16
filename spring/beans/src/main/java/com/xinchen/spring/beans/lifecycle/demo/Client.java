package com.xinchen.spring.beans.lifecycle.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/2/6 19:04
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("custom-lifecycle.xml");

        context.getBean("lifeCycleDemoByAnnotation");

        // 为context添加一个关闭的钩子
        ((ClassPathXmlApplicationContext) context).registerShutdownHook();

        // 代码继续运行
        System.out.println("still run.....");
        context.getBean("lifeCycleDemoByAnnotation");


        // main方法退出，在应用程序关闭前调用hook
    }
}
