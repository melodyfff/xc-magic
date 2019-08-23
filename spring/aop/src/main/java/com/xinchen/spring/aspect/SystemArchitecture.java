package com.xinchen.spring.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 *
 * 公共全局切入点{@link Pointcut}
 *
 * @author xinchen
 * @version 1.0
 * @date 23/08/2019 12:47
 */
@Aspect
public class SystemArchitecture {

    @Pointcut(value = "execution(public * *(..))")
    public void anyPublicOperation() {}

    @Pointcut("within(com.xinchen.spring.service..*)")
    public void inPackage() {}

    @Pointcut("target(com.xinchen.spring.service.DemoService)")
    public void one(){}

    @Pointcut(value = "execution(* com.xinchen.spring.service.DemoService.say(..)) && args(param))",argNames = "param")
    public void oneWithArgs(Object param){}

    @Pointcut("execution(* com.xinchen.spring.service.DemoService.say(..))")
    public void oneMethod(){}


    @Pointcut("execution(* com.xinchen.spring.service.ExceptionDemoService..*(..))")
    public void getThrow(){}

    @Pointcut("@annotation(com.xinchen.spring.annotation.Ok)")
    public void toAnnotation(){}
}
