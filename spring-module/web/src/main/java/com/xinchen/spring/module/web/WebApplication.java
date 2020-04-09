package com.xinchen.spring.module.web;

import com.xinchen.spring.module.base.annotation.EnableBaseContext;
import com.xinchen.spring.module.base.config.BaseContextConfiguration;
import com.xinchen.spring.module.web.core.Magic;
import com.xinchen.spring.module.web.core.service.BusiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * 使用{@link SpringBootServletInitializer}是为了有些依赖外部容器启动的项目，如jboss老版本等
 */
@SpringBootApplication
//@EnableBaseContext
public class WebApplication extends SpringBootServletInitializer {

    private ApplicationContext context;

    public static void main(String[] args) {
        // 这种方式启动需要使用@EnableBaseContext载入基础环境
        SpringApplication.run(WebApplication.class, args);

        // 这种方式启动不需要@EnableBaseContext
//        new SpringApplicationBuilder()
//                .sources(WebApplication.class, BaseContextConfiguration.class)
//                .run(args);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 这里加载环境配置文件Configuration
        return builder.sources(WebApplication.class,BaseContextConfiguration.class);
    }

    @Override
    protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
        // 注意： org.springframework.boot.web.servlet.support.SpringBootServletInitializer.getExistingRootWebApplicationContext()
        // 这里会去检测ServletContext的Attribute是否包含其他ApplicationContext,直接返回该上下文


        // TODO 如果业务拆分模块后，这个地方获取spring上下文，用于模块加载业务具体的bean
        final ApplicationContext rootApplicationContext = super.createRootApplicationContext(servletContext);

        // 暂时不能被扫描依赖自动注入，需要加上@Lazy才能被正确识别
        new Magic(rootApplicationContext).register(BusiServiceImpl.class);

        return (WebApplicationContext) rootApplicationContext;
    }

}
