package com.xinchen.spring.api.advice;


import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 *
 * {@link ThrowsAdvice} 接口不包含任何方法
 *
 * 它是一个标记接口，用于标识给定对象实现一个或多个类型化throws建议方法
 *
 * 抛出的形式:
 *
 * afterThrowing([Method, args, target], subclassOfThrowable)
 *
 * 也可以只跑出单个(包括子类)
 *
 * <pre>
 *     public class RemoteThrowsAdvice implements ThrowsAdvice {
 *
 *     public void afterThrowing(RemoteException ex) throws Throwable {
 *         // Do something with remote exception
 *     }
 * </pre>
 *
 * @author xinchen
 * @version 1.0
 * @date 03/09/2019 16:47
 */
public class ServletThrowsAdviceWithArguments implements ThrowsAdvice {
    public void afterThrowing(Method m, Object[] args, Object target, Exception ex) {
        // Do something with all arguments
    }
}
