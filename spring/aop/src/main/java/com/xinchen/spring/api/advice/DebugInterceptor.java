package com.xinchen.spring.api.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 *
 * interception around advice
 *
 * {@link MethodInvocation} 对方法的调用的描述，在方法调用时给予拦截器。
 *
 * @author xinchen
 * @version 1.0
 * @date 03/09/2019 16:37
 */
public class DebugInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Before: invocation=[" + invocation + "]");
        Object rval = invocation.proceed();
        System.out.println("Invocation returned");
        return rval;
    }
}
