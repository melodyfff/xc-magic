package com.xinchen.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * proxyTargetClass=true 强制使用CGLIB代理
 * @author xinchen
 * @version 1.0
 * @date 19/08/2019 16:18
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackages = "com.xinchen.spring")
public class AppConfig {
}
