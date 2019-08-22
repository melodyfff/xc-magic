package com.xinchen.spring.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author xinchen
 * @version 1.0
 * @date 20/08/2019 15:42
 */
public abstract class AbstractPointCuts {
    @Pointcut(value = "execution(public * *(..))")
    public void anyPublicOperation() {}

    @Pointcut("within(com.xinchen.spring.service..*)")
    public void inPackage() {}

    @Pointcut("anyPublicOperation() && inPackage()")
    public void both(){}

    @Pointcut("target(com.xinchen.spring.service.DemoService)")
    public void one(){}

    @Pointcut("execution(* com.xinchen.spring.service.DemoService.say())")
    public void oneMethod(){}


    @Pointcut("execution(* com.xinchen.spring.service.ExceptionDemoService..*(..))")
    public void getThrow(){}

}
