package com.xinchen.spring.web.controller.config;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * {@link RequestModel}自定义request参数解析，类似{@link org.springframework.web.bind.annotation.RequestBody}
 *
 * 解析参数处理流程的自定义类： {@link RequestModelArgumentResolver}
 *
 *
 * 参考博客： 自定义spring参数注解 - 打破@RequestBody单体限制 https://www.cnblogs.com/java-zhao/p/9119258.html
 * see {@link RequestMappingHandlerAdapter#getDefaultArgumentResolvers()}
 * see {@link RequestResponseBodyMethodProcessor}
 * see {@link HandlerMethodArgumentResolver}
 * see {@link AbstractMessageConverterMethodArgumentResolver}
 *
 * @author xinchen
 * @version 1.0
 * @date 07/08/2020 13:45
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestModel {
    boolean required() default true;
}
