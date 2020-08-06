package com.xinchen.spring.aspect;

import com.xinchen.spring.annotation.Ok;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author xinchen
 * @version 1.0
 * @date 23/08/2019 15:34
 */
@Aspect
@Component
@Order(3)
public class AnnotationAspect {

    @Around("com.xinchen.spring.aspect.SystemArchitecture.toAnnotation() && @annotation(ok)")
    public Object toAnnotation(ProceedingJoinPoint pjp, Ok ok) throws Throwable {
        // 通过反射获取注解的相关
        // Class.forName(pjp.getTarget().getClass().getName()).getMethods()[0].getDeclaredAnnotations()[0].annotationType()
        System.out.println("> catch Annotation");
        return pjp.proceed();
    }

}
