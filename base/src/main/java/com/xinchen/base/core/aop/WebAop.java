package com.xinchen.base.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/3/13 23:07
 */
@Aspect
@Component
@Slf4j
public class WebAop {

    @Pointcut("execution(* com.xinchen.base.web.*.*(..))")
    public void point(){

    }

    @Before("point())")
    public void test(JoinPoint joinPoint){
        log.info(">>> WebAop: method:{}() , args:{}",joinPoint.getSignature().getName(),joinPoint.getArgs());
    }
}
