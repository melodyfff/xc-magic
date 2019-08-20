package com.xinchen.spring.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author xinchen
 * @version 1.0
 * @date 20/08/2019 15:31
 */
@Aspect
@Component
public class DemoAspect extends AbstractPointCuts {

    @Before(value = "inPackage()")
    public void doBefore(){
        System.out.println("before cut.");
    }


    @After("one()")
    public void doAfter(){
        System.out.println("after cut.");
    }

}
