package com.xinchen.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.xinchen.spring.base.entity")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
