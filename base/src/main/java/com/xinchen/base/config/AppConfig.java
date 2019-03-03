package com.xinchen.base.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 *
 * Spring环境配置
 * 排除web相关，避免被重复扫描
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/2/28 23:26
 */
@Configuration
@ComponentScan(basePackages = "com.xinchen.base",
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = WebConfig.class)})
public class AppConfig {
}
