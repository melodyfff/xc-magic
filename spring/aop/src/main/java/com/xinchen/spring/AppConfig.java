package com.xinchen.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author xinchen
 * @version 1.0
 * @date 19/08/2019 16:18
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.xinchen.spring")
public class AppConfig {
}
