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
public class ExceptionDemoAspect extends AbstractPointCuts{

    @AfterThrowing("getThrow()")
    public void catchException(){
        System.out.println("we get the exception");
    }
}
