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
 * 访问地址： curl http://localhost:8080/app1/test -d "hello world"
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
        final RequestMappingInfo info = RequestMappingInfo
                .paths("/test").methods(RequestMethod.POST).build();
        Method method = UserHandler.class.getMethod("ok", String.class);
        mapping.registerMapping(info, userHandler, method);
    }
}
