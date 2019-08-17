package com.xinchen.spring.beans.conversion.propertyeditors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 参考： https://www.concretepage.com/spring/custom-propertyeditors-spring-example
 * https://stackoverflow.com/questions/12544479/spring-mvc-type-conversion-propertyeditor-or-converter
 * @author xinchen
 * @version 1.0
 * @date 17/08/2019 15:43
 */
public class TestCase {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("property-editor.xml");
        final Person bean = context.getBean(Person.class);
        final PersonType type = bean.getType();
        System.out.println(type.getTypeName());

    }
}
