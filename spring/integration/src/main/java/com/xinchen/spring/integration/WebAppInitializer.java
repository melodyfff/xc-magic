package com.xinchen.spring.integration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration;

/**
 * Hello world!
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }

    @Override
    protected String getServletName() {
        return "dispatcher";
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // 设置启动级别
        registration.setLoadOnStartup(1);
    }
}
