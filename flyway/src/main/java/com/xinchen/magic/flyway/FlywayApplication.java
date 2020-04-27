package com.xinchen.magic.flyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EntityScan("com.xinchen.magic.flyway.core.entity")
public class FlywayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlywayApplication.class, args);
    }

}
