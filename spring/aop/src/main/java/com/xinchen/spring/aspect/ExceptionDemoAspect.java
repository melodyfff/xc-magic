package com.xinchen.spring.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author xinchen
 * @version 1.0
 * @date 20/08/2019 16:24
 */
@Aspect
@Component
public class ExceptionDemoAspect {

    @AfterThrowing(value = "com.xinchen.spring.aspect.SystemArchitecture.getThrow()",throwing = "ex")
    public void catchException(Exception ex){
        System.out.println("> we get the exception : "+ ex.getLocalizedMessage());
    }
}
