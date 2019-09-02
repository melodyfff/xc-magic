package com.xinchen.spring;

import com.xinchen.spring.loadtime.LoadTimeWeaverConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;

/**
 * proxyTargetClass=true 强制使用CGLIB代理
 * @author xinchen
 * @version 1.0
 * @date 19/08/2019 16:18
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackages = "com.xinchen.spring",excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = {LoadTimeWeaverConfig.class})})
public class AppConfig {
}
