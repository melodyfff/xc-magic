package com.xinchen.spring.web.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 *
 * 通过手动配置的方式配置路由
 *
 * 访问地址：
 * <pre>
 *  curl http://localhost:8080/app1/test -d "hello world"
 *  curl http://localhost:8080/app1/test2 -d "hello world"
 *  curl http://localhost:8080/app1/test3 -d "hello world"
 * </pre>
 * 注意： app1和app都可以，此项目中配置了2个servlet
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/5/7 23:34
 */
@Configuration
public class HandlerMappingConfig {
    @Autowired
    public void setHandlerMapping(RequestMappingHandlerMapping mapping, UserHandler userHandler) throws NoSuchMethodException {
        // 传统的绑定方式
        final RequestMappingInfo info = RequestMappingInfo
                .paths("/test").methods(RequestMethod.POST).build();
        Method method = UserHandler.class.getMethod("ok", String.class);
        mapping.registerMapping(info, userHandler, method);


        // 通过bean name方式绑定
        final RequestMappingInfo info2 = RequestMappingInfo
                .paths("/test2").methods(RequestMethod.POST).build();
        Method method2 = UserHandler.class.getMethod("ok", String.class);
        mapping.registerMapping(info2, "userHandler", method2);

        // 自定义RequestModel注解，功能和RequestBody相似
        final RequestMappingInfo info3 = RequestMappingInfo
                .paths("/test3").methods(RequestMethod.POST).build();
        Method method3 = UserHandler.class.getMethod("customArgument", String.class);
        mapping.registerMapping(info3,userHandler,method3);
    }
}
