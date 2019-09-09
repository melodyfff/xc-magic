package com.xinchen.spring.proxying.api.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 *
 * after advice
 *
 * <pre>
 *     public interface AfterReturningAdvice extends Advice {
 *     void afterReturning(Object returnValue, Method m, Object[] args, Object target) throws Throwable;
 * }
 * </pre>
 *
 * 返回后的建议可以访问返回值（它无法修改），调用的方法，方法的参数和目标。
 *
 *
 * 实现一个计算未抛出异常调用成功的方法计数
 *
 * @author xinchen
 * @version 1.0
 * @date 03/09/2019 16:54
 */
public class CountingAfterReturningAdvice implements AfterReturningAdvice {
    private int count;

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        ++count;
    }

    public int getCount(){
        return count;
    }
}
