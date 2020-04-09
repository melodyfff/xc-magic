package com.xinchen.spring.module.base.interceptors;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/4/9 22:05
 */
public class InterceptorConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 添加全局拦截器
        registry.addInterceptor(new CustomterInterceptor())
                .addPathPatterns("/**")
                .order(1);
    }
}
