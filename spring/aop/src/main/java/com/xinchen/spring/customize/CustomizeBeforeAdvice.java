package com.xinchen.spring.customize;

import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;

import java.lang.reflect.Method;

/**
 *
 * 自定义新的Advice Types
 *
 * 自定义Advice类型的唯一约束是它必须实现 {@link Advice}标记接口
 *
 * 具体可参考{@link AfterReturningAdviceInterceptor}等的实现
 *
 * {@link org.springframework.aop.framework.adapter} 包是一个SPI的拓展包,支持自定义advice types
 *
 * 参考: https://docs.spring.io/spring/docs/5.2.0.BUILD-SNAPSHOT/spring-framework-reference/core.html#aop-extensibility
 *
 *
 * @author xinchen
 * @version 1.0
 * @date 10/09/2019 13:37
 */
public interface CustomizeBeforeAdvice extends Advice {
    /**
     * Callback before a given method is invoked.
     * @param method method being invoked
     * @param args arguments to the method
     * @param target target of the method invocation. May be {@code null}.
     * @throws Throwable if this object wishes to abort the call.
     * Any exception thrown will be returned to the caller if it's
     * allowed by the method signature. Otherwise the exception
     * will be wrapped as a runtime exception.
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
