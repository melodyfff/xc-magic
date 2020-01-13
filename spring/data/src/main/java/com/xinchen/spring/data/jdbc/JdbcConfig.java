package com.xinchen.spring.data.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/9/22 17:49
 */
@Configuration
public class JdbcConfig {
    @Bean
    public UserDao userDao(){
       return new UserDao();
   }
}
