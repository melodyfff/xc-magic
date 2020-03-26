package com.xinchen.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 页面地址 ： http://localhost:8080/swagger-ui.html
 * api地址  ： http://localhost:8080//v2/api-docs
 */
@SpringBootApplication
public class SpringSwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSwaggerApplication.class, args);
    }

}
