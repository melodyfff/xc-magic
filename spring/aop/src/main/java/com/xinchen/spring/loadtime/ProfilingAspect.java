package com.xinchen.spring.loadtime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.util.StopWatch;

/**
 * @author xinchen
 * @version 1.0
 * @date 29/08/2019 15:36
 */
@Aspect
@Configurable
public class ProfilingAspect {


    @Around("methodsToBeProfiled()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        try {
            sw.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }
    }

    @Pointcut("execution(public * com.xinchen.spring.loadtime.StubEntitlementCalculationService..*(..))")
    public void methodsToBeProfiled() {}
}
