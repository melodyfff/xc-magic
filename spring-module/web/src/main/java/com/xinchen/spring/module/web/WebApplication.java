package com.xinchen.spring.module.web;

import com.xinchen.spring.module.base.annotation.EnableBaseContext;
import com.xinchen.spring.module.base.config.BaseContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 使用{@link SpringBootServletInitializer}是为了有些依赖外部容器启动的项目，如jboss老版本等
 */
@SpringBootApplication
//@EnableBaseContext
public class WebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        // 这种方式启动需要使用@EnableBaseContext载入基础环境
        SpringApplication.run(WebApplication.class, args);

        // 这种方式启动不需要@EnableBaseContext
//        new SpringApplicationBuilder()
//                .sources(WebApplication.class, BaseContextConfiguration.class)
//                .run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WebApplication.class,BaseContextConfiguration.class);
    }
}
