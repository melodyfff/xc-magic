package com.xinchen.base.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 *
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @date Created In 2019/3/13 22:54
 * @version 1.0
 */
@Aspect
@Component
@Slf4j
public class ServiceAop {

    @Pointcut("execution(* com.xinchen.base.core.service.impl..*(..))")
    public void point(){

    }


    @Before("point()")
    public void test(JoinPoint joinPoint){
        log.info(">>> ServiceAop: method:{}() , args:{}",joinPoint.getSignature().getName(),joinPoint.getArgs());
    }
}
