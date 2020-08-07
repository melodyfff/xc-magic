package com.xinchen.spring.web.controller.config;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 *
 * 采用编码配置路由的方式去注册RequestMappingHandlerMapping
 *
 * 官网文档相关： https://docs.spring.io/spring/docs/5.2.6.RELEASE/spring-framework-reference/web.html#mvc-ann-requestmapping-registration
 *
 *
 * {@link RequestModel}自定义注解，功能类似{@link RequestBody}
 * 解析参数处理流程的自定义类： {@link RequestModelArgumentResolver}
 * 关于参数绑定的具体流程
 * 参考博客： 自定义spring参数注解 - 打破@RequestBody单体限制 https://www.cnblogs.com/java-zhao/p/9119258.html
 * see {@link RequestMappingHandlerAdapter#getDefaultArgumentResolvers()}
 * see {@link RequestResponseBodyMethodProcessor}
 * see {@link HandlerMethodArgumentResolver}
 * see {@link AbstractMessageConverterMethodArgumentResolver}
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2020/5/7 23:32
 */
@Component
public class UserHandler {
    public ResponseEntity<String> ok(@RequestBody String say){
        return ResponseEntity.ok(say);
    }

    public ResponseEntity<String> customArgument(@RequestModel String say){
        return ResponseEntity.ok(say);
    }
}
