package com.xinchen.spring.proxying.api.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 *
 * before advice
 *
 * 实现一个简单的方法调用计数器
 *
 * @author xinchen
 * @version 1.0
 * @date 03/09/2019 16:44
 */
public class CountingBeforeAdvice implements MethodBeforeAdvice {

    private int count;

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        ++count;
    }

    public int getCount(){
        return count;
    }
}
