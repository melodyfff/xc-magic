package com.xinchen.spring.customize;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.BeforeAdvice;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author xinchen
 * @version 1.0
 * @date 10/09/2019 13:52
 */
public class CustomizeBeforeAdviceInterceptor implements MethodInterceptor, BeforeAdvice, Serializable {


    private final CustomizeBeforeAdvice advice;

    public CustomizeBeforeAdviceInterceptor(CustomizeBeforeAdvice advice) {
        Assert.notNull(advice,"Advice must not be null");
        this.advice = advice;
    }


    @Override

    public Object invoke(MethodInvocation invocation) throws Throwable {

        this.advice.before(invocation.getMethod(),invocation.getArguments(),invocation.getThis());

        return invocation.proceed();
    }
}
