package com.xinchen.spring.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 *
 * Root WebApplicationContext Config
 *
 * @author xinchen
 * @version 1.0
 * @date 11/09/2019 12:16
 */
@Configuration
@ComponentScan(basePackages = "com.xinchen.spring.web",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {AppConfig.class})})
public class RootConfig {
}
