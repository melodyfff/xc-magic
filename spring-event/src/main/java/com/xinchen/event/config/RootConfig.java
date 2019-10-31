package com.xinchen.event.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xinchen
 * @version 1.0
 * @date 23/10/2019 14:25
 */
@Configuration
@ComponentScan(basePackages = "com.xinchen.event")
@Import(AsyncConfig.class)
public class RootConfig {
}
