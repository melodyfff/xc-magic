package com.xinchen.spring.beans.scope;

import com.xinchen.spring.beans.entity.Coffee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自定义scope
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/1/31 23:48
 */
public class APP {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("custom-scope.xml");

        final Coffee coffee1 = (Coffee) context.getBean("coffee1");
        final Coffee coffee2 = (Coffee) context.getBean("coffee2");
        final Coffee coffee3 = context.getBean(Coffee.class);
        // No qualifying bean of type 'com.xinchen.spring.beans.entity.Coffee' available: expected single matching bean but found 2: coffee1,coffee2

    }
}
