package com.xinchen.spring.integration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * Root WevMvcConfig
 *
 * @author xinchen
 * @version 1.0
 * @date 18/09/2019 15:12
 */
@Configuration
@ComponentScan("com.xinchen.spring.integration")
@EnableWebMvc
public class RootConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 设置欢迎页面
        registry.addRedirectViewController("/", "/swagger-ui.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置支持静态资源访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static");

        // swagger-ui.html相关的所有前端静态文件都在springfox-swagger-ui.jar里面
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
