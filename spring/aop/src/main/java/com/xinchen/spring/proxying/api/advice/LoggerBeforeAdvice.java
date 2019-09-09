package com.xinchen.spring.proxying.api.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author xinchen
 * @version 1.0
 * @date 09/09/2019 16:12
 */
public class LoggerBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("打印日志...");
    }
}
