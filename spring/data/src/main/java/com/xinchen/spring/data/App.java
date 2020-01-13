package com.xinchen.spring.data;

import com.xinchen.spring.data.jdbc.JdbcConfig;
import com.xinchen.spring.data.jdbc.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class, JdbcConfig.class);

        final UserDao bean = context.getBean(UserDao.class);
        System.out.println(bean.rowCount());
        System.out.println(bean.getUserById(1L));
    }
}
