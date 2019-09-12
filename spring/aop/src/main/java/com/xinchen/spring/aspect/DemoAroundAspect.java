package com.xinchen.spring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xinchen
 * @version 1.0
 * @date 23/08/2019 14:47
 */
@Aspect
@Component
@Order(2)
public class DemoAroundAspect {
    @Around(value = "com.xinchen.spring.aspect.SystemArchitecture.oneWithArgs(p1)",argNames = "pjp,p1")
    public Object doAround(ProceedingJoinPoint pjp, Object p1) throws Throwable {
        System.out.println("> around :" + p1);
        // Proceed with the next advice or target method invocation
        return pjp.proceed(new Object[]{p1});
    }
}
