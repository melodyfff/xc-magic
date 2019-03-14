package com.xinchen.base.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

/**
 *
 * Spring环境配置
 * 排除web相关，避免被重复扫描
 *
 *
 * EnableAspectJAutoProxy 开启aop自动扫描注解  <aop:aspectj-autoproxy />
 * 默认JDK动态代理， proxyTargetClass = true 开启cglib动态代理
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/2/28 23:26
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@PropertySource(value = "classpath:application.properties",ignoreResourceNotFound = true)
@ComponentScan(basePackages = "com.xinchen.base",
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = Controller.class),
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,value = WebConfig.class)})
public class AppConfig {
}
